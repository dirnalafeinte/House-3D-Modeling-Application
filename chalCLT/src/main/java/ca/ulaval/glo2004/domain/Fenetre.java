package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;

public class Fenetre extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.RED;

    public Fenetre(Chalet chalet, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Mur mur) {
        super(chalet, largeur, hauteur, coordonnee, mur);
    }

    @Override
    protected void setColor() {
        color = DEFAULT_COLOR;
    }

    @Override
    public void calculateSommets() {
        if (EstContenu(mur)) {
            if (getDistanceMinEntre(mur)) {
                sommets_accessoire.clear();
                Coordonnee sommet_1 = new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), coordonnee.getY().add(hauteur.divideBy(2)));
                Coordonnee sommet_2 = new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), coordonnee.getY().add(hauteur.divideBy(2)));
                Coordonnee sommet_3 = new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), coordonnee.getY().substract(hauteur.divideBy(2)));
                Coordonnee sommet_4 = new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), coordonnee.getY().substract(hauteur.divideBy(2)));
                sommets_accessoire.add(sommet_1);
                sommets_accessoire.add(sommet_2);
                sommets_accessoire.add(sommet_3);
                sommets_accessoire.add(sommet_4);
            }
        }
    }
}
