package ca.ulaval.glo2004.domain.accessoires;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Accessoire extends Drawable {
    protected Imperial largeur;
    protected Imperial hauteur;
    protected Coordonnee coordonnee;
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
        boolean isLeft = getLeftEdge().lessThan(that.getRightEdge());
        boolean isRight = getRightEdge().greaterThan(that.getLeftEdge());
        boolean isAbove = getTopEdge().greaterThan(that.getBottomEdge());
        boolean isBelow = getBottomEdge().lessThan(that.getTopEdge());

        return isLeft && isRight && isAbove && isBelow;
    }

    public Imperial getMinDistance(Accessoire that) {
        Imperial minDistance = Imperial.MAX_VALUE;
        for (Coordonnee corner : getSommets()) {
            Imperial temp = that.calculateMinDistance(corner);
            if (temp.lessThan(minDistance)) {
                minDistance = temp;
            }
        }
        for (Coordonnee corner : that.getSommets()) {
            Imperial temp = calculateMinDistance(corner);
            if (temp.lessThan(minDistance)) {
                minDistance = temp;
            }
        }
        return minDistance;
    }

    private Imperial calculateMinDistance(Coordonnee point) {
        Imperial topDistance = getTopEdge().subtract(point.getY()).abs();
        Imperial bottomDistance = getBottomEdge().subtract(point.getY()).abs();
        Imperial leftDistance = getLeftEdge().subtract(point.getX()).abs();
        Imperial rightDistance = getRightEdge().subtract(point.getX()).abs();

        if (getLeftEdge().lessThan(point.getX()) && point.getX().lessThan(getRightEdge())) {
            return Imperial.min(topDistance, bottomDistance);
        } else if (getBottomEdge().lessThan(point.getY()) && point.getY().lessThan(getTopEdge())) {
            return Imperial.min(leftDistance, rightDistance);
        } else {
            Imperial cornerY = topDistance.lessThan(bottomDistance) ? getTopEdge() : getBottomEdge();
            Imperial cornerX = leftDistance.lessThan(rightDistance) ? getLeftEdge() : getRightEdge();
            Imperial xDistance = cornerX.subtract(point.getX());
            Imperial yDistance = cornerY.subtract(point.getY());
            return xDistance.pow(2).add(yDistance.pow(2)).sqrt();
        }
    }
    public abstract void validate();

    public abstract Imperial getLeftEdge();

    public abstract Imperial getRightEdge();

    public abstract Imperial getTopEdge();

    public abstract Imperial getBottomEdge();

    public Coordonnee getTopLeftCorner() {
        return new Coordonnee(getLeftEdge(), getTopEdge());
    }

    public Coordonnee getTopRightCorner() {
        return new Coordonnee(getRightEdge(), getTopEdge());
    }

    public Coordonnee getBottomLeftCorner() {
        return new Coordonnee(getLeftEdge(), getBottomEdge());
    }

    public Coordonnee getBottomRightCorner() {
        return new Coordonnee(getRightEdge(), getBottomEdge());
    }

    public Collection<Coordonnee> getSommets() {
        return List.of(getTopLeftCorner(), getTopRightCorner(), getBottomRightCorner(), getBottomLeftCorner());
    }

    @Override
    public void calculateSommets() {
        sommetsByVue.clear();
        calculateSommetsAccessoire();
    }

    private void calculateSommetsAccessoire() {
        List<Coordonnee> sommetsAccessoire = new ArrayList<>();
        sommetsAccessoire.add(new Coordonnee(getLeftEdge(), chalet.getHauteur().subtract(getTopEdge()))); // top left
        sommetsAccessoire.add(new Coordonnee(getRightEdge(), chalet.getHauteur().subtract(getTopEdge()))); // top right
        sommetsAccessoire.add(new Coordonnee(getRightEdge(), chalet.getHauteur().subtract(getBottomEdge()))); // bottom right
        sommetsAccessoire.add(new Coordonnee(getLeftEdge(), chalet.getHauteur().subtract(getBottomEdge())));  // bottom left
        sommetsByVue.put(getCote().toVue(), sommetsAccessoire);
    }
}
