package ca.ulaval.glo2004.domain;

// A revoir avec anas

import ca.ulaval.glo2004.domain.util.Imperial;

public class Pignon {
    private ChaletDTO chalet;
    private Imperial largeurPignon;
    private Imperial hauteurPignon;
    public Pignon(ChaletDTO chalet,  int largeurPignon, int hauteurPignon) {
        this.chalet = chalet;
        calculerLargeurPignon(chalet.SensDuToit);
        calculerHauteurPignon(chalet.SensDuToit);
    }

   private void calculerHauteurPignon(Orientation sensToit) {
       if ("GAUCHE".equals(chalet.SensDuToit) || "DROITE".equals(sensToit)){
           this.hauteurPignon = new Imperial (Math.tan(Math.toRadians(chalet.AngleToit))/chalet.Longueur.toInt());
       }
       if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
           this.hauteurPignon =  new Imperial(Math.tan(Math.toRadians(chalet.AngleToit))/chalet.Largeur.toInt());
       }
   }
   private void calculerLargeurPignon(Orientation sensToit) {
       if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
           this.largeurPignon = chalet.Longueur;
       }
       if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
           this.largeurPignon = chalet.Largeur;
       }
   }

    public Imperial getHauteurPignon() {
        return hauteurPignon;
    }

    public Imperial getLargeurPignon() {
        return largeurPignon;
    }
}

