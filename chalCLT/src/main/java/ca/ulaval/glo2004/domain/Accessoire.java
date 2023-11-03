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

    public Imperial getLargeur() {
        return largeur;
    }

    public void setLargeur(Imperial largeur) {
        this.largeur = largeur;
    }

    public Imperial getHauteur() {
        return hauteur;
    }

    public void setHauteur(Imperial hauteur) {
        this.hauteur = hauteur;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    public void validate() {

    }
}
