package ca.ulaval.glo2004.domain;

import java.awt.*;

public class Toit extends Drawable{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private final Chalet chalet;
    public Toit(Chalet chalet) {
        this.chalet = chalet;
    }

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

//    private void calculerHauteurToit(Orientation sensToit) {
//        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//            this.hauteurToit =  chalet.Longueur.divide(Imperial.InchesToImperial(Math.cos(Math.toRadians(chalet.AngleToit))));
//        }
//        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//            this.hauteurToit =  chalet.Largeur.divide ( Imperial.InchesToImperial( Math.cos(Math.toRadians(chalet.AngleToit))));
//        }
//    }

//    private void calculerLongueurToit(Orientation sensToit) {
//        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//            this.largeurToit = chalet.Largeur;
//        }
//        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//            this.largeurToit = chalet.Longueur;
//        }
//    }

    @Override
    public void calculateSommets() {
        // TODO
    }
}
