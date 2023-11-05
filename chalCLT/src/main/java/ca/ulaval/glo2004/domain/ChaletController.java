package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChaletController implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final ChaletFactory chaletFactory = new ChaletFactory();
    private Afficheur afficheur;
    private Chalet chalet;

    public ChaletController() {
        chalet = chaletFactory.createDefaultChalet();
        afficheur = new Afficheur(chalet, Vue.PLAN);

        notifyObservers();
    }

    @Override
    public void registerObserver(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregisterObserver(Observer observable) {
        observers.remove(observable);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Afficheur getAfficheur() {
        return afficheur;
    }
}
