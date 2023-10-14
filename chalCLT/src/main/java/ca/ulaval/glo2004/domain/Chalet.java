package ca.ulaval.glo2004.domain;

public class Chalet {
    protected int largeur;
    protected int longueur;
    protected int hauteur;
    protected int deltaRainure;
    protected int epaisseurMur;
    protected int angleToit;
    protected String sensToit;

    public Chalet(int largeur, int longueur, int hauteur, int deltaRainure, int epaisseurMur, String sensToit) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.deltaRainure = deltaRainure;
        this.epaisseurMur= epaisseurMur;
        this.sensToit = sensToit;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getDeltaRainure() {
        return deltaRainure;
    }

    private int getEpaisseurMur() {
        return epaisseurMur;
    }

    public String getSensToit() {
        return sensToit;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setEpaisseurMur (int epaisseurMur) {
        this.epaisseurMur = epaisseurMur;
        setDeltaRainure(epaisseurMur/2);

    }

    protected void setDeltaRainure(int deltaRainure) {
        this.deltaRainure = deltaRainure;
    }

    public void setSensToit(String sensToit) {
        this.sensToit = sensToit;
    }

}
