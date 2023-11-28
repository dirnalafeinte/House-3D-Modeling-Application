package ca.ulaval.glo2004.domain.accessoires;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.List;

public abstract class Accessoire extends Drawable {
    protected Imperial largeur;
    protected Imperial hauteur;
    protected Coordonnee coordonnee;
    public abstract void updateCoordonnees();
    protected final AccessoireType type;
    protected final Mur mur;

    public Accessoire(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, AccessoireType type, Chalet chalet, Mur mur) {
        super(chalet);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.coordonnee = coordonnee;
        this.type = type;
        this.mur = mur;
        calculateSommets();
    }

    public Accessoire(String id, Imperial largeur, Imperial hauteur, Coordonnee coordonnee, AccessoireType type, Chalet chalet, Mur mur) {
        super(id, chalet);
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.coordonnee = coordonnee;
        this.type = type;
        this.mur = mur;
        calculateSommets();
    }

    public Imperial getLargeur() {
        return largeur;
    }

    public Imperial getHauteur() {
        return hauteur;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public Orientation getCote() {
        return mur.getCote();
    }

    public AccessoireType getType() {
        return type;
    }

    public boolean intersects(Accessoire that) {
        boolean isRight = getRightEdge().lessThan(that.getLeftEdge());
        boolean isLeft = getLeftEdge().greaterThan(that.getRightEdge());
        boolean isAbove = getTopEdge().greaterThan(that.getBottomEdge());
        boolean isBelow = getBottomEdge().lessThan(that.getTopEdge());

        return isLeft && isRight && isAbove && isBelow;
    }

    public Imperial getMinDistance(Accessoire that) {
        Imperial distanceTop = getTopEdge().subtract(that.getBottomEdge()).abs();
        Imperial distanceBottom = getBottomEdge().subtract(that.getTopEdge()).abs();
        Imperial distanceLeft = getLeftEdge().subtract(that.getRightEdge()).abs();
        Imperial distanceRight = getRightEdge().subtract(that.getLeftEdge()).abs();

        return Imperial.min(Imperial.min(distanceTop, distanceBottom), Imperial.min(distanceLeft, distanceRight));
    }

    public abstract void validate();

    public abstract Imperial getLeftEdge();

    public abstract Imperial getRightEdge();

    public abstract Imperial getTopEdge();

    public abstract Imperial getBottomEdge();

    @Override
    public void calculateSommets() {
        sommetsByVue.clear();
        calculateSommetsAccessoire();
    }

    private void calculateSommetsAccessoire() {
        List<Coordonnee> sommetsAccessoire = new ArrayList<>();
        sommetsAccessoire.add(new Coordonnee(getLeftEdge(), chalet.getHauteur().subtract(getTopEdge())));
        sommetsAccessoire.add(new Coordonnee(getRightEdge(), chalet.getHauteur().subtract(getTopEdge())));
        sommetsAccessoire.add(new Coordonnee(getRightEdge(), chalet.getHauteur().subtract(getBottomEdge())));
        sommetsAccessoire.add(new Coordonnee(getLeftEdge(), chalet.getHauteur().subtract(getBottomEdge())));
        sommetsByVue.put(getCote().toVue(), sommetsAccessoire);
    }

//    public void updateCoordonnees(Imperial newLargeurMur, Imperial newHauteurMur) {
//
//        Imperial ratioLargeur = newLargeurMur.divide(largeur);
//        Imperial ratioHauteur = newHauteurMur.divide(hauteur);
//
//        Imperial newCoordX = coordonnee.getX().multiply(ratioLargeur);
//        Imperial newCoordY = coordonnee.getY().multiply(ratioHauteur);
//
//        Imperial deltaX = newCoordX.subtract(coordonnee.getX());
//        Imperial deltaY = newCoordY.subtract(coordonnee.getY());
//
//        coordonnee = new Coordonnee(newCoordX, newCoordY);
//        moveBy(deltaX, deltaY);
//
//        calculateSommets();
//    }


    private void moveBy(Imperial deltaX, Imperial deltaY) {
        sommetsByVue.forEach((vue, sommets) -> {
            List<Coordonnee> newSommets = new ArrayList<>();
            for (Coordonnee sommet : sommets) {
                Imperial newCoordX = sommet.getX().add(deltaX);
                Imperial newCoordY = sommet.getY().add(deltaY);
                newSommets.add(new Coordonnee(newCoordX, newCoordY));
            }
            sommets.clear();
            sommets.addAll(newSommets);
        });
    }

}


