package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;

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
    private Imperial largeur;
    private Imperial longueur;
    private Imperial hauteur;
    private Imperial deltaRainure;
    private Orientation sensDuToit;
    private int angleToit;
    private Imperial epaisseurMur;
    private Map<Orientation, Mur> mapMur;
//    private Pignon pignonDroit;
//    private Pignon pignonGauche;
//    private Rallonge rallonge;

    public Chalet() {
        this.largeur = DEFAULT_LARGEUR;
        this.longueur = DEFAULT_LONGUEUR;
        this.hauteur = DEFAULT_HAUTEUR;
        this.deltaRainure = DEFAULT_DELTA_RAINURE;
        this.sensDuToit = DEFAULT_SENS_DU_TOIT;
        this.angleToit = DEFAULT_ANGLE_TOIT;
        this.epaisseurMur = DEFAULT_EPAISSEUR_MUR;
    }

    public Chalet(Imperial largeur, Imperial longueur, Imperial hauteur, Imperial deltaRainure, Orientation sensDuToit, int angleToit, Imperial epaisseurMur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.deltaRainure = deltaRainure;
        this.sensDuToit = sensDuToit;
        this.angleToit = angleToit;
        this.epaisseurMur = epaisseurMur;
    }


    public Imperial getLargeur() {
        return largeur;
    }

    public void setLargeur(Imperial largeur) {
        this.largeur = largeur;
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
        //setDeltaRainure((epaisseurMur.toInt()/2)); // on doit creer une fonction toImperial()
    }

    public Map<Orientation, Mur> getMapMur() {
        return mapMur;
    }

//    public Pignon getPignonDroit() {
//        return pignonDroit;
//    }
//
//    public Pignon getPignonGauche() {
//        return pignonGauche;
//    }
//
//    public Rallonge getRallonge() {
//        return rallonge;
//    }

    public List<Drawable> getComposanteVisible(Vue vue){

        return null;
    }
}
