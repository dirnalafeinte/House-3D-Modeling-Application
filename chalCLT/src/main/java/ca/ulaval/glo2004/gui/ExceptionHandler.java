package ca.ulaval.glo2004.gui;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final MainWindow mainWindow;

    public ExceptionHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        new Toast(mainWindow, e.getMessage());
    }
}
