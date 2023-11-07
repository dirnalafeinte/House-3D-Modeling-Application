package ca.ulaval.glo2004.domain;

import java.awt.*;

public class Rallonge extends Drawable {
    private static final Color DEFAULT_COLOR = Color.GREEN;

    public Rallonge(Chalet chalet) {
        super(chalet);
        calculateSommets();
    }

    @Override
    public void calculateSommets() {
        // TODO
    }

    @Override
    public Color getColor() {
        return isValid ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }

//    private void calculerHauteurRallonge(Orientation sensToit) {
//        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//            this.hauteurRallonge =  longueurRallonge.multiply ( Imperial.InchesToImperial( Math.tan(Math.toRadians(chalet.AngleToit))));
//        }
//        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//            this.hauteurRallonge =  longueurRallonge.multiply ( Imperial.InchesToImperial( Math.tan(Math.toRadians(chalet.AngleToit))));
//        }
//    }

//  private void calculerLongueurRallonge(Orientation sensToit) {
//      if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//          this.longueurRallonge = chalet.Largeur;
//      }
//      if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//          this.longueurRallonge = chalet.Longueur;
//      }
//  }
}
