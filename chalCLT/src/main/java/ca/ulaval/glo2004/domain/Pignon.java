package ca.ulaval.glo2004.domain;

// A revoir avec anas

public class Pignon {
    private Chalet chalet;
    private boolean estPignonDroit;
    private int largeurPignon;
    private int hauteurPignon;
    public Pignon(Chalet chalet, boolean estPignonDroit, int largeurPignon, int hauteurPignon) {
        this.chalet = chalet;
        this.estPignonDroit = estPignonDroit;
        this.largeurPignon = largeurPignon;
        this.hauteurPignon = hauteurPignon;
    }

//    private void calculerHauteurPignon() {
//        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//            this.hauteurPignon = (int) (Math.tan(Math.toRadians(this.angleToit))/this.longueur);
//        }
//        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//            this.hauteurPignon = (int) (Math.tan(Math.toRadians(this.angleToit))/this.largeur);
//        }
//    }
//
//    private void calculerLargeurPignon(String sensToit) {
//        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//            this.largeurPignon = this.longueur;
//        }
//        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//            this.largeurPignon = this.largeur;
//        }
//    }

    public int getHauteurPignon() {
        return hauteurPignon;
    }

    public int getLargeurPignon() {
        return largeurPignon;
    }
}

