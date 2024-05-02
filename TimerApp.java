import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerApp extends JFrame {
    private Timer timer;
    private JLabel timerLabel;
    private JLabel lastTimeLabel;
    private long startTime;
    private long pausedTime;
    private long elapsedTime;
    private boolean isTimerRunning;

    public TimerApp() {
        setTitle("Timer App");
        setLayout(new FlowLayout());
        timerLabel = new JLabel("00:00:000");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 48));
        add(timerLabel);

        // lastTimeLabel = new JLabel("Last Time: 00:00:000");
        // add(lastTimeLabel);

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pauseTimer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        add(startButton);
        add(pauseButton);
        add(stopButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void startTimer() {
        if (!isTimerRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            timer = new Timer(10, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    int minutes = (int) (elapsedTime / 60000);
                    int seconds = (int) ((elapsedTime % 60000) / 1000);
                    int milliseconds = (int) (elapsedTime % 1000);
                    timerLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
                }
            });
            isTimerRunning = true;
            timer.start();
            lastTimeLabel.setText("Last Time: 00:00:000"); // Reset last time label
        }
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            timer.stop();
            isTimerRunning = false;
        }
    }

    private void stopTimer() {
        if (isTimerRunning) {
            timer.stop();
            isTimerRunning = false;
            int minutes = (int) (elapsedTime / 60000);
            int seconds = (int) ((elapsedTime % 60000) / 1000);
            int milliseconds = (int) (elapsedTime % 1000);
            lastTimeLabel.setText("Last Time: " + String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
            elapsedTime = 0;
        }
        timerLabel.setText("00:00:000");
    }

    public static void main(String[] args) {
        new TimerApp();
    }
}