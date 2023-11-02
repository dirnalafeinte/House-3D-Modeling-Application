package ca.ulaval.glo2004.domain;

public class Rallonge {

    private int hauteurRallonge;
    private Chalet chalet;

    public Rallonge(int hauteurRallonge, Chalet chalet) {
        this.hauteurRallonge = hauteurRallonge;
        this.chalet = chalet;
    }

//    private void calculerLongueurRallonge(String sensToit) {
//        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//            this.longueurRallonge = this.largeur;
//        }
//        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//            this.longueurRallonge = this.longueur;
//        }
//    }
//
//    private void calculerHauteurRallonge(String sensToit) {
//        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//            this.hauteurRallonge = (int) (this.longueur * Math.tan(Math.toRadians(this.angleToit)));
//        }
//        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//            this.hauteurRallonge = (int) (this.largeur * Math.tan(Math.toRadians(this.angleToit)));
//        }
//    }

    public int getHauteurRallonge() {
        return hauteurRallonge;
    }

}
