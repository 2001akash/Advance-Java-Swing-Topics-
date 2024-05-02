import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExtendedCurrencyConverter extends JFrame {
    private JLabel amountLabel, fromLabel, toLabel, resultLabel, historyLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton, historyButton, clearHistoryButton, saveHistoryButton, loadHistoryButton,
            updateRatesButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] currencies = { "USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR" };
    private double[] exchangeRates = { 1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14 };
    private List<String> conversionHistory = new ArrayList<>();

    public ExtendedCurrencyConverter() {
        setTitle("Extended Currency Converter");
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        amountLabel = new JLabel("Amount:");
        topPanel.add(amountLabel);

        amountField = new JTextField();
        topPanel.add(amountField);

        fromLabel = new JLabel("From:");
        fromComboBox = new JComboBox<>(currencies);
        topPanel.add(fromLabel);
        topPanel.add(fromComboBox);

        toLabel = new JLabel("To:");
        toComboBox = new JComboBox<>(currencies);
        topPanel.add(toLabel);
        topPanel.add(toComboBox);

        convertButton = new JButton("Convert");
        topPanel.add(convertButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel("Result will appear here");
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        JPanel historyPanel = new JPanel(new GridLayout(1, 3));
        historyButton = new JButton("Show History");
        clearHistoryButton = new JButton("Clear History");
        saveHistoryButton = new JButton("Save History");
        loadHistoryButton = new JButton("Load History");
        historyPanel.add(historyButton);
        historyPanel.add(clearHistoryButton);
        historyPanel.add(saveHistoryButton);
        historyPanel.add(loadHistoryButton);

        resultPanel.add(historyPanel, BorderLayout.SOUTH);

        JPanel updateRatesPanel = new JPanel(new FlowLayout());
        updateRatesButton = new JButton("Update Exchange Rates");
        updateRatesPanel.add(updateRatesButton);
        add(updateRatesPanel, BorderLayout.SOUTH);

        add(resultPanel, BorderLayout.CENTER);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromComboBox.getSelectedItem();
                    String toCurrency = (String) toComboBox.getSelectedItem();
                    double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                    double result = amount * exchangeRate;
                    resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);

                    String historyEntry = amount + " " + fromCurrency + " = " + decimalFormat.format(result) + " "
                            + toCurrency;
                    conversionHistory.add(historyEntry);
                } catch (Exception ex) {
                    resultLabel.setText("Invalid input. Please enter a positive number.");
                }
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        ExtendedCurrencyConverter.this,
                        String.join("\n", conversionHistory),
                        "Conversion History",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        clearHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conversionHistory.clear();
                JOptionPane.showMessageDialog(
                        ExtendedCurrencyConverter.this,
                        "History cleared",
                        "Clear History",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        saveHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveConversionHistoryToFile("conversion_history.txt");
                    JOptionPane.showMessageDialog(
                            ExtendedCurrencyConverter.this,
                            "History saved to file",
                            "Save History",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    resultLabel.setText("Error saving history.");
                }
            }
        });

        loadHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadConversionHistoryFromFile("conversion_history.txt");
                    JOptionPane.showMessageDialog(
                            ExtendedCurrencyConverter.this,
                            "History loaded from file",
                            "Load History",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    resultLabel.setText("Error loading history.");
                }
            }
        });

        updateRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateExchangeRates();
                    JOptionPane.showMessageDialog(
                            ExtendedCurrencyConverter.this,
                            "Exchange rates updated",
                            "Update Rates",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    resultLabel.setText("Error updating exchange rates.");
                }
            }
        });

        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1;
    }

    private void saveConversionHistoryToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String entry : conversionHistory) {
                writer.write(entry);
                writer.newLine();
            }
        }
    }

    private void loadConversionHistoryFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            conversionHistory.clear();
            while ((line = reader.readLine()) != null) {
                conversionHistory.add(line);
            }
        }
    }

    private void updateExchangeRates() throws IOException {
        // This is a mock implementation. Replace with actual API integration if
        // desired.
        // The actual code would involve making HTTP requests and parsing the response.
        // For this example, let's assume the exchange rates are updated.
        exchangeRates[1] = 0.85; // EUR
        exchangeRates[2] = 110.00; // JPY
        exchangeRates[3] = 0.73; // GBP
    }

    public static void main(String[] args) {
        new ExtendedCurrencyConverter();
    }
}
