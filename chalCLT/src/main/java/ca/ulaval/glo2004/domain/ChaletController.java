package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public Vue getVue() {
        return afficheur.getVue();
    }

    public Chalet getChalet() {
        return chalet;
    }

    public void ajouterPorte(PorteDTO porteDTO)  {
        Porte porte = accessoireFactory.createPorte(porteDTO, chalet);
        chalet.getMurByOrientation(porteDTO.Orientation).ajouterAccessoire(porte);
        notifyObservers();
    }

    public void ajouterFenetre(FenetreDTO fenetreDTO) {
        Fenetre fenetre = accessoireFactory.createFenetre(fenetreDTO, chalet);
        chalet.getMurByOrientation(fenetreDTO.Orientation).ajouterAccessoire(fenetre);
        notifyObservers();
    }

    public void supprimerAccessoire(Orientation mur, Coordonnee coordonnee) {

    }

    public void modifierPorte(PorteDTO porteModifie) {
        for (Mur mur : chalet.getMapMur().values()) {
            for (Accessoire accessoire : mur.getAccessoires()) {
                if (accessoire.getId().equals(porteModifie.id)) {
                    accessoire.setHauteur(porteModifie.Hauteur);
                    accessoire.setLargeur(porteModifie.Largeur);
                    accessoire.setCoordonnee(porteModifie.Coordonnee);
                }
            }
        }
        notifyObservers();
    }

    public List<PorteDTO> getPortes() {
        List<PorteDTO> portes = new ArrayList<>();
        for (Mur mur : chalet.getMapMur().values()) {
            for (Accessoire accessoire : mur.getAccessoires()) {
                if (accessoire instanceof Porte) {
                    portes.add(new PorteDTO((Porte) accessoire));
                }
            }
        }
        return portes;
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
