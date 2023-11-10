package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.dtos.*;
import ca.ulaval.glo2004.domain.factories.AccessoireFactory;
import ca.ulaval.glo2004.domain.factories.ChaletFactory;
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

    public void addPorte(AddPorteDTO addPorteDTO)  {
        Porte porte = accessoireFactory.createPorte(addPorteDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(addPorteDTO.orientation())).addAccessoire(porte);
        notifyObservers();
    }

    public void addFenetre(AddFenetreDTO addFenetreDTO) {
        Fenetre fenetre = accessoireFactory.createFenetre(addFenetreDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(addFenetreDTO.orientation())).addAccessoire(fenetre);
        notifyObservers();
    }

    public void deleteFenetre(FenetreDTO fenetreDTO) {
        chalet.getMurByOrientation(Orientation.valueOf(fenetreDTO.orientation())).removeAccessoireById(fenetreDTO.id());
        notifyObservers();
    }

    public void deletePorte(PorteDTO porteDTO) {
        chalet.getMurByOrientation(Orientation.valueOf(porteDTO.orientation())).removeAccessoireById(porteDTO.id());
        notifyObservers();
    }

    public void modifyPorte(PorteDTO porteDTO) {
        Porte porte = accessoireFactory.createPorte(porteDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(porteDTO.orientation())).modifyAccessoire(porte);
        notifyObservers();
    }

    public void modifyFenetre(FenetreDTO fenetreDTO) {
        Fenetre fenetre = accessoireFactory.createFenetre(fenetreDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(fenetreDTO.orientation())).modifyAccessoire(fenetre);
        notifyObservers();
    }

    public Map<String, PorteDTO> getPortesById() {
        return chalet.getMurs().stream().map(Mur::getPortes).flatMap(List::stream).collect(Collectors.toMap(Porte::getId, dtoAssembler::toPorteDTO));
    }

    public Map<String, FenetreDTO> getFenetresById() {
        return chalet.getMurs().stream().map(Mur::getFenetres).flatMap(List::stream).collect(Collectors.toMap(Fenetre::getId, dtoAssembler::toFenetreDTO));
    }
//    public void updateDimensions(double largeur, double longueur, double hauteur) {
//
//        Imperial newLargeur = Imperial.fromFeet((int) largeur);
//        Imperial newLongueur = Imperial.fromFeet((int) longueur);
//        Imperial newHauteur = Imperial.fromFeet((int) hauteur);
//
//        chalet.recalculerChalet();
//        notifyObservers();
//
//    }

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
