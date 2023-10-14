package ca.ulaval.glo2004.domain;

public class Pignon extends Toit{

    protected int largeurPignon;
    protected int hauteurPignon;
    public Pignon(int largeur, int longueur, int hauteur, int deltaRainure, int epaisseurMur, int angleToit, String sensToit, int largeurPignon, int hauteurPignon) {
            super(largeur, longueur, hauteur, deltaRainure, epaisseurMur,sensToit,angleToit);
            this.largeurPignon = largeurPignon;
            this.hauteurPignon = hauteurPignon;
    }

    private void calculerHauteurPignon() {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.hauteurPignon = (int) (Math.tan(Math.toRadians(this.angleToit))/this.longueur);
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.hauteurPignon = (int) (Math.tan(Math.toRadians(this.angleToit))/this.largeur);
        }
    }

    private void calculerLargeurPignon(String sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.largeurPignon = this.longueur;
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.largeurPignon = this.largeur;
        }
    }

    public int getHauteurPignon() {
        return hauteurPignon;
    }

    public int getLargeurPignon() {
        return largeurPignon;
    }
}

