package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;
import com.sun.org.apache.xpath.internal.operations.Or;

public class ToitSurface {

    private Imperial hauteurToit;
    private Imperial largeurToit;

    private ChaletDTO chalet;
    public ToitSurface(ChaletDTO chalet) {
        calculerHauteurToit(chalet.SensDuToit);
        calculerLongueurToit(chalet.SensDuToit);
        this.chalet = chalet;
    }

    private void calculerHauteurToit(Orientation sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            //this.hauteurToit =  new Imperial(chalet.Longueur.toInt()/(Math.cos(Math.toRadians(chalet.AngleToit))));
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            //
            // this.hauteurToit =  new Imperial(chalet.Largeur.toInt() / Math.cos(Math.toRadians(chalet.AngleToit)));
        }
    }
    private void calculerLongueurToit(Orientation sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.largeurToit = chalet.Largeur;
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.largeurToit = chalet.Longueur;
        }
    }

    public Imperial getHauteurToit() {
        return hauteurToit;
    }

    public Imperial getLargeurToit() {
        return largeurToit;
    }
}
