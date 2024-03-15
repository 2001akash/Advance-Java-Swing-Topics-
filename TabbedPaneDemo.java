import java.awt.*;
import javax.swing.*;

class TabbedPaneDemo {
    static public void main(String... rk) {
        JFrame f = new JFrame();
        f.setSize(600, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(Color.YELLOW);
        JTabbedPane tp = new JTabbedPane();
        f.add(tp);

        JPanel personal_panel = new JPanel();
        personal_panel.setBackground(Color.magenta);
        tp.addTab("Personal", personal_panel);

        JPanel academic_panel = new JPanel();
        academic_panel.setBackground(Color.pink);
        tp.addTab("Academic", academic_panel);

        tp.setSelectedIndex(1);

        f.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        tp.removeTabAt(0);
    }
}



// import javax.swing.*;
// import java.awt.*;
// import java.util.Arrays;

// public class DrawShapes extends JFrame {

//     public DrawShapes(int... params) {
//         setSize(500, 500);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         Canvas canvas = new Canvas(params);
//         add(canvas);

//         setVisible(true);
//     }

//     public static void main(String[] args) {
//         // Example parameters: height, width, top-left-x, top-left-y, shape1, shape2, shape3, text1, text2, text3
//         int[] parameters = {200, 300, 50, 50, 1, 2, 3, 4, 5, 6};

//         SwingUtilities.invokeLater(() -> new DrawShapes(parameters));
//     }
// }

// class Canvas extends JPanel {

//     private int[] params;

//     public Canvas(int... params) {
//         this.params = params;
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);

//         int height = params[0];
//         int width = params[1];
//         int x = params[2];
//         int y = params[3];

//         // Draw rectangle
//         g.drawRect(x, y, width, height);

//         // Draw oval
//         g.drawOval(x, y, width, height);

//         // Draw square
//         int side = Math.min(width, height);
//         g.drawRect(x, y, side, side);

//         // Draw text
//         for (int i = 4; i < params.length; i++) {
//             int shapeType = params[i];
//             switch (shapeType) {
//                 case 1:
//                     g.drawRect(x, y, width, height);
//                     break;
//                 case 2:
//                     g.drawOval(x, y, width, height);
//                     break;
//                 case 3:
//                     int squareSide = Math.min(width, height);
//                     g.drawRect(x, y, squareSide, squareSide);
//                     break;
//                 case 4:
//                     // Draw text1
//                     g.drawString("Text1", x, y);
//                     break;
//                 case 5:
//                     // Draw text2
//                     g.drawString("Text2", x, y);
//                     break;
//                 case 6:
//                     // Draw text3
//                     g.drawString("Text3", x, y);
//                     break;
//             }
//         }
//     }
// }







