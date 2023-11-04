package ca.ulaval.glo2004.domain;

// A revoir avec anas

import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;

public class Pignon extends Drawable{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private final boolean isPignonDroit;
    public Pignon(Chalet chalet, boolean isPignonDroit) {
        super(chalet);
        this.isPignonDroit = isPignonDroit;
    }

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

    @Override
    public void calculateSommets() {
        // TODO
    }

//   private void calculerHauteurPignon(Orientation sensToit) {
//       if ("GAUCHE".equals(chalet.SensDuToit) || "DROITE".equals(sensToit)){
//           this.hauteurPignon = (Imperial.InchesToImperial( Math.tan(Math.toRadians(chalet.AngleToit))).divide(chalet.Longueur));
//       }
//       if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//           this.hauteurPignon = (Imperial.InchesToImperial( Math.tan(Math.toRadians(chalet.AngleToit))).divide(chalet.Largeur));
//       }
//   }

//   private void calculerLargeurPignon(Orientation sensToit) {
//       if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
//           this.largeurPignon = chalet.Longueur;
//       }
//       if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
//           this.largeurPignon = chalet.Largeur;
//       }
//   }


    public boolean isPignonDroit() {
        return isPignonDroit;
    }
}

