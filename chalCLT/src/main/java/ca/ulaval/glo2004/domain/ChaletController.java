package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.dtos.*;
import ca.ulaval.glo2004.domain.factories.AccessoireFactory;
import ca.ulaval.glo2004.domain.factories.ChaletFactory;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChaletController implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final AccessoireFactory accessoireFactory = new AccessoireFactory();
    private final ChaletFactory chaletFactory = new ChaletFactory();
    private final DTOAssembler dtoAssembler = new DTOAssembler();
    public Afficheur afficheur;
    private Chalet chalet;

    public ChaletController() {
        chalet = chaletFactory.createDefaultChalet();
        afficheur = new Afficheur(chalet, Vue.PLAN);
    }

    public void setVue(Vue vue) {
        afficheur.setVue(vue);
        notifyObservers();
    }

    public void ajouterPorte(AddPorteDTO addPorteDTO)  {
        Porte porte = accessoireFactory.createPorte(addPorteDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(addPorteDTO.orientation())).ajouterAccessoire(porte);
        notifyObservers();
    }

    public void ajouterFenetre(AddFenetreDTO addFenetreDTO) {
        Fenetre fenetre = accessoireFactory.createFenetre(addFenetreDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(addFenetreDTO.orientation())).ajouterAccessoire(fenetre);
        notifyObservers();
    }

    public void supprimerAccessoire(Orientation mur, Coordonnee coordonnee) {

    }

    public void modifierPorte(PorteDTO porteDTO) {
        for (Mur mur : chalet.getMurs()) {
            for (Accessoire accessoire : mur.getAccessoires()) {
                if (accessoire.getId().equals(porteDTO.id())) {
                    accessoire.setHauteur(Imperial.fromString(porteDTO.hauteur()));
                    accessoire.setLargeur(Imperial.fromString(porteDTO.largeur()));
                    accessoire.setCoordonnee(new Coordonnee(Imperial.fromString(porteDTO.coordonneeX()), chalet.getHauteur()));
                }
            }
        }
        notifyObservers();
    }

    public Map<String, PorteDTO> getPortesById() {
        return chalet.getMurs().stream().map(Mur::getPortes).flatMap(List::stream).collect(Collectors.toMap(Porte::getId, dtoAssembler::toPorteDTO));
    }

    public Map<String, FenetreDTO> getFenetresById() {
        return chalet.getMurs().stream().map(Mur::getFenetres).flatMap(List::stream).collect(Collectors.toMap(Fenetre::getId, dtoAssembler::toFenetreDTO));
    }

    public void modifierFenetre(FenetreDTO fenetreDTO) {

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
}
