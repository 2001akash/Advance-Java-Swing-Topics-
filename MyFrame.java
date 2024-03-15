import javax.swing.*;
import java.awt.*;

class MyFrame {
    public static void main(String [] args){
        // Initialising the frame
        JFrame frame = new JFrame("Calculator");
        frame.setBackground(new Color(155,255, 155));
        frame.getContentPane().setBackground(new Color(155,255, 155));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        // TextField for display
        JTextField displayField = new JTextField();
        frame.add(displayField, BorderLayout.NORTH);

        // Creating a JPanel for holding the Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        // Adding number buttons
        for(int i = 1; i <= 9; i++) {
            JButton number = new JButton(""+i);
            buttonPanel.add(number);
        }

        // Adding zero and point buttons
        String[] zeros = {"0", "00", "."};
        for(String zero : zeros)
        {
            JButton zeroButton = new JButton(zero);
            buttonPanel.add(zeroButton);
        }


        // Separate JPanel for the operations column
        JPanel buttonPanel2 = new JPanel(new GridLayout(0, 1, 5, 5));

        // Adding operations buttons
        String[] operations = {"+", "-", "*", "/"};
        for(String operation : operations) {
            JButton operationButton = new JButton(operation);
            buttonPanel2.add(operationButton);
        }

        // Adding equals button
        JButton equalsButton = new JButton("=");
        buttonPanel2.add(equalsButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(buttonPanel2, BorderLayout.EAST);

        // Adding Submit Button
        JButton submit = new JButton("Submit");
        frame.add(submit, BorderLayout.SOUTH);

        // SetVisible
        frame.setVisible(true);
    }

}