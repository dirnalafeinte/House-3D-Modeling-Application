package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.List;

public class ChaletController implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final AccessoireFactory accessoireFactory = new AccessoireFactory();
    private final ChaletFactory chaletFactory = new ChaletFactory();
    private Afficheur afficheur;
    private Chalet chalet;

    public ChaletController() {
        chalet = chaletFactory.createDefaultChalet();
        afficheur = new Afficheur(chalet, Vue.PLAN);
    }

    public void setVue(Vue vue) {
        afficheur.setVue(vue);
        notifyObservers();
    }

    public Chalet getChalet() {
        return chalet;
    }

    public void ajouterPorte(PorteDTO porteDTO) {
        accessoireFactory.createPorte(porteDTO);
    }

    public void ajouterFenetre(Orientation mur, Coordonnee coordonnee, Imperial largeur, Imperial hauteur){

    }

    public void supprimerAccessoire(Orientation mur, Coordonnee coordonnee) {

    }

    public void modifierPorte(PorteDTO porteModifie) {

    }
    public void modifierFenetre(FenetreDTO fenetreModifie) {

    }

    public void mofifierChalet(ChaletDTO chaletModifie) {

    }

    public Drawable getObjectsAtCoord(Coordonnee coordonnee) {
        return null;
    }

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
