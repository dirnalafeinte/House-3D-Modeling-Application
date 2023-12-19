package ca.ulaval.glo2004.domain;

import java.io.Serializable;

public enum Orientation implements Serializable {
    FACADE,
    ARRIERE,
    GAUCHE,
    DROITE;

    public Orientation getOpposite() {
        return getOrientation(ARRIERE, FACADE, DROITE, GAUCHE);
    }

    public Orientation getGauche() {
        return getOrientation(GAUCHE, DROITE, ARRIERE, FACADE);
    }

    public Orientation getDroite() {
        return getOrientation(DROITE, GAUCHE, FACADE, ARRIERE);
    }

    public Vue toVue() {
        return switch (this) {
            case FACADE:
                yield Vue.FACADE;
            case ARRIERE:
                yield Vue.ARRIERE;
            case GAUCHE:
                yield Vue.GAUCHE;
            case DROITE:
                yield Vue.DROITE;
        };
    }

    private Orientation getOrientation(Orientation orientation, Orientation orientation2, Orientation orientation3, Orientation orientation4) {
        return switch (this) {
            case FACADE:
                yield orientation;
            case ARRIERE:
                yield orientation2;
            case GAUCHE:
                yield orientation3;
            case DROITE:
                yield orientation4;
        };
    }

}
