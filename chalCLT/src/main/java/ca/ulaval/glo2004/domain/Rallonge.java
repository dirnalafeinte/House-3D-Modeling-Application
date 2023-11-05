package ca.ulaval.glo2004.domain;

import java.awt.*;

public class Rallonge extends Drawable {
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private final Chalet chalet;

    public Rallonge(Chalet chalet) {
        this.chalet = chalet;
    }

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

    @Override
    public void calculateSommets() {
        // TODO
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
