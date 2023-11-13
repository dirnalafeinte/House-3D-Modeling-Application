package ca.ulaval.glo2004;

import ca.ulaval.glo2004.gui.ExceptionHandler;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(mainWindow));
            }
        });
    }
}

