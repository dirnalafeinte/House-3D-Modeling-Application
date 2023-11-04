package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;

public class Toit {

    private Imperial hauteurToit;
    private Imperial largeurToit;

    private ChaletDTO chalet;
    public Toit(ChaletDTO chalet) {
        calculerHauteurToit(chalet.SensDuToit);
        calculerLongueurToit(chalet.SensDuToit);
        this.chalet = chalet;
    }

    private void calculerHauteurToit(Orientation sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.hauteurToit =  chalet.Longueur.divide(Imperial.InchesToImperial(Math.cos(Math.toRadians(chalet.AngleToit))));
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.hauteurToit =  chalet.Largeur.divide ( Imperial.InchesToImperial( Math.cos(Math.toRadians(chalet.AngleToit))));
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
