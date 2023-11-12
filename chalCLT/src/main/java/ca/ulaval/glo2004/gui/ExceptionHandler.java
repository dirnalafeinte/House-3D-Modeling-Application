package ca.ulaval.glo2004.gui;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Uncaught exception: " + e);
    }
}
