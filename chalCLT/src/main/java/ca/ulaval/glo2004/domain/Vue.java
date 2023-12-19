package ca.ulaval.glo2004.domain;

import java.io.Serializable;

public enum Vue implements Serializable {
    PLAN,
    FACADE,
    GAUCHE,
    ARRIERE,
    DROITE;

    public Orientation toOrientation() {
        return switch (this) {
            case PLAN:
                throw new IllegalArgumentException("Impossible de convertir la vue plan en orientation");
            case FACADE:
                yield Orientation.FACADE;
            case ARRIERE:
                yield Orientation.ARRIERE;
            case GAUCHE:
                yield Orientation.GAUCHE;
            case DROITE:
                yield Orientation.DROITE;
        };
    }
}
