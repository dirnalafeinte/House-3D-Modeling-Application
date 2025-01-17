package ca.ulaval.glo2004.gui.Toast;

import ca.ulaval.glo2004.gui.MainWindow;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final MainWindow mainWindow;

    public ExceptionHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        if (e.getClass() == NullPointerException.class) return;
        new Toast(mainWindow, e.getMessage());
        e.printStackTrace();
    }
}
