import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyCanvas extends Canvas {
    int x = 0;
    static boolean left, right;

    void move() {
        if (right == true)
            x++;
        else if (left == true)
            x--;

        if (x == 550)
            x = 0;
        try {
            Thread.sleep(5);
        } catch (Exception ex) {
        }
        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(0 + x, 200, 100, 100);
        move();

        /*
         * int [] x = {150, 200, 100, 500, 300};
         * int[] y = {100, 300, 150, 50, 150};
         * g.fillPolygon(x, y, 5);
         * 
         * 
         * g.setColor(Color.GREEN);
         * 
         * g.fillRect(150, 100, 300, 200);
         * g.setColor(Color.RED);
         * 
         * g.fillOval(250, 150, 100, 100);
         * 
         * g.drawLine(100, 200, 300, 550);
         * 
         * g.setColor(Color.BLUE);
         * g.setFont(new Font("Serif", 1, 30));
         * g.drawString("Lovely Professional University", 100, 60);
         * 
         * g.setColor(Color.RED);
         * g.setFont(new Font("Serif", 3, 24));
         * g.drawString("Phagwara", 230, 120);
         * 
         */
    }

    public static void main(String... rk) {
        JFrame f = new JFrame();
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        class MyListener extends KeyAdapter {
            public void keyPressed(KeyEvent k) {
                if (k.getKeyCode() == KeyEvent.VK_LEFT) {
                    left = true;
                    right = false;
                } else if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
                    left = false;
                    right = true;
                }
            }
        }

        MyListener l = new MyListener();
        MyCanvas c = new MyCanvas();
        c.addKeyListener(l);
        f.add(c);
        f.setVisible(true);
    }
}