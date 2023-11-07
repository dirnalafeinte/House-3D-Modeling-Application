package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.error.exceptions.IllegalFenetreException;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalPorteException;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.GREEN;

    public Fenetre(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Chalet chalet, Mur mur) {
        super(largeur, hauteur, coordonnee, chalet, mur);
    }

    public Fenetre(String id, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Chalet chalet, Mur mur) {
        super(id, largeur, hauteur, coordonnee, chalet, mur);
    }

    @Override
    public void validate() {
        if (!mur.contains(this)) {
            isValid = false;
            throw new IllegalPorteException("La porte doit être sur le mur");
        }
        if (mur.getMinDistance(this).lessThan(chalet.getDistanceMin())) {
            isValid = false;
            throw new IllegalFenetreException("La porte doit être à au moins " + chalet.getDistanceMin().toString() + " pouces du mur");
        }
        for (Accessoire accessoire : mur.getAccessoires()) {
            if (intersects(accessoire)) {
                isValid = false;
                throw new IllegalFenetreException("La porte ne doit pas intersecter un autre accessoire");
            }
            if (getMinDistance(accessoire).lessThan(chalet.getDistanceMin())) {
                isValid = false;
                throw new IllegalFenetreException("La porte doit être à au moins " + chalet.getDistanceMin().toString() + " pouces de l'accessoire");
            }
        }
        isValid = true;
    }

    @Override
    public void calculateSommets() {
        sommets.clear();
        calculateSommetsAccessoire();
    }

    @Override
    public Color getColor() {
        return isValid ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }

    private void calculateSommetsAccessoire() {
        List<Coordonnee> sommetsAccessoire = new ArrayList<>();
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().subtract(largeur.divideBy(2)), chalet.getHauteur().subtract(coordonnee.getY().add(hauteur.divideBy(2)))));
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), chalet.getHauteur().subtract(coordonnee.getY().add(hauteur.divideBy(2)))));
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), chalet.getHauteur().subtract(coordonnee.getY().subtract(hauteur.divideBy(2)))));
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().subtract(largeur.divideBy(2)), chalet.getHauteur().subtract(coordonnee.getY().subtract(hauteur.divideBy(2)))));
        sommets.put(getCote().toVue(), sommetsAccessoire);
    }

    public Orientation getCote() {
        return mur.getCote();
    }
}
