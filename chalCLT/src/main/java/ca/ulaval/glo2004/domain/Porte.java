package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.error.exceptions.IllegalPorteException;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Porte extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;
    public Porte(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Chalet chalet, Mur mur) {
        super(largeur, hauteur, coordonnee, chalet, mur);
    }

    public Porte(String id, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Chalet chalet, Mur mur) {
        super(largeur, hauteur, coordonnee, chalet, mur);
    }

    @Override
    public void validate() {
        if(!this.estContenu(mur.getCote().toVue(), mur)){
            throw new IllegalPorteException("La porte doit être contenue dans le mur");
        }
//        if(getDistanceMinEntre(mur).lessThan(chalet.getDistanceMin())){
//            throw new IllegalPorteException("La porte doit être à au moins " + chalet.getDistanceMin().toString() + " pouces du mur");
//        }
        for (Accessoire accessoire : mur.getAccessoires()) {
            if(this.estContenu(mur.getCote().toVue(), accessoire)){
                throw new IllegalPorteException("La porte ne doit pas être contenue dans un autre accessoire");
            }
//            if(getDistanceMinEntre(accessoire).lessThan(chalet.getDistanceMin())){
//                throw new IllegalPorteException("La porte doit être à au moins " + chalet.getDistanceMin().toString() + " pouces de l'accessoire");
//            }
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
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), chalet.getHauteur())); // la porte est fixe au sol du mur
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), chalet.getHauteur())); // la porte est fixe au sol du mur
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().add(largeur.divideBy(2)), coordonnee.getY().substract(hauteur))); // y est fixe dans le UI
        sommetsAccessoire.add(new Coordonnee(coordonnee.getX().substract(largeur.divideBy(2)), coordonnee.getY().substract(hauteur))); // y est fixe dans le UI
        sommets.put(getCote().toVue(), sommetsAccessoire);
    }
}
