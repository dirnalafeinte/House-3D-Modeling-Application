package ca.ulaval.glo2004.domain;


import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Accessoire {
    private static final Color DEFAULT_COLOR = Color.RED;

    public Fenetre(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Chalet chalet, Mur mur) {
        super(largeur, hauteur, coordonnee, chalet, mur);
        validate();
    }

    @Override
    public void validate() {
//        if(!this.estContenu(mur.getCote().toVue(), mur)){
//            throw new IllegalFenetreException("La fenêtre doit être contenue dans le mur");
//        }
//        if(getDistanceMinEntre(mur).lessThan(chalet.getDistanceMin())){
//            throw new IllegalFenetreException("La fenêtre doit être à au moins " + chalet.getDistanceMin().toString() + " pouces du mur");
//        }
//        for (Accessoire accessoire : mur.getAccessoires()) {
//            if(this.estContenu(mur.getCote().toVue(), accessoire)){
//                throw new IllegalFenetreException("La fenêtre ne doit pas être contenue dans un autre accessoire");
//            }
//            if(getDistanceMinEntre(accessoire).lessThan(chalet.getDistanceMin())){
//                throw new IllegalFenetreException("La fenêtre doit être à au moins " + chalet.getDistanceMin().toString() + " pouces de l'accessoire");
//            }
//        }
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
