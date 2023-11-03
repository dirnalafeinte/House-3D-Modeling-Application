package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.List;
import java.util.Map;

public class Chalet {
    private Imperial largeur;
    private Imperial longueur;
    private Imperial hauteur;
    private Imperial deltaRainure;
    private Orientation sensDuToit;
    private int angleToit;
    private Imperial epaisseurMur;
    private Map<Orientation, Mur> mapMur;
    private Pignon pignonDroit;
    private Pignon pignonGauche;
    private Rallonge rallonge;

    public Chalet(Imperial largeur, Imperial longueur, Imperial hauteur, Imperial deltaRainure, Orientation sensDuToit, int angleToit, Imperial epaisseurMur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.deltaRainure = deltaRainure;
        this.sensDuToit = sensDuToit;
        this.angleToit = angleToit;
        this.epaisseurMur = epaisseurMur;
    }

    public Chalet(){
        this.largeur = new Imperial(10);
        this.longueur = new Imperial(10);
        this.hauteur = new Imperial(8);
        this.deltaRainure = new Imperial(0);
        this.sensDuToit = Orientation.FACADE;
        this.angleToit = 45;
        this.epaisseurMur = new Imperial(3);
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

    public List<Drawable> getComposanteVisible(Vue vue){

        return null;
    }
}
