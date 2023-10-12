package ca.ulaval.glo2004.domain;

public interface Observable {
    public void registerObserver(Observer newObserver);
    public void unregisterObserver(Observer newObserver);
}
