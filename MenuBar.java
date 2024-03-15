import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuBarDemo {
    public static void main(String[] args) {
        // Setting JFrame
        JFrame f = new JFrame();
        f.setSize(800, 300);
        f.setBackground(Color.gray);
        f.getContentPane().setBackground(Color.gray);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        // Creating JMenuBar
        JMenuBar jmb = new JMenuBar();

        // Creating JMenu
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Edit");
        JMenu m3 = new JMenu("View");
        JMenu m5 = new JMenu("Save As");
        JMenu zoomMenu = new JMenu("Zoom");

        // Create JMenuItem
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m12 = new JMenuItem("Save");
        JMenuItem m21 = new JMenuItem("Copy");
        JMenuItem m22 = new JMenuItem("Paste");
        JMenuItem m51 = new JMenuItem("PDF");
        JMenuItem m52 = new JMenuItem("DOCX");
        JMenuItem m53 = new JMenuItem("TXT");
        JMenuItem maxItem = new JMenuItem("Maximise");
        JMenuItem minItem = new JMenuItem("Minimise");

        class myListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == m11) {
                    System.out.println("Open");
                    JOptionPane.showMessageDialog(f, "Do you want to create a new file!");
                } else if (e.getSource() == m12) {
                    JFileChooser fe = new JFileChooser("D:");
                    fe.showOpenDialog(f);
                }
            }
        }

        myListener my = new myListener();
        m11.addActionListener(my);
        m12.addActionListener(my);

        // Check-able MenuItem
        JCheckBoxMenuItem statusItem = new JCheckBoxMenuItem("Status");

        // Adding JMenuItem to JMenu
        m1.add(m11);
        m1.add(m12);
        m1.add(m5);
        m2.add(m21);
        m2.add(m22);
        m3.add(zoomMenu);
        m5.add(m51);
        m5.add(m52);
        m5.add(m53);
        zoomMenu.add(maxItem);
        zoomMenu.add(minItem);
        m3.add(statusItem);

        // Adding Separator
        m1.insertSeparator(2);

        // Adding JMenuItem to JMenuBar
        jmb.add(m1);
        jmb.add(m2);
        jmb.add(m3);

        // Adding JMenuBar to JFrame
        f.setJMenuBar(jmb);

        // setting JFrame visible
        f.setVisible(true);
    }
}
