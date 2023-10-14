package ca.ulaval.glo2004.domain;

public class Toit extends Chalet {

    protected int angleToit;
    public Toit(int largeur, int longueur, int hauteur, int deltaRainure, int epaisseurMur, String sensToit, int angleToit) {
            super(largeur, longueur, hauteur, deltaRainure, epaisseurMur, sensToit);
            this.angleToit = angleToit;
    }

    private int getAngleToit() {
        return angleToit;
    }
    public void setAngleToit(int angleToit) {
        this.angleToit = angleToit;
    }
}