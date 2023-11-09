package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.accessoires.Fenetre;
import ca.ulaval.glo2004.domain.accessoires.Porte;
import ca.ulaval.glo2004.domain.assemblers.AccessoireAssembler;
import ca.ulaval.glo2004.domain.assemblers.DTOAssembler;
import ca.ulaval.glo2004.domain.drawers.Afficheur;
import ca.ulaval.glo2004.domain.drawers.AfficheurMur;
import ca.ulaval.glo2004.domain.drawers.AfficheurPlan;
import ca.ulaval.glo2004.domain.dtos.*;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalFenetreException;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalPorteException;
import ca.ulaval.glo2004.domain.factories.AccessoireFactory;
import ca.ulaval.glo2004.domain.factories.ChaletFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChaletController implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final AccessoireFactory accessoireFactory = new AccessoireFactory();
    private final ChaletFactory chaletFactory = new ChaletFactory();
    private final DTOAssembler dtoAssembler = new DTOAssembler();
    private final AccessoireAssembler accessoireAssembler = new AccessoireAssembler();
    public Afficheur afficheur;
    private Chalet chalet;

    public ChaletController() {
        chalet = chaletFactory.createDefaultChalet();
        afficheur = new AfficheurPlan(chalet, Vue.PLAN);
    }

    public void setVue(Vue vue) {
        switch (vue) {
            case PLAN -> afficheur = new AfficheurPlan(chalet, vue);
            case FACADE, ARRIERE, GAUCHE, DROITE -> afficheur = new AfficheurMur(chalet, vue);
        }
        notifyObservers();
    }

    public void addPorte(AddPorteDTO addPorteDTO)  {
        Porte porte = accessoireFactory.createPorte(addPorteDTO, chalet);
        try {
            chalet.getMurByOrientation(Orientation.valueOf(addPorteDTO.orientation())).addAccessoire(porte);
        } catch (IllegalPorteException exception) {
            notifyObservers();
            throw new IllegalPorteException(exception.getMessage());
        }
        notifyObservers();
    }

    public void addFenetre(AddFenetreDTO addFenetreDTO) {
        Fenetre fenetre = accessoireFactory.createFenetre(addFenetreDTO, chalet);
        try {
        chalet.getMurByOrientation(Orientation.valueOf(addFenetreDTO.orientation())).addAccessoire(fenetre);
        } catch (IllegalFenetreException exception) {
            notifyObservers();
            throw new IllegalFenetreException(exception.getMessage());
        }
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
        Porte porte = accessoireAssembler.toPorte(porteDTO, chalet);
        try {
            chalet.getMurByOrientation(Orientation.valueOf(porteDTO.orientation())).modifyAccessoire(porte);
        } catch (IllegalPorteException exception) {
            notifyObservers();
            throw new IllegalPorteException(exception.getMessage());
        }
        notifyObservers();
    }

    public void modifyFenetre(FenetreDTO fenetreDTO) {
        Fenetre fenetre = accessoireAssembler.toFenetre(fenetreDTO, chalet);
        try {
            chalet.getMurByOrientation(Orientation.valueOf(fenetreDTO.orientation())).modifyAccessoire(fenetre);
        } catch (IllegalFenetreException exception) {
            notifyObservers();
            throw new IllegalFenetreException(exception.getMessage());
        }
        notifyObservers();
    }

    public Map<String, PorteDTO> getPortesById() {
        return chalet.getMurs().stream().map(Mur::getPortes).flatMap(List::stream).collect(Collectors.toMap(Porte::getId, dtoAssembler::toPorteDTO));
    }

    public Map<String, FenetreDTO> getFenetresById() {
        return chalet.getMurs().stream().map(Mur::getFenetres).flatMap(List::stream).collect(Collectors.toMap(Fenetre::getId, dtoAssembler::toFenetreDTO));
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
