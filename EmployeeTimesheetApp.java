import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.*;
import java.util.*;
import java.util.List;

class InMemoryDatabase {
    // Holds in-memory lists to simulate a database.
    private final List<User> users = new ArrayList<>();
    private final List<TimesheetEntry> timesheets = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();

    public InMemoryDatabase() {
        // Initialize with some sample data.
        users.add(new User("employee1", "password", "employee"));
        users.add(new User("manager1", "password", "manager"));

        employees.add(new Employee("E1", "John Doe", "Engineering"));
        employees.add(new Employee("E2", "Jane Smith", "HR"));
    }

    public User authenticate(String username, String password) {
        // Checks if the username and password match any existing user.
        return users.stream()
            .filter(u -> u.username().equals(username) && u.password().equals(password))
            .findFirst()
            .orElse(null);
    }

    public List<TimesheetEntry> getTimesheetEntries(String employeeId) {
        // Returns all timesheet entries for the given employee ID.
        List<TimesheetEntry> result = new ArrayList<>();
        for (TimesheetEntry entry : timesheets) {
            if (entry.employeeId().equals(employeeId)) {
                result.add(entry);
            }
        }
        return result;
    }

    public void addTimesheetEntry(TimesheetEntry entry) {
        // Adds a new timesheet entry.
        timesheets.add(entry);
    }

    public List<Employee> getAllEmployees() {
        // Returns the list of all employees.
        return new ArrayList<>(employees);
    }

    public void addEmployee(Employee employee) {
        // Adds a new employee.
        employees.add(employee);
    }

    public void deleteEmployee(String employeeId) {
        // Deletes an employee by their ID.
        employees.removeIf(e -> e.employeeId().equals(employeeId));
    }

    public Map<String, Integer> generateReport() {
        // Generates a simple timesheet report based on the timesheet entries.
        Map<String, Integer> report = new HashMap<>();
        for (TimesheetEntry entry : timesheets) {
            report.put(entry.employeeId(), report.getOrDefault(entry.employeeId(), 0) + 1);
        }
        return report;
    }

    public List<User> users() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'users'");
    }
}

// Data classes used in the in-memory database.
record User(String username, String password, String role) {}
record Employee(String employeeId, String name, String department) {}
record TimesheetEntry(String employeeId, LocalDateTime startTime, LocalDateTime endTime, double duration) {}

public class EmployeeTimesheetApp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private final InMemoryDatabase db;

    public EmployeeTimesheetApp(InMemoryDatabase db) {
        this.db = db;

        setTitle("Employee Timesheet App");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Employee Timesheet App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(titleLabel);
        loginPanel.add(Box.createVerticalStrut(20));

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        usernamePanel.add(usernameField);
        loginPanel.add(usernamePanel);
        loginPanel.add(Box.createVerticalStrut(10));

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        loginPanel.add(passwordPanel);
        loginPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);
        loginPanel.add(buttonPanel);

        add(loginPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            authenticate(username, password);
        });

        signUpButton.addActionListener(e -> showSignUpScreen());

        setVisible(true);
    }

    private void authenticate(String username, String password) {
        User user = db.authenticate(username, password);
        if (user != null) {
            switch (user.role()) {
                case "employee" -> {
                    new EmployeeTimesheet(db, user.username()).setVisible(true);
                    dispose();
                }
                case "manager" -> {
                    new ManagerDashboard(db).setVisible(true);
                    dispose();
                }
                default -> JOptionPane.showMessageDialog(this, "Unknown role.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSignUpScreen() {
        new SignUpScreen(db, this);
    }

    class EmployeeTimesheet extends JFrame {
        private final InMemoryDatabase db;
        private final String employeeId;
        private JTable timesheetTable;
        private JButton startButton, endButton, addButton;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public EmployeeTimesheet(InMemoryDatabase db, String employeeId) {
            this.db = db;
            this.employeeId = employeeId;

            setTitle("Employee Timesheet");
            setSize(500, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(10, 10));

            timesheetTable = new JTable();
            JScrollPane scrollPane = new JScrollPane(timesheetTable);
            add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            startButton = new JButton("Start Time");
            endButton = new JButton("End Time");
            addButton = new JButton("Add Entry");

            startButton.addActionListener(e -> recordStartTime());
            endButton.addActionListener(e -> recordEndTime());
            addButton.addActionListener(e -> addEntry());

            buttonPanel.add(startButton);
            buttonPanel.add(endButton);
            buttonPanel.add(addButton);
            add(buttonPanel, BorderLayout.SOUTH);

            loadTimesheetData();
        }

        private void loadTimesheetData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Start Time");
            model.addColumn("End Time");
            model.addColumn("Duration (Hours)");

            List<TimesheetEntry> entries = db.getTimesheetEntries(employeeId);
            for (TimesheetEntry entry : entries) {
                model.addRow(new Object[]{
                    entry.startTime(),
                    entry.endTime(),
                    entry.duration()
                });
            }

            timesheetTable.setModel(model);
        }

        private void recordStartTime() {
            if (startTime == null) {
                startTime = LocalDateTime.now();
                JOptionPane.showMessageDialog(this, "Start time recorded: " + startTime, "Start Time", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Start time has already been recorded.", "Start Time", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void recordEndTime() {
            if (startTime != null && endTime == null) {
                endTime = LocalDateTime.now();
                Duration duration = Duration.between(startTime, endTime);
                double durationHours = (double) duration.toMinutes() / 60;
                JOptionPane.showMessageDialog(this, "End time recorded: " + endTime + "\nDuration: " + durationHours + " hours", "End Time", JOptionPane.INFORMATION_MESSAGE);
                startTime = null;
            } else if (startTime == null) {
                JOptionPane.showMessageDialog(this, "Start time has not been recorded yet.", "End Time", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "End time has already been recorded.", "End Time", JOptionPane.WARNING_MESSAGE);
            }
        }

        private void addEntry() {
            if (startTime == null || endTime == null) {
                JOptionPane.showMessageDialog(this, "Please record both start and end times before adding an entry.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Duration duration = Duration.between(startTime, endTime);
            double durationHours = (double) duration.toMinutes() / 60;

            db.addTimesheetEntry(new TimesheetEntry(employeeId, startTime, endTime, durationHours));

            loadTimesheetData();
            startTime = null;
            endTime = null;

            JOptionPane.showMessageDialog(this, "Time entry added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class ManagerDashboard extends JFrame {
        private final InMemoryDatabase db;
        private JTable employeeTable;
        private JButton addButton, deleteButton, reportButton;

        public ManagerDashboard(InMemoryDatabase db) {
            this.db = db;

            setTitle("Manager Dashboard");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout(10, 10));

            employeeTable = new JTable();
            JScrollPane scrollPane = new JScrollPane(employeeTable);
            add(scrollPane, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            addButton = new JButton("Add Employee");
            deleteButton = new JButton("Delete Employee");
            reportButton = new JButton("Generate Report");

            buttonPanel.add(addButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(reportButton);

            addButton.addActionListener(e -> addEmployee());
            deleteButton.addActionListener(e -> deleteEmployee());
            reportButton.addActionListener(e -> generateReport());

            add(buttonPanel, BorderLayout.SOUTH);

            loadEmployeeData();
        }

        private void loadEmployeeData() {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Employee ID");
            model.addColumn("Name");
            model.addColumn("Department");

            List<Employee> employees = db.getAllEmployees();
            for (Employee employee : employees) {
                model.addRow(new Object[]{
                    employee.employeeId(),
                    employee.name(),
                    employee.department()
                });
            }

            employeeTable.setModel(model);
        }

        private void addEmployee() {
            String employeeId = JOptionPane.showInputDialog(this, "Enter Employee ID:");
            String employeeName = JOptionPane.showInputDialog(this, "Enter Employee Name:");
            String employeeDepartment = JOptionPane.showInputDialog(this, "Enter Employee Department:");

            if (employeeId != null && employeeName != null && employeeDepartment != null) {
                db.addEmployee(new Employee(employeeId, employeeName, employeeDepartment));
                loadEmployeeData();
                JOptionPane.showMessageDialog(this, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please provide all employee details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void deleteEmployee() {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow >= 0) {
                String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);

                int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this employee?",
                    "Delete Confirmation",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    db.deleteEmployee(employeeId);
                    loadEmployeeData();
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No employee selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void generateReport() {
            Map<String, Integer> report = db.generateReport();
            StringBuilder reportText = new StringBuilder("Employee Timesheet Report:\n");

            for (Map.Entry<String, Integer> entry : report.entrySet()) {
                reportText.append("Employee ID: ")
                    .append(entry.getKey())
                    .append(", Total Hours Worked: ")
                    .append(entry.getValue())
                    .append("\n");
            }

            JOptionPane.showMessageDialog(this, reportText.toString(), "Timesheet Report", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    record SignUpScreen(InMemoryDatabase db, EmployeeTimesheetApp app) {
        public SignUpScreen {
            JFrame frame = new JFrame("Sign Up");
            frame.setSize(400, 200);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JTextField newUsernameField = new JTextField(15);
            JPasswordField newPasswordField = new JPasswordField(15);
            JButton signUpButton = new JButton("Sign Up");

            panel.add(new JLabel("Username:"));
            panel.add(newUsernameField);
            panel.add(Box.createVerticalStrut(10));
            panel.add(new JLabel("Password:"));
            panel.add(newPasswordField);
            panel.add(Box.createVerticalStrut(20));
            panel.add(signUpButton);

            frame.add(panel);

            signUpButton.addActionListener(e -> {
                String newUsername = newUsernameField.getText();
                String newPassword = new String(newPasswordField.getPassword());
                db.users().add(new User(newUsername, newPassword, "employee"));
                JOptionPane.showMessageDialog(frame, "User signed up successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            });

            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        InMemoryDatabase db = new InMemoryDatabase();
        new EmployeeTimesheetApp(db); // Pass InMemoryDatabase to the app
    }
}
