package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public class Fenetre extends Accessoire{
    public Fenetre(Imperial largeur, Imperial hauteur, Coordonnee coordonnee) {
        super(largeur, hauteur, coordonnee);
    }
}
