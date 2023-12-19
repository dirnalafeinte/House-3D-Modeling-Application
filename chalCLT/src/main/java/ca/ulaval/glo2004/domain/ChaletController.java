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
import ca.ulaval.glo2004.domain.factories.AccessoireFactory;
import ca.ulaval.glo2004.domain.factories.ChaletFactory;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.util.UnitConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class ChaletController implements Observable, Serializable {

    public Afficheur afficheur;
    private final List<Observer> observers = new ArrayList<>();
    private final AccessoireFactory accessoireFactory = new AccessoireFactory();
    private final ChaletFactory chaletFactory = new ChaletFactory();
    private final DTOAssembler dtoAssembler = new DTOAssembler();
    private final AccessoireAssembler accessoireAssembler = new AccessoireAssembler();
    private Chalet chalet;
    private ChaletDTO chaletDTO;

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
    public void updateDimensions(ChaletDTO chalets) {

        Imperial updatedLongueur = Imperial.fromString(chalets.longueur());
        Imperial updatedLargeur = Imperial.fromString(chalets.largeur());
        Imperial updatedHauteur = Imperial.fromString(chalets.hauteur());
        Imperial updatedEpaisseur = Imperial.fromString(chalets.epaisseurMur());
        Imperial updatedDeltaRainure = Imperial.fromString(chalets.deltaRainure());
        Imperial updatedDistanceMin = Imperial.fromString(chalets.distanceMin());
        double angleToit = Double.parseDouble(chalets.angleToit());
        Orientation sensDuToit = Orientation.valueOf(chalets.sensDuToit());




        chalet.recalculerChalet(updatedLongueur,updatedLargeur,updatedHauteur, updatedEpaisseur, updatedDeltaRainure, updatedDistanceMin, angleToit, sensDuToit);
        notifyObservers();
    }

    public void sauvegarderFichier(String filePath) throws IOException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(chalet);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean importerFichier(String filePath) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))){
            chalet = (Chalet) input.readObject();
            afficheur.setChalet(chalet);
            notifyObservers();
            return true;

            //System.out.println(((Chalet)input.readObject()).get);
            //chalet = (Chalet) input.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
//        notifyObservers();
//        return null;
    }

    public void resetChaletDefaut() {
        chalet = chaletFactory.createDefaultChalet();
        afficheur.setChalet(chalet);
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

    public Drawable getObjectAtCoord(Coordonnee coordonnee) {
        List<Drawable> components = chalet.getVisibleComponents(afficheur.getVue());
        List<Accessoire> accessoires = new ArrayList<>();
        List<Mur> murs = new ArrayList<>();

        for (Drawable drawable : components) {
            if (drawable instanceof Mur)
                murs.add((Mur) drawable);
            else if (drawable instanceof Accessoire)
                accessoires.add((Accessoire) drawable);
        }

        for (Accessoire accessoire : accessoires){
            if (accessoire.estContenu(afficheur.getVue(), coordonnee))
                return accessoire;
        }

        for (Mur mur : murs) {
            if (mur.estContenu(afficheur.getVue(), coordonnee))
                return mur;
        }

        return null;
    }

    public Coordonnee getMousePostionInCoordonnee(int x, int y) {
        double zoom = afficheur.getZoomFactor();
        double offsetX = afficheur.getxOffset() * zoom;
        double offsetY = afficheur.getyOffset() * zoom;

        int mouseX = (int) ((x - offsetX) / zoom);
        int mouseY = (int) ((y - offsetY) / zoom);

        UnitConverter unitConverter = afficheur.getUnitConverter();;
        Imperial mouseCoordX = unitConverter.pixelToInches(mouseX);
        Imperial mouseCoordY = unitConverter.pixelToInches(mouseY);

        return new Coordonnee(mouseCoordX, mouseCoordY);
    }

    public ChaletDTO getChaletDTO() {
        return dtoAssembler.toChaletDTO(chalet);
    }
    public Chalet getChalet() {
        return this.chalet;
    }
}
