package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chalet {
    private static final Imperial DEFAULT_LARGEUR = Imperial.fromFeet(10);
    private static final Imperial DEFAULT_LONGUEUR = Imperial.fromFeet(10);
    private static final Imperial DEFAULT_HAUTEUR = Imperial.fromFeet(8);
    private static final Imperial DEFAULT_DELTA_RAINURE = new Imperial();
    private static final Orientation DEFAULT_SENS_DU_TOIT = Orientation.FACADE;
    private static final int DEFAULT_ANGLE_TOIT = 15;
    private static final Imperial DEFAULT_EPAISSEUR_MUR = Imperial.fromInches(6);
    private static final Imperial DEFAULT_DISTANCE_MIN = Imperial.fromInches(3);
    private Imperial largeur;
    private Imperial longueur;
    private Imperial hauteur;
    private Imperial deltaRainure;
    private Orientation sensDuToit;
    private int angleToit;
    private Imperial epaisseurMur;
    private final Map<Orientation, Mur> mapMur = new HashMap<>();
    private final Toit toit = new Toit(this);
    private final Pignon pignonDroit = new Pignon(this, true);
    private final Pignon pignonGauche = new Pignon(this, false);
    private final Rallonge rallonge = new Rallonge(this);
    private Imperial distanceMin;

    public Chalet() {
        this.largeur = DEFAULT_LARGEUR;
        this.longueur = DEFAULT_LONGUEUR;
        this.hauteur = DEFAULT_HAUTEUR;
        this.deltaRainure = DEFAULT_DELTA_RAINURE;
        this.sensDuToit = DEFAULT_SENS_DU_TOIT;
        this.angleToit = DEFAULT_ANGLE_TOIT;
        this.epaisseurMur = DEFAULT_EPAISSEUR_MUR;
        this.distanceMin = DEFAULT_DISTANCE_MIN;
        init();
    }

    public Chalet(Imperial largeur, Imperial longueur, Imperial hauteur, Imperial deltaRainure, Orientation sensDuToit, int angleToit, Imperial epaisseurMur, Imperial distanceMin) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.deltaRainure = deltaRainure;
        this.sensDuToit = sensDuToit;
        this.angleToit = angleToit;
        this.epaisseurMur = epaisseurMur;
        this.distanceMin = distanceMin;
        init();
    }

    private void init() {
        mapMur.put(Orientation.FACADE, new Mur(this, Orientation.FACADE));
        mapMur.put(Orientation.ARRIERE, new Mur(this, Orientation.ARRIERE));
        mapMur.put(Orientation.GAUCHE, new Mur(this, Orientation.GAUCHE));
        mapMur.put(Orientation.DROITE, new Mur(this, Orientation.DROITE));
    }

    public Imperial getLargeur() {
        return largeur;
    }

    public void setLargeur(Imperial largeur) {
        this.largeur = largeur;
    }

    public Imperial getDistanceMin() {
        return distanceMin;
    }

    public void setDistanceMin(Imperial distanceMin) {
        this.distanceMin = distanceMin;
    }

    public Imperial getLongueur() {
        return longueur;
    }

    public void setLongueur(Imperial longueur) {
        this.longueur = longueur;
    }

    public Imperial getHauteur() {
        return hauteur;
    }

    public void setHauteur(Imperial hauteur) {
        this.hauteur = hauteur;
    }

    public Imperial getDeltaRainure() {
        return deltaRainure;
    }

    protected void setDeltaRainure(Imperial deltaRainure) {
        this.deltaRainure = deltaRainure;
    }

    public Orientation getSensDuToit() {
        return sensDuToit;
    }

    public void setSensDuToit(Orientation sensDuToit) {
        this.sensDuToit = sensDuToit;
    }

    public int getAngleToit() {
        return angleToit;
    }

    public void setAngleToit(int angleToit) {
        this.angleToit = angleToit;
    }

    public Imperial getEpaisseurMur() {
        return epaisseurMur;
    }
    public void setEpaisseurMur (Imperial epaisseurMur) {
        this.epaisseurMur = epaisseurMur;
    }

    public Map<Orientation, Mur> getMapMur() {
        return mapMur;
    }

    public Pignon getPignonDroit() {
        return pignonDroit;
    }

    public Pignon getPignonGauche() {
        return pignonGauche;
    }

    public Rallonge getRallonge() {
        return rallonge;
    }

    public Toit getToit() {
        return toit;
    }

    public List<Drawable> getComposanteVisible(Vue vue){
        // TODO
        return null;
    }

    public List<Porte> getPortes() {
        List<Porte> portes = new ArrayList<>();
        for (Mur mur : mapMur.values()) {
            for (Accessoire accessoire : mur.getAccessoires()) {
                if (accessoire instanceof Porte) {
                    portes.add((Porte) accessoire);
                }
            }
        }
        return portes;
    }
    public Mur getMurByOrientation(Orientation orientation){
        return mapMur.get(orientation);
    }
}
