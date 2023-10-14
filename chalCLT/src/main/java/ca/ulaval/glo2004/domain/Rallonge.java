package ca.ulaval.glo2004.domain;

public class Rallonge extends Toit{

    private int longueurRallonge;
    private int hauteurRallonge;
    public Rallonge(int largeur, int longueur, int hauteur, int deltaRainure, int epaisseurMur, int angleToit, String sensToit, int longueurRallonge, int hauteurRallonge) {
        super(largeur, longueur, hauteur, deltaRainure, epaisseurMur,sensToit,angleToit);
        this.longueurRallonge = longueurRallonge;
        this.hauteurRallonge = hauteurRallonge;
    }
    private void calculerLongueurRallonge(String sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.longueurRallonge = this.largeur;
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.longueurRallonge = this.longueur;
        }
    }

    private void calculerHauteurRallonge(String sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.hauteurRallonge = (int) (this.longueur * Math.tan(Math.toRadians(this.angleToit)));
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.hauteurRallonge = (int) (this.largeur * Math.tan(Math.toRadians(this.angleToit)));
        }
    }

    public int getLongueurRallonge() {
        return longueurRallonge;
    }

    public int getHauteurRallonge() {
        return hauteurRallonge;
    }

}
