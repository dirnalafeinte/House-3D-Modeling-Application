package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;

public class Porte extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

    public Porte(Chalet chalet, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Mur mur) {
        super(largeur, hauteur, coordonnee, mur);
    }

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

    @Override
    public void calculateSommets() {

    }
}
