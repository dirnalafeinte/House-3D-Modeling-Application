package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import java.util.List;

public abstract class Accessoire extends Drawable {
    protected Imperial largeur;
    protected Imperial hauteur;
    protected Coordonnee coordonnee;
    protected final Mur mur;

    public Accessoire(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Mur mur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.coordonnee = coordonnee;
        this.mur = mur;
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

    public Mur getMur() {
        return mur;
    }

    public abstract void validate();
}
