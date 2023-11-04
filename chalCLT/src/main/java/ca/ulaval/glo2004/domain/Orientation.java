package ca.ulaval.glo2004.domain;

public enum Orientation {
    FACADE,
    ARRIERE,
    GAUCHE,
    DROITE;

    public Orientation getOpposite() {
        return switch (this) {
            case FACADE:
                yield ARRIERE;
            case ARRIERE:
                yield FACADE;
            case GAUCHE:
                yield DROITE;
            case DROITE:
                yield GAUCHE;
        };
    }
}
