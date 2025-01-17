package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.accessoires.Accessoire;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chalet implements Serializable {
    private static final Imperial DEFAULT_LARGEUR = Imperial.fromFeet(10);
    private static final Imperial DEFAULT_LONGUEUR = Imperial.fromFeet(10);
    private static final Imperial DEFAULT_HAUTEUR = Imperial.fromFeet(8);
    private static final Imperial DEFAULT_DELTA_RAINURE = new Imperial();
    private static final Orientation DEFAULT_SENS_DU_TOIT = Orientation.FACADE;
    private static final double DEFAULT_ANGLE_TOIT = 75.0;
    private static final Imperial DEFAULT_EPAISSEUR_MUR = Imperial.fromInches(6);
    private static final Imperial DEFAULT_DISTANCE_MIN = Imperial.fromInches(3);
    private Imperial largeur;
    private Imperial longueur;
    private Imperial hauteur;
    private Imperial deltaRainure;
    private Orientation sensDuToit;
    private double angleToit;
    private Imperial epaisseurMur;
    private final Map<Orientation, Mur> mursByOrientation = new HashMap<>();
    private final Map<Orientation, Mur> mursByVue = new HashMap<>();
    protected Toit toit;
    protected Pignon pignonDroit;
    protected Pignon pignonGauche;
    protected Rallonge rallonge;
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
        mursByOrientation.put(Orientation.FACADE, new Mur(this, Orientation.FACADE));
        mursByOrientation.put(Orientation.ARRIERE, new Mur(this, Orientation.ARRIERE));
        mursByOrientation.put(Orientation.GAUCHE, new Mur(this, Orientation.GAUCHE));
        mursByOrientation.put(Orientation.DROITE, new Mur(this, Orientation.DROITE));
        this.rallonge = new Rallonge(this);
        this.toit = new Toit(this);
        this.pignonDroit = new Pignon(this, true);
        this.pignonGauche = new Pignon(this, false);
    }

    void recalculerChalet(Imperial longueur, Imperial largeur, Imperial hauteur, Imperial epaisseur, Imperial deltaRainure, Imperial distanceMin, double angleToit, Orientation sensDuToit) {
        Imperial ratioLargeur = largeur.divide(this.largeur);
        Imperial ratioHauteur = hauteur.divide(this.hauteur);
        Imperial ratioLongueur = longueur.divide(this.longueur);

        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.epaisseurMur = epaisseur;
        this.deltaRainure = deltaRainure;
        this.distanceMin = distanceMin;
        this.angleToit = angleToit;
        this.sensDuToit = sensDuToit;

        for (Mur mur : getMurs()) {
            mur.calculateSommets();

            for (Accessoire accessoire : mur.getAccessoires()) {
                Imperial coordPrecendenteX = accessoire.getCoordonnee().getX();
                Imperial coordPrecendentey = accessoire.getCoordonnee().getY();

                Imperial newCoordX;
                Imperial newCoordY;

                if (mur.getCote().equals(Orientation.FACADE) || mur.getCote().equals(Orientation.ARRIERE)) {
                    newCoordX = coordPrecendenteX.multiply(ratioLongueur);
                } else {
                    newCoordX = coordPrecendenteX.multiply(ratioLargeur);
                }
                newCoordY = coordPrecendentey.multiply(ratioHauteur);

                accessoire.updateCoordonnee(newCoordX, newCoordY);
            }
            mur.getAccessoires().forEach(Accessoire::validate);
        }

        this.toit.calculerDimensionToit(this.sensDuToit);
        this.pignonDroit.calculerDimensionPignon(this.sensDuToit);
        this.pignonGauche.calculerDimensionPignon(this.sensDuToit);
        this.rallonge.calculerDimensionRallonge(this.sensDuToit);

        this.toit.calculateSommets();
        this.pignonDroit.calculateSommets();
        this.pignonGauche.calculateSommets();
        this.rallonge.calculateSommets();
    }

    public List<Drawable> getVisibleComponents(Vue vue) {
        List<Drawable> components = new ArrayList<>();

        getMurs().stream().filter(mur -> mur.isVisible(vue)).toList().forEach(mur -> {
            components.add(mur);
            components.addAll(mur.getAccessoires());
        });
        if (toit.isVisible(vue)) components.add(toit);
        if (pignonDroit.isVisible(vue)) components.add(pignonDroit);
        if (pignonGauche.isVisible(vue)) components.add(pignonGauche);
        if (rallonge.isVisible(vue)) components.add(rallonge);

        return components;
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

    public double getAngleToit() {
        return angleToit;
    }

    public void setAngleToit(int angleToit) {
        this.angleToit = angleToit;
    }

    public Imperial getEpaisseurMur() {
        return epaisseurMur;
    }

    public void setEpaisseurMur(Imperial epaisseurMur) {
        this.epaisseurMur = epaisseurMur;
    }

    public List<Mur> getMurs() {
        return mursByOrientation.values().stream().toList();
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

    public Mur getMurByOrientation(Orientation orientation) {
        return mursByOrientation.get(orientation);
    }
}
