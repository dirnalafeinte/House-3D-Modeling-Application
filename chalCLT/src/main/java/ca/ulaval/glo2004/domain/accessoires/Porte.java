package ca.ulaval.glo2004.domain.accessoires;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.Mur;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalPorteException;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.Objects;

public class Porte extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;
    public Porte(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Chalet chalet, Mur mur) {
        super(largeur, hauteur, coordonnee, AccessoireType.PORTE, chalet, mur);
    }

    public Porte(String id, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Chalet chalet, Mur mur) {
        super(id, largeur, hauteur, coordonnee, AccessoireType.PORTE, chalet, mur);
    }

    @Override
    public void validate() {
        if (!mur.contains(this)) {
            isValid = false;
            throw new IllegalPorteException("La porte doit être dans le mur");
        }
        if (mur.getMinDistance(this).lessThan(chalet.getDistanceMin())) {
            isValid = false;
            throw new IllegalPorteException("La porte doit être à au moins " + chalet.getDistanceMin().toString() + " pouces du mur");
        }
        for (Accessoire accessoire : mur.getAccessoires().stream().filter(accessoire -> !Objects.equals(accessoire.getId(), id)).toList()) {
            if (intersects(accessoire)) {
                isValid = false;
                throw new IllegalPorteException("La porte ne doit pas intersecter un autre accessoire");
            }
            if (getMinDistance(accessoire).lessThan(chalet.getDistanceMin())) {
                isValid = false;
                throw new IllegalPorteException("La porte doit être à au moins " + chalet.getDistanceMin().toString() + " pouces de l'accessoire");
            }
        }
        isValid = true;
    }

    @Override
    public Imperial getLeftEdge() {
        return coordonnee.getX().subtract(largeur.divideBy(2));
    }

    @Override
    public Imperial getRightEdge() {
        return coordonnee.getX().add(largeur.divideBy(2));
    }

    @Override
    public Imperial getTopEdge() {
        return coordonnee.getY().add(hauteur).add(chalet.getDistanceMin());
    }

    @Override
    public Imperial getBottomEdge() {
        return coordonnee.getY().add(chalet.getDistanceMin());
    }

    @Override
    public Color getColor() {
        return isValid ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }
}
