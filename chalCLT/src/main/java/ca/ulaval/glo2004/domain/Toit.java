package ca.ulaval.glo2004.domain;

import java.awt.*;

public class Toit extends Drawable{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    public Toit(Chalet chalet) {
        super(chalet);
    }

    //private void calculerHauteurToit(Orientation sensToit) {
    //    if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
    //        this.hauteurToit =  chalet.Longueur.divide(Imperial.DoubleToFeetAndInchesAndFractions(Math.cos(Math.toRadians(chalet.AngleToit))));
    //    }
    //    if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
    //        this.hauteurToit =  chalet.Largeur.divide ( Imperial.DoubleToFeetAndInchesAndFractions( Math.cos(Math.toRadians(chalet.AngleToit))));
    //    }
    //}

    //private void calculerLongueurToit(Orientation sensToit) {
    //    if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
    //        this.largeurToit = chalet.Largeur;
    //    }
    //    if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
    //        this.largeurToit = chalet.Longueur;
    //    }
    //}

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

    @Override
    public void calculateSommets() {

    }
}
