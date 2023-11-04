package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;

public class Rallonge {

    private Imperial hauteurRallonge;
    private Imperial longueurRallonge;

    private ChaletDTO chalet;

    public Rallonge(Imperial hauteurRallonge, Imperial longueurRallonge, ChaletDTO chalet) {
        calculerHauteurRallonge(chalet.SensDuToit);
        calculerLongueurRallonge(chalet.SensDuToit);
        this.chalet = chalet;
    }

    private void calculerLongueurRallonge(Orientation sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.longueurRallonge = chalet.Largeur;
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.longueurRallonge = chalet.Longueur;
        }
    }

    private void calculerHauteurRallonge(Orientation sensToit) {
        if ("GAUCHE".equals(sensToit) || "DROITE".equals(sensToit)){
            this.hauteurRallonge =  longueurRallonge.multiply ( Imperial.InchesToImperial( Math.tan(Math.toRadians(chalet.AngleToit))));
        }
        if ("FACADE".equals(sensToit) || "ARRIERE".equals(sensToit)){
            this.hauteurRallonge =  longueurRallonge.multiply ( Imperial.InchesToImperial( Math.tan(Math.toRadians(chalet.AngleToit))));
        }
    }

    public Imperial getHauteurRallonge() {
        return hauteurRallonge;
    }

}
