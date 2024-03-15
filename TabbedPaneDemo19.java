import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TabbedPaneDemo19 {
    static public void main(String... rk) {
        JFrame f = new JFrame();
        f.setSize(600, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        //JLabel lbl = new JLabel(new ImageIcon(
               // "C:\\Users\\hp\\OneDrive - Lovely Professional University\\Desktop\\CELEBAL.py\\Pictures\\Akash-passport photo.jpeg"));
        f.getContentPane().setBackground(Color.YELLOW);
       // f.add(lbl);

        JPanel bpanel = new JPanel();
        JButton b1 = new JButton("Previous");
        bpanel.add(b1);
        JButton b2 = new JButton("Next");
        bpanel.add(b2);

        f.add(bpanel, BorderLayout.SOUTH);

        JPanel tpanel = new JPanel();
        JButton b3 = new JButton("Show");
        JTextField t = new JTextField(20);
        tpanel.add(t);
        tpanel.add(b3);
        f.add(tpanel, BorderLayout.NORTH);

        CardLayout cl = new CardLayout();
        JPanel cardpanel = new JPanel();
        f.add(cardpanel);
        cardpanel.setLayout(cl);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.RED);
        cardpanel.add(p1, "Red");

        JPanel p2 = new JPanel();
        p2.setBackground(Color.green);
        cardpanel.add(p2, "Green");

        JPanel p4 = new JPanel();
        p4.setBackground(Color.cyan);
        cardpanel.add(p4, "Cyan");

        JPanel p3 = new JPanel();
        p3.setBackground(Color.PINK);
        cardpanel.add(p3, "Pink");

        class Listener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == b1)
                    cl.previous(cardpanel);
                else if (ae.getSource() == b2)
                    cl.next(cardpanel);
                else if (ae.getSource() == b3)
                    cl.show(cardpanel, t.getText());
            }
        }

        Listener l = new Listener();
        b1.addActionListener(l);
        b2.addActionListener(l);
        b3.addActionListener(l);
        f.setVisible(true);

    }
}