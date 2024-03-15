import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawShapes extends JFrame {

    private Canvas canvas;

    public DrawShapes() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
        add(canvas);

        // Buttons for shapes
        JButton rectangleButton = new JButton("Rectangle");
        JButton ovalButton = new JButton("Oval");
        JButton squareButton = new JButton("Square");

        rectangleButton.addActionListener(new ShapeButtonListener(ShapeType.RECTANGLE));
        ovalButton.addActionListener(new ShapeButtonListener(ShapeType.OVAL));
        squareButton.addActionListener(new ShapeButtonListener(ShapeType.SQUARE));

        // Panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rectangleButton);
        buttonPanel.add(ovalButton);
        buttonPanel.add(squareButton);

        // Layout setup
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawShapes());
    }

    enum ShapeType {
        RECTANGLE,
        OVAL,
        SQUARE
    }

    private class ShapeButtonListener implements ActionListener {

        private ShapeType shapeType;

        public ShapeButtonListener(ShapeType shapeType) {
            this.shapeType = shapeType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.setShapeType(shapeType);
            canvas.repaint();
        }
    }
}

class Canvas extends JPanel {

    private DrawShapes.ShapeType shapeType;

    public Canvas() {
        this.shapeType = DrawShapes.ShapeType.RECTANGLE; 
    }

    public void setShapeType(DrawShapes.ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int height = 100;
        int width = 200;
        int x = 50;
        int y = 50;

        switch (shapeType) {
            case RECTANGLE:
                g.drawRect(x, y, width, height);
                break;
            case OVAL:
                g.drawOval(x, y, width, height);
                break;
            case SQUARE:
                int side = Math.min(width, height);
                g.drawRect(x, y, side, side);
                break;
        }
    }
}
