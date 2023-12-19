package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pignon extends Drawable implements Serializable {
    private static final Color DEFAULT_COLOR = Color.gray;
    private Imperial longueurPignon = new Imperial();
    private Imperial hauteurPignon = new Imperial();
    private final boolean isPignonDroit;

    public Pignon(Chalet chalet, boolean isPignonDroit) {
        super(chalet);
        this.isPignonDroit = isPignonDroit;
        calculerDimensionPignon(chalet.getSensDuToit());
        calculateSommets();
    }

    @Override
    public void calculateSommets() {
        sommetsByVue.clear();
        if(isPignonDroit){
            calculateSommetsPignonDroit();
        } else {
            calculateSommetsPignonGauche();
        }
    }

    private void calculateSommetsPignonDroit(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetsTriangle());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetsTriangle());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetsTriangleInverse());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetsTriangleInverse());
                break;
        }
    }

    private void calculateSommetsPignonGauche(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetsTriangleInverse());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetsTriangleInverse());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetsTriangle());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetsTriangle());
                break;
        }
    }

    //brief : Sommets d'un triangle dont l'angle a 90 degres est en bas a gauche
    private List<Coordonnee> sommetsTriangle(){
        List<Coordonnee> sommetsPignon = new ArrayList<>();
        sommetsPignon.add(new Coordonnee(getBigEpaisseur(), new Imperial())); // (0,0)
        sommetsPignon.add(new Coordonnee(longueurPignon, new Imperial()));
        sommetsPignon.add(new Coordonnee(getBigEpaisseur(), calculerYSommetTriangulation().negate()));
        return sommetsPignon;
    }

    //brief : Sommets d'un triangle dont l'angle a 90 degres est en bas a droite
    private List<Coordonnee> sommetsTriangleInverse(){
        List<Coordonnee> sommetsPignon = new ArrayList<>();
        sommetsPignon.add(new Coordonnee(getBigEpaisseur(), new Imperial())); // (0,0)
        sommetsPignon.add(new Coordonnee(longueurPignon, new Imperial()));
        sommetsPignon.add(new Coordonnee(longueurPignon, calculerYSommetTriangulation().negate()));
        return sommetsPignon;
    }

    @Override
    public Color getColor() {
        return state.isValid() ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }

    public boolean isPignonDroit() {
        return isPignonDroit;
    }
    
    public void calculerDimensionPignon(Orientation sensToit) {
        if (Orientation.GAUCHE.equals(sensToit) || Orientation.DROITE.equals(sensToit)){
            this.longueurPignon = getLongueur().subtract(getBigEpaisseur());
            Imperial tempHauteurPignon =  getLongueur().divideBy (Math.tan(Math.toRadians(chalet.getAngleToit()))); // longueur / tan(angle) = hauteur
            this.hauteurPignon = tempHauteurPignon.subtract(getBigEpaisseur()); // Le panneau du toit recouvre la rallonge, donc on soustrait moitie de l'epaisseur du panneau
        }
        if (Orientation.FACADE.equals(sensToit) || Orientation.ARRIERE.equals(sensToit)){
            this.longueurPignon = getLargeur().subtract(getBigEpaisseur());
            Imperial tempHauteurPignon =  getLargeur().divideBy (Math.tan(Math.toRadians(chalet.getAngleToit()))); // largeur / tan(angle) = hauteur
            this.hauteurPignon = tempHauteurPignon.subtract(getBigEpaisseur());
        }
    }

    private Imperial calculerYSommetTriangulation(){
        //brief : Triangulation de la partie superieur de la rallonge pour les Overflows.
        //return : la coordonnee Y du sommet du Overflow qui cree la triangulation
        double tetaPrime = 180 - (90 + chalet.getAngleToit());
        Imperial oppose = getBigEpaisseur().multiplyBy(Math.tan(Math.toRadians(tetaPrime))); // Oppose = Adjacent * tan(tetaPrime), donc: Oppose = MoitieEpaisseur * tan(tetaPrime)
        return hauteurPignon.subtract(oppose);
    }

    public Imperial getEpaisseur() {
        return chalet.getEpaisseurMur();
    }

    private Imperial getBigEpaisseur() {
        return getEpaisseur().divideBy(2).add(chalet.getDeltaRainure().divideBy(2));
    }

    private Imperial getLongueur() {
        return chalet.getLongueur();
    }

    private Imperial getLargeur() {
        return chalet.getLargeur();
    }

    public Imperial getHauteurPignon() {
        return hauteurPignon;
    }

    public Imperial getLongueurPignon() {
        return longueurPignon;
    }

}

