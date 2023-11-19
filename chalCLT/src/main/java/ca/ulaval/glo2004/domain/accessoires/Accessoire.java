package ca.ulaval.glo2004.domain.accessoires;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public abstract class Accessoire extends Drawable {
    protected Imperial largeur;
    protected Imperial hauteur;
    protected Coordonnee coordonnee;
    protected final AccessoireType type;
    protected final Mur mur;

    public Accessoire(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, AccessoireType type, Chalet chalet, Mur mur) {
        super(chalet);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.coordonnee = coordonnee;
        this.type = type;
        this.mur = mur;
        calculateSommets();
    }

    public Accessoire(String id, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, AccessoireType type, Chalet chalet, Mur mur) {
        super(id, chalet);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.coordonnee = coordonnee;
        this.type = type;
        this.mur = mur;
        calculateSommets();
    }

    public Imperial getLargeur() {
        return largeur;
    }

    public Imperial getHauteur() {
        return hauteur;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public Orientation getCote() {
        return mur.getCote();
    }

    public AccessoireType getType() {
        return type;
    }

    public boolean intersects(Accessoire that) {
        boolean isLeft = getCoordonnee().getX().subtract(largeur.divideBy(2)).lessThan(that.getCoordonnee().getX().add(that.getLargeur().divideBy(2)));
        boolean isRight = getCoordonnee().getX().add(largeur.divideBy(2)).greaterThan(that.getCoordonnee().getX().subtract(that.getLargeur().divideBy(2)));
        boolean isAbove = getCoordonnee().getY().add(hauteur.divideBy(2)).greaterThan(that.getCoordonnee().getY().subtract(that.getHauteur().divideBy(2)));
        boolean isBelow = getCoordonnee().getY().subtract(hauteur.divideBy(2)).lessThan(that.getCoordonnee().getY().add(that.getHauteur().divideBy(2)));

        return isLeft && isRight && isAbove && isBelow;
    }

    public Imperial getMinDistance(Accessoire that) {
        Imperial distanceTop = getCoordonnee().getY().add(hauteur.divideBy(2))
                .subtract(that.getCoordonnee().getY().subtract(that.getHauteur().divideBy(2))).abs();
        Imperial distanceBottom = getCoordonnee().getY().subtract(hauteur.divideBy(2))
                .subtract(that.getCoordonnee().getY().add(that.getHauteur().divideBy(2))).abs();
        Imperial distanceLeft = getCoordonnee().getX().subtract(largeur.divideBy(2))
                .subtract(that.getCoordonnee().getX().add(that.getLargeur().divideBy(2))).abs();
        Imperial distanceRight = getCoordonnee().getX().add(largeur.divideBy(2))
                .subtract(that.getCoordonnee().getX().subtract(that.getLargeur().divideBy(2))).abs();

        return Imperial.min(Imperial.min(distanceTop, distanceBottom), Imperial.min(distanceLeft, distanceRight));
    }

    public abstract void validate();
}
