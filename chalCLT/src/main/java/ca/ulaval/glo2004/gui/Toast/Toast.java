package ca.ulaval.glo2004.gui.Toast;

import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.action.FadeOutToastAction;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Toast extends JWindow {
    private static final double DISTANCE_FROM_BOTTOM = 100;
    private static int CHARACTER_LENGTH_MULTIPLIER = 9;
    private static int TOAST_HEIGHT = 50;
    private static float MAX_OPACITY = 0.8F;
    private static int INITIAL_DELAY = 2000;
    private static int TOAST_RADIUS = 50;
    private final MainWindow mainWindow;
    private final String message;
    public Toast(MainWindow mainWindow, String message) {
        super(mainWindow);
        this.mainWindow = mainWindow;
        this.message = message;
        init();
    }

    private void init() {
        JLabel messageLabel = new JLabel(message);
        messageLabel.setForeground(Color.WHITE);
        getContentPane().setBackground(Color.BLACK);
        int toastWidth = message.length() * CHARACTER_LENGTH_MULTIPLIER;
        setSize(toastWidth, TOAST_HEIGHT);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        add(messageLabel);

        int x = (int) (mainWindow.getLocation().getX() + (mainWindow.getWidth() / 2) - (getWidth() / 2));
        int y = (int) (mainWindow.getLocation().getY() + mainWindow.getHeight() - DISTANCE_FROM_BOTTOM);
        setLocation(new Point(x, y));
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), TOAST_RADIUS, TOAST_RADIUS));

        showToast();
    }

    private void showToast() {
        setVisible(true);
        setOpacity(MAX_OPACITY);
        Timer timer = new Timer(100, new FadeOutToastAction(this, MAX_OPACITY));
        timer.setInitialDelay(INITIAL_DELAY);
        timer.start();
    }
}
