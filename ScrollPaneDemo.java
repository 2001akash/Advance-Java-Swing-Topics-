import javax.swing.*;

class ScrollPaneDemo {
    static public void main(String[] rk) {
        JFrame f = new JFrame("ScrollPane Demo");
        f.setSize(600, 800);

        String[] items = { "Amazon", "Optymyzer", "Infenion", "Flipkart", "Capgemini", "Play Simple", "Wipro" };
        JList<String> ls = new JList<>(items);
        f.setLayout(null);
        f.add(ls);
        ls.setBounds(250, 100, 100, 80);

        JTextArea ta = new JTextArea("Write your review...");
        JScrollPane jsp = new JScrollPane(ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        f.add(jsp);
        jsp.setBounds(200, 200, 150, 100);
        ta.setLineWrap(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}