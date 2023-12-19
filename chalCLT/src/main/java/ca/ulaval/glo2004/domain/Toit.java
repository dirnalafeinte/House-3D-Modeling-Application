package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Toit extends Drawable implements Serializable {
    private static final Color DEFAULT_COLOR = Color.cyan;
    private Imperial hauteurToit = new Imperial();
    private Imperial longueurToit = new Imperial();

    public Toit(Chalet chalet) {
        super(chalet);
        calculerDimensionToit(chalet.getSensDuToit());
        calculateSommets();
    }

    @Override
    public void calculateSommets() {
        sommetsByVue.clear();
        calculateSommetsToitOverflowMilieu();
        calculateSommetsToitOverflowBas();
        calculateSommetsToitOverflowGauche();
        calculateSommetsToitOverflowDroit();
        calculateSommetsToitOverflowArriere();
    }

    private void calculateSommetsToitOverflowMilieu(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetForOverflowMilieuWithLargeur());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetForOverflowMilieuWithLargeur());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetForOverflowMilieuWithLongueur());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetForOverflowMilieuWithLongueur());
                break;
        }
    }


    private void calculateSommetsToitOverflowBas(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetForOverflowBasWithLargeur(Orientation.GAUCHE);
                break;
            case DROITE:
                sommetForOverflowBasWithLargeur(Orientation.DROITE);
                break;
            case FACADE:
                sommetForOverflowBasWithLongueur(Orientation.FACADE);
                break;
            case ARRIERE:
                sommetForOverflowBasWithLongueur(Orientation.ARRIERE);
                break;
        }
    }


    private void calculateSommetsToitOverflowGauche(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetOverflowToit());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetOverflowToitInverse());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetOverflowToit());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetOverflowToitInverse());
                break;
        }

    }

    private void calculateSommetsToitOverflowDroit(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetOverflowToitInverse());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetOverflowToit());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetOverflowToitInverse());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetOverflowToit());
                break;
        }

    }

    private void calculateSommetsToitOverflowArriere(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetOverflowArriereWithLargeur());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetOverflowArriereWithLargeur());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetOverflowArriereWithLongueur());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetOverflowArriereWithLongueur());
                break;
        }
    }

    private List<Coordonnee> sommetOverflowArriereWithLargeur() {
        List<Coordonnee> sommetsToit = new ArrayList<>();
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.subtract(getSmallEpaisseur()).negate()));
        sommetsToit.add(new Coordonnee(getLargeur(), hauteurToit.subtract(getSmallEpaisseur()).negate()));
        sommetsToit.add(new Coordonnee(getLargeur(), hauteurToit.negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.negate()));
        return sommetsToit;
    }

    private List<Coordonnee> sommetOverflowArriereWithLongueur() {
        List<Coordonnee> sommetsToit = new ArrayList<>();
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.subtract(getSmallEpaisseur()).negate()));
        sommetsToit.add(new Coordonnee(getLongueur(), hauteurToit.subtract(getSmallEpaisseur()).negate()));
        sommetsToit.add(new Coordonnee(getLongueur(), hauteurToit.negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.negate()));
        return sommetsToit;
    }

    private List<Coordonnee> sommetForOverflowMilieuWithLargeur() {
        List<Coordonnee> sommetsToit = new ArrayList<>();
        sommetsToit.add(new Coordonnee(new Imperial(), getBigEpaisseur().negate()));
        sommetsToit.add(new Coordonnee(getLargeur(), getBigEpaisseur().negate()));
        sommetsToit.add(new Coordonnee(getLargeur(), hauteurToit.negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.negate()));
        return sommetsToit;
    }

    private List<Coordonnee> sommetForOverflowMilieuWithLongueur() {
        List<Coordonnee> sommetsToit = new ArrayList<>();
        sommetsToit.add(new Coordonnee(new Imperial(), getBigEpaisseur().negate()));
        sommetsToit.add(new Coordonnee(getLongueur(), getBigEpaisseur().negate()));
        sommetsToit.add(new Coordonnee(getLongueur(), hauteurToit.negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.negate()));
        return sommetsToit;
    }

    private void sommetForOverflowBasWithLargeur(Orientation orientation) {
        List<Coordonnee> sommetsToit = sommetsByVue.get(orientation.toVue());
        sommetsToit.add(new Coordonnee(new Imperial(), new Imperial()));
        sommetsToit.add(new Coordonnee(getLargeur(), new Imperial()));
        sommetsToit.add(new Coordonnee(getLargeur(), getBigEpaisseur().negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), getBigEpaisseur().negate()));
    }

    private void sommetForOverflowBasWithLongueur(Orientation orientation) {
        List<Coordonnee> sommetsToit = sommetsByVue.get(orientation.toVue());
        sommetsToit.add(new Coordonnee(new Imperial(), new Imperial()));
        sommetsToit.add(new Coordonnee(getLongueur(), new Imperial()));
        sommetsToit.add(new Coordonnee(getLongueur(), getBigEpaisseur().negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), getBigEpaisseur().negate()));
    }

    //brief : Overflow du Toit vue de cote, le cote sureleve est a gauche
    private List<Coordonnee> sommetOverflowToit() {
        List<Coordonnee> sommetsToit = new ArrayList<>();
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.subtract(getSmallEpaisseur()).negate()));
        sommetsToit.add(new Coordonnee(longueurToit.subtract(getSmallEpaisseur()), new Imperial()));
        sommetsToit.add(new Coordonnee(longueurToit, new Imperial()));
        sommetsToit.add(new Coordonnee(longueurToit, getEpaisseur().divideBy(2).negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), hauteurToit.negate()));
        return sommetsToit;
    }

    //brief : Overflow du Toit vue de cote, le cote sureleve est a droite
    private List<Coordonnee> sommetOverflowToitInverse() {
        List<Coordonnee> sommetsToit = new ArrayList<>();
        sommetsToit.add(new Coordonnee(new Imperial(), new Imperial()));
        sommetsToit.add(new Coordonnee(getSmallEpaisseur(), new Imperial()));
        sommetsToit.add(new Coordonnee(longueurToit, hauteurToit.subtract(getSmallEpaisseur()).negate()));
        sommetsToit.add(new Coordonnee(longueurToit, hauteurToit.negate()));
        sommetsToit.add(new Coordonnee(new Imperial(), getEpaisseur().divideBy(2).negate()));
        return sommetsToit;
    }

    @Override
    public Color getColor() {
        return state.isValid() ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }

    public void calculerDimensionToit(Orientation sensToit) {
        if (Orientation.GAUCHE.equals(sensToit) || Orientation.DROITE.equals(sensToit)){
            this.longueurToit = getLongueur();
            this.hauteurToit = getLongueur().divideBy (Math.tan(Math.toRadians(chalet.getAngleToit())));
        }
        if (Orientation.FACADE.equals(sensToit) || Orientation.ARRIERE.equals(sensToit)){
            this.longueurToit = getLargeur();
            this.hauteurToit = getLargeur().divideBy (Math.tan(Math.toRadians(chalet.getAngleToit())));
        }
    }

    public Imperial getEpaisseur() {
        return chalet.getEpaisseurMur();
    }


    public Imperial getSmallEpaisseur() {
        return getEpaisseur().divideBy(2).subtract(chalet.getDeltaRainure().divideBy(2));
    }

    public Imperial getBigEpaisseur() {
        return getEpaisseur().divideBy(2).add(chalet.getDeltaRainure().divideBy(2));
    }

    private Imperial getLongueur() {
        return chalet.getLongueur();
    }

    public Imperial getLargeur() {
        return chalet.getLargeur();
    }
}
