package ca.ulaval.glo2004.domain;

import java.awt.*;

public class Toit extends Drawable {
    private static final Color DEFAULT_COLOR = Color.GREEN;

    public Toit(Chalet chalet) {
        super(chalet);
        calculateSommets();
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

    @Override
    public Color getColor() {
        return state.isValid() ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }
}
