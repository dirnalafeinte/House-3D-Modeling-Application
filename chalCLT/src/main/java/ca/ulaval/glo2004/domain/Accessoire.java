package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public abstract class Accessoire {
    Imperial largeur;
    Imperial hauteur;
    Coordonnee coordonnee;

    public Accessoire(Imperial largeur, Imperial hauteur, Coordonnee coordonnee) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.coordonnee = coordonnee;
    }


    public void validate() {

    }
}
