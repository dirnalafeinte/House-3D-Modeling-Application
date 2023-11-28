package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.accessoires.Accessoire;
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
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;



public class ChaletController implements Observable {

    public Afficheur afficheur;
    private final List<Observer> observers = new ArrayList<>();
    private final AccessoireFactory accessoireFactory = new AccessoireFactory();
    private final ChaletFactory chaletFactory = new ChaletFactory();
    private final DTOAssembler dtoAssembler = new DTOAssembler();
    private final AccessoireAssembler accessoireAssembler = new AccessoireAssembler();
    private Chalet chalet;

    public ChaletController() {
        chalet = chaletFactory.createDefaultChalet();
        afficheur = new AfficheurPlan(chalet, Vue.PLAN);

    }

    public Chalet getDefaultChalet() {
        return chaletFactory.createDefaultChalet();
    }

    public void resetToDefaultChalet(){
        chalet.resetChaletDefaut();
        notifyObservers();
    }

    public void initializeDefaultValue(){
        chalet.resetChaletDefaut();
        notifyObservers();
    }

    public void setVue(Vue vue) {
        switch (vue) {
            case PLAN -> afficheur = new AfficheurPlan(chalet, vue);
            case FACADE, ARRIERE, GAUCHE, DROITE -> afficheur = new AfficheurMur(chalet, vue);
        }
        notifyObservers();
    }

    public PorteDTO addPorte(AddPorteDTO addPorteDTO)  {
        Porte porte = accessoireFactory.createPorte(addPorteDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(addPorteDTO.orientation())).addAccessoire(porte);
        notifyObservers();
        return dtoAssembler.toPorteDTO(porte);
    }

    public FenetreDTO addFenetre(AddFenetreDTO addFenetreDTO) {
        Fenetre fenetre = accessoireFactory.createFenetre(addFenetreDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(addFenetreDTO.orientation())).addAccessoire(fenetre);
        notifyObservers();
        return dtoAssembler.toFenetreDTO(fenetre);
    }

    public void deleteFenetre(FenetreDTO fenetreDTO) {
        chalet.getMurByOrientation(Orientation.valueOf(fenetreDTO.orientation())).removeAccessoireById(fenetreDTO.id());
        notifyObservers();
    }

    public void deletePorte(PorteDTO porteDTO) {
        chalet.getMurByOrientation(Orientation.valueOf(porteDTO.orientation())).removeAccessoireById(porteDTO.id());
        notifyObservers();
    }

    public PorteDTO modifyPorte(PorteDTO porteDTO) {
        Porte porte = accessoireAssembler.toPorte(porteDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(porteDTO.orientation())).modifyAccessoire(porte);
        notifyObservers();
        return dtoAssembler.toPorteDTO(porte);
    }

    public FenetreDTO modifyFenetre(FenetreDTO fenetreDTO) {
        Fenetre fenetre = accessoireAssembler.toFenetre(fenetreDTO, chalet);
        chalet.getMurByOrientation(Orientation.valueOf(fenetreDTO.orientation())).modifyAccessoire(fenetre);
        notifyObservers();
        return dtoAssembler.toFenetreDTO(fenetre);
    }

    public Map<String, PorteDTO> getPortesById() {
        return chalet.getMurs().stream().map(Mur::getPortes).flatMap(List::stream).collect(Collectors.toMap(Porte::getId, dtoAssembler::toPorteDTO));
    }

    public Map<String, FenetreDTO> getFenetresById() {

        return chalet.getMurs().stream().map(Mur::getFenetres).flatMap(List::stream).collect(Collectors.toMap(Fenetre::getId, dtoAssembler::toFenetreDTO));
    }
    public void updateDimensions(double newLargeur, double newLongueur, double newHauteur, double newEpaisseur, double newDelaRainure, double newDistanceMin) {
        Imperial defaultLargeur = chalet.getLargeur();
        Imperial defaultLongueur = chalet.getLongueur();
        Imperial defaultHauteur = chalet.getHauteur();
        Imperial defaultEpaisseur = chalet.getEpaisseurMur();
        Imperial defaultDeltaRainure = chalet.getDeltaRainure();
        Imperial defaultDistanceMin = chalet.getDistanceMin();


        Imperial updatedLargeur = Optional.ofNullable(newLargeur).map(value -> Imperial.fromFeet(value.intValue())).orElse(defaultLargeur);
        Imperial updatedLongueur = Optional.ofNullable(newLongueur).map(value -> Imperial.fromFeet(value.intValue())).orElse(defaultLongueur);
        Imperial updatedHauteur = Optional.ofNullable(newHauteur).map(value -> Imperial.fromFeet(value.intValue())).orElse(defaultHauteur);
        Imperial updatedEpaisseur = Optional.ofNullable(newEpaisseur).map(value -> Imperial.fromInches(value.intValue())).orElse(defaultEpaisseur);
        Imperial updatedDeltaRainure = Optional.ofNullable(newDelaRainure).map(value -> Imperial.fromInches(value.intValue())).orElse(defaultDeltaRainure);
        Imperial updatedDistanceMin = Optional.ofNullable(newDistanceMin).map(value -> Imperial.fromInches(value.intValue())).orElse(defaultDistanceMin);

        chalet.recalculerChalet(updatedLongueur,updatedLargeur,updatedHauteur, updatedEpaisseur, updatedDeltaRainure, updatedDistanceMin);
        notifyObservers();
    }

    public void resetChaletDefaut() {
        chalet.resetChaletDefaut();
        notifyObservers();
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

    public void exportPanneauxBruts(String path) {
        ExportBrut exportBrut = new ExportBrut(chalet, path);
        exportBrut.export();
    }

    public void exportPanneauxFinis(String path) {
        ExportFini exportFini = new ExportFini(chalet, path);
        exportFini.export();
    }

    public void exportRetraits(String path) {
        ExportRetrait exportRetrait = new ExportRetrait(chalet, path);
        exportRetrait.export();
    }

}
