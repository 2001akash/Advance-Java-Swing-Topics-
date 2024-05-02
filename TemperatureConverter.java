import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JComboBox<String> inputUnitCombo, outputUnitCombo;
    private JTextField inputField;
    private JTextField outputField;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the input components
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create JComboBox for input unit
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        inputUnitCombo = new JComboBox<>(units);
        inputPanel.add(new JLabel("Input Unit:"));
        inputPanel.add(inputUnitCombo);

        // Create input field
        inputField = new JTextField();
        inputPanel.add(new JLabel("Input Value:"));
        inputPanel.add(inputField);

        // Create a panel for the output components
        JPanel outputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create JComboBox for output unit
        outputUnitCombo = new JComboBox<>(units);
        outputPanel.add(new JLabel("Output Unit:"));
        outputPanel.add(outputUnitCombo);

        // Create output field (non-editable)
        outputField = new JTextField();
        outputField.setEditable(false);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(outputField, BorderLayout.SOUTH);

        // Add action listener to input field and combo boxes
        inputField.addActionListener(this);
        inputUnitCombo.addActionListener(this);
        outputUnitCombo.addActionListener(this);

        setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        double inputValue, outputValue;
        int inputUnit, outputUnit;

        try {
            inputValue = Double.parseDouble(inputField.getText());
        } catch (NumberFormatException ex) {
            outputField.setText("Invalid input value");
            return;
        }

        inputUnit = inputUnitCombo.getSelectedIndex();
        outputUnit = outputUnitCombo.getSelectedIndex();

        if (inputUnit == outputUnit) {
            outputValue = inputValue;
        } else if (inputUnit == 0) { // Celsius
            if (outputUnit == 1) { // Fahrenheit
                outputValue = inputValue * 9 / 5 + 32;
            } else { // Kelvin
                outputValue = inputValue + 273.15;
            }
        } else if (inputUnit == 1) { // Fahrenheit
            if (outputUnit == 0) { // Celsius
                outputValue = (inputValue - 32) * 5 / 9;
            } else { // Kelvin
                outputValue = (inputValue - 32) * 5 / 9 + 273.15;
            }
        } else { // Kelvin
            if (outputUnit == 0) { // Celsius
                outputValue = inputValue - 273.15;
            } else { // Fahrenheit
                outputValue = (inputValue - 273.15) * 9 / 5 + 32;
            }
        }

        outputField.setText(String.format("%.2f", outputValue));
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}