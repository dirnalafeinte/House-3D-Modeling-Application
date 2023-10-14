package ca.ulaval.glo2004.domain;

public class ToitSurface extends Toit{

    private int hauteurToit;
    private int largeurToit;
    public ToitSurface(int largeur, int longueur, int hauteur, int deltaRainure, int epaisseurMur, String sensToit,int angleToit, int hauteurToit, int largeurToit) {
        super(largeur, longueur, hauteur, deltaRainure, epaisseurMur, sensToit, angleToit);
        this.hauteurToit = hauteurToit;
        this.largeurToit = largeurToit;
    }

    private void calculerHauteurToit(String sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.hauteurToit = (int) (this.longueur / Math.cos(Math.toRadians(this.angleToit)));
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.hauteurToit = (int) (this.largeur / Math.cos(Math.toRadians(this.angleToit)));
        }
    }
    private void calculerLongueurToit(String sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.largeurToit = this.largeur;
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.largeurToit = this.longueur;
        }
    }

    public int getHauteurToit() {
        return hauteurToit;
    }

    public int getLongueurToit() {
        return largeurToit;
    }
}
