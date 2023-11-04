package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.RED;

    public Fenetre(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Mur mur) {
        super(largeur, hauteur, coordonnee, mur);
    }

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

    @Override
    public void calculateSommets() {
        sommets.clear();
        calculateSommetsAccessoire();
    }

    private void calculateSommetsAccessoire() {
        List<Coordonnee> sommetsAccessoire = new ArrayList<>();
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), coordonnee.getY().add(hauteur.divideBy(2))));
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), coordonnee.getY().add(hauteur.divideBy(2))));
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), coordonnee.getY().substract(hauteur.divideBy(2))));
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), coordonnee.getY().substract(hauteur.divideBy(2))));
        sommets.put(getCote().toVue(), sommetsAccessoire);
    }

    private Orientation getCote() {
        return mur.getCote();
    }
}
