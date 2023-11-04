package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;

public class Fenetre extends Accessoire{
    private static final Color DEFAULT_COLOR = Color.RED;
    public Fenetre(Chalet chalet, Imperial largeur, Imperial hauteur, Coordonnee coordonnee) {
        super(chalet, largeur, hauteur, coordonnee);
    }

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

    @Override
    public void calculateSommets() {

    }
}
