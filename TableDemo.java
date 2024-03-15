import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TableDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(400, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.gray);

        // JComboBox
        String[] items = { "Kerala", "Tamil Nadu", "Bengaluru" };
        JComboBox<String> states = new JComboBox<>(items);
        f.add(states);
        states.setBounds(100, 100, 200, 40);

        states.addItem("Punjab");
        states.addItem("Delhi");
        states.addItem("Chandigarh");
        class MyListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == states) {
                    System.out.println("You have selected " + states.getSelectedItem());
                }
            }
        }

        states.addActionListener(new MyListener());

        // JList will overlap other items if not added in scrollbar
        String[] names = { "Ravi", "Arnav", "Shahbaz", "Balpreet", "A", "B", "C", "D" };
        JList<String> jl = new JList<>(names);
        JScrollPane jsp = new JScrollPane(jl);
        // jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        f.add(jsp);
        jsp.setBounds(100, 200, 200, 80);

        // JTable
        // The entries are editable in the JTable but column header are non-editable
        // You can also swap the columns
        Object[][] data = { { "A", 3.78, "ML" },
                { "B", 9.12, "Full Stack" },
                { "C", 6.91, "Cyber Security" },
                { "D", 6.72, "Full Stack" }
        };
        Object[] column = { "Name", "CGPA", "Specialization" };
        JTable table = new JTable(data, column);
        JScrollPane jsp1 = new JScrollPane(table);
        f.add(jsp1);
        jsp1.setBounds(100, 300, 200, 90);

        f.setVisible(true);
    }
}