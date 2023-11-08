package ca.ulaval.glo2004.domain.accessoire;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.Mur;
import ca.ulaval.glo2004.domain.accessoire.Accessoire;
import ca.ulaval.glo2004.domain.accessoire.AccessoireType;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalFenetreException;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
            throw new IllegalFenetreException("La fenêtre doit être dans le mur");
        }
        if (mur.getMinDistance(this).lessThan(chalet.getDistanceMin())) {
            isValid = false;
            throw new IllegalFenetreException("La fenêtre doit être à au moins " + chalet.getDistanceMin().toString() + " pouces du mur");
        }
        for (Accessoire accessoire : mur.getAccessoires()) {
            if (intersects(accessoire)) {
                isValid = false;
                throw new IllegalFenetreException("La fenêtre ne doit pas intersecter un autre accessoire");
            }
            if (getMinDistance(accessoire).lessThan(chalet.getDistanceMin())) {
                isValid = false;
                throw new IllegalFenetreException("La fenêtre doit être à au moins " + chalet.getDistanceMin().toString() + " pouces de l'accessoire");
            }
        }
        isValid = true;
    }

    @Override
    public void calculateSommets() {
        sommetsByVue.clear();
        calculateSommetsAccessoire();
    }

    @Override
    public Color getColor() {
        return isValid ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }

    private void calculateSommetsAccessoire() { // Pour une porte, on prend en compte seulement la position en x, le reste est fixe.
        List<Coordonnee> sommetsAccessoire = new ArrayList<>();
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().subtract(largeur.divideBy(2)), chalet.getHauteur())); // la porte est fixe au sol du mur
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), chalet.getHauteur())); // la porte est fixe au sol du mur
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), coordonnee.getY().subtract(hauteur))); // y est fixe dans le UI
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().subtract(largeur.divideBy(2)), coordonnee.getY().subtract(hauteur))); // y est fixe dans le UI
        sommetsByVue.put(getCote().toVue(), sommetsAccessoire);
    }
}
