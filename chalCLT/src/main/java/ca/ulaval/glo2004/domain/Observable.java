package ca.ulaval.glo2004.domain;

public interface Observable {
    void registerObserver(Observer newObserver);
    void unregisterObserver(Observer newObserver);
}
