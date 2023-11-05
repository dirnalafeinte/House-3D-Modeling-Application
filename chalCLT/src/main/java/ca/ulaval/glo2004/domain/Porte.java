package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Porte extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

    public Porte(Chalet chalet, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Mur mur) {
        super(largeur, hauteur, coordonnee, mur);
        validate();
    }

    @Override
    public void validate() {
        if(!this.estContenu(mur)){
            throw new IllegalArgumentException("La porte doit être contenue dans le mur");
        }
        if(getDistanceMinEntre(mur).lessThan(mur.getChalet().getDistanceMin())){
            throw new IllegalArgumentException("La porte doit être à au moins " + mur.getChalet().getDistanceMin().toString() + " pouces du mur");
        }
        for (Accessoire accessoire : mur.getAccessoires()) {
            if(this.estContenu(accessoire)){
                throw new IllegalArgumentException("La porte ne doit pas être contenue dans un autre accessoire");
            }
            if(getDistanceMinEntre(accessoire).lessThan(mur.getChalet().getDistanceMin())){
                throw new IllegalArgumentException("La porte doit être à au moins " + mur.getChalet().getDistanceMin().toString() + " pouces de l'accessoire");
            }
        }
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

    private void calculateSommetsAccessoire() { // Pour une porte, on prend en compte seulement la position en x, le reste est fixe.
        List<Coordonnee> sommetsAccessoire = new ArrayList<>();
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), mur.getChalet().getHauteur())); // la porte est fixe au sol du mur
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), mur.getChalet().getHauteur())); // la porte est fixe au sol du mur
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), coordonnee.getY().substract(hauteur.divideBy(2)))); // y est fixe dans le UI
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), coordonnee.getY().substract(hauteur.divideBy(2)))); // y est fixe dans le UI
        sommets.put(getCote().toVue(), sommetsAccessoire);
    }

    private Orientation getCote() {
        return mur.getCote();
    }

}
