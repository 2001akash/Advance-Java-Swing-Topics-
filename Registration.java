import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == SportsRegistration18.jcb) {
            SportsRegistration18.l.setText("" + SportsRegistration18.jcb.getSelectedItem());
        }
    }
}

class SportsRegistration18 {
    static JButton b = new JButton("Submit");
    static JLabel l = new JLabel("Empty");
    static String arr[] = { "--Select--", "Punjab", "Haryana", "Himachal Pradesh" };
    static JComboBox<String> jcb = new JComboBox<>(arr);

    static public void main(String[] rk) {
        JFrame f = new JFrame("Sports Registration Form");
        f.setSize(600, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);

        f.add(jcb);
        jcb.setBounds(200, 100, 150, 40);

        f.add(l);
        l.setBounds(200, 200, 300, 50);
        l.setFont(new Font(Font.SERIF, 3, 28));
        l.setForeground(Color.blue);

        MyListener ml = new MyListener();
        b.addActionListener(ml);
        jcb.addActionListener(ml);

        f.add(b);
        b.setBounds(200, 300, 200, 50);

        ButtonGroup bg = new ButtonGroup();
        JCheckBox cb1 = new JCheckBox("Cricket", true);
        f.add(cb1);
        cb1.setBounds(100, 400, 100, 50);
        JCheckBox cb2 = new JCheckBox("Hockey", true);
        f.add(cb2);
        cb2.setBounds(200, 400, 100, 50);

        JRadioButton rb1 = new JRadioButton("Male", true);
        f.add(rb1);
        rb1.setBounds(100, 500, 100, 50);
        JRadioButton rb2 = new JRadioButton("Female");
        f.add(rb2);
        rb2.setBounds(200, 500, 100, 50);

        JToggleButton jt = new JToggleButton("ON");
        f.add(jt);
        jt.setBounds(400, 500, 80, 40);

        JButton b1 = new JButton("Submit");
        f.add(b1);
        b1.setBounds(400, 600, 100, 50);
        bg.add(cb2);
        bg.add(rb1);
        bg.add(rb2);
        bg.add(cb1);
        bg.add(jt);
        bg.add(b1);
        f.setVisible(true);
    }
}
