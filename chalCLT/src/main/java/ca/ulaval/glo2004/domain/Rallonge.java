package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rallonge extends Drawable implements Serializable {
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private Imperial longueurRallonge = new Imperial();
    private Imperial hauteurRallonge = new Imperial();
    public Rallonge(Chalet chalet) {
        super(chalet);
        calculerDimensionRallonge(chalet.getSensDuToit());
        calculateSommets();
    }

    @Override
    public void calculateSommets() {
        sommetsByVue.clear();
        calculateSommetsRallongeOverflowMilieu();
        calculateSommetsRallongeOverflowGauche();
        calculateSommetsRallongeOverflowDroit();
    }

    private void calculateSommetsRallongeOverflowMilieu(){
        List<Coordonnee> sommetsRallonge = new ArrayList<>();
        sommetsRallonge.add(new Coordonnee(new Imperial(), new Imperial())); // (0,0)
        sommetsRallonge.add(new Coordonnee(longueurRallonge, new Imperial())); // (longueurRallonge,0)
        sommetsRallonge.add(new Coordonnee(longueurRallonge, hauteurRallonge.negate())); // (longueurRallonge,-hauteurRallonge)
        sommetsRallonge.add(new Coordonnee(new Imperial(), hauteurRallonge.negate())); // (0,-hauteurRallonge)
        sommetsByVue.put(chalet.getSensDuToit().getOpposite().toVue(), sommetsRallonge);
    }

    private void calculateSommetsRallongeOverflowGauche() {
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetForOverflowGaucheDroiteEtGauche());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetForOverflowGaucheDroiteEtGauche());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetForOverflowGaucheFacadeEtArriere());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetForOverflowGaucheFacadeEtArriere());
                break;
        }
    }

    private void calculateSommetsRallongeOverflowDroit(){
        switch (chalet.getSensDuToit()) {
            case GAUCHE:
                sommetsByVue.put(Orientation.ARRIERE.toVue(), sommetForOverflowDroit());
                break;
            case DROITE:
                sommetsByVue.put(Orientation.FACADE.toVue(), sommetForOverflowDroit());
                break;
            case FACADE:
                sommetsByVue.put(Orientation.GAUCHE.toVue(), sommetForOverflowDroit());
                break;
            case ARRIERE:
                sommetsByVue.put(Orientation.DROITE.toVue(), sommetForOverflowDroit());
                break;
        }

    }

    private List<Coordonnee> sommetForOverflowGaucheFacadeEtArriere(){
        List<Coordonnee> sommets = new ArrayList<>();
        sommets.add(new Coordonnee(getLargeur().subtract(getSmallEpaisseur()), new Imperial()));
        sommets.add(new Coordonnee(getLargeur(), new Imperial()));
        sommets.add(new Coordonnee(getLargeur(), hauteurRallonge.negate()));
        sommets.add(new Coordonnee(getLargeur().subtract(getSmallEpaisseur()), calculerYSommetTriangulation().negate()));
        return sommets;
    }

    private List<Coordonnee> sommetForOverflowGaucheDroiteEtGauche(){
        List<Coordonnee> sommets = new ArrayList<>();
        sommets.add(new Coordonnee(getLargeur().subtract(getSmallEpaisseur()), new Imperial()));
        sommets.add(new Coordonnee(getLongueur(), new Imperial()));
        sommets.add(new Coordonnee(getLongueur(), hauteurRallonge.negate()));
        sommets.add(new Coordonnee(getLongueur().subtract(getSmallEpaisseur()), calculerYSommetTriangulation().negate()));
        return sommets;
    }

    private List<Coordonnee> sommetForOverflowDroit() {
        List<Coordonnee> sommets = new ArrayList<>();
        sommets.add(new Coordonnee(new Imperial(), new Imperial()));
        sommets.add(new Coordonnee(getSmallEpaisseur(), new Imperial()));
        sommets.add(new Coordonnee(getSmallEpaisseur(), calculerYSommetTriangulation().negate()));
        sommets.add(new Coordonnee(new Imperial(), hauteurRallonge.negate()));
        return sommets;
    }


    public Imperial calculerYSommetTriangulation(){
        //brief : Triangulation de la partie superieur de la rallonge pour les Overflows.
        //return : la coordonnee Y du sommet du Overflow qui cree la triangulation
        int tetaPrime = (int) (180 - (90 + chalet.getAngleToit()));
        Imperial oppose = getSmallEpaisseur().multiplyBy(Math.tan(Math.toRadians(tetaPrime))); // Oppose = Adjacent * tan(tetaPrime), donc: Oppose = MoitieEpaisseur * tan(tetaPrime)
        return hauteurRallonge.subtract(oppose);
    }

    @Override
    public Color getColor() {
        return state.isValid() ? DEFAULT_COLOR : DEFAULT_ERROR_COLOR;
    }

    public void calculerDimensionRallonge(Orientation sensToit) {
      if (Orientation.GAUCHE.equals(sensToit) || Orientation.DROITE.equals(sensToit)){
          this.longueurRallonge = getLargeur();
          Imperial tempHauteurRallonge =  getLongueur().divideBy (Math.tan(Math.toRadians(chalet.getAngleToit()))); // longueur / tan(angle) = hauteur
          this.hauteurRallonge = tempHauteurRallonge.subtract(getBigEpaisseur()); // Le panneau du toit recouvre la rallonge, donc on soustrait moitie de l'epaisseur du panneau
      }
      if (Orientation.FACADE.equals(sensToit) || Orientation.ARRIERE.equals(sensToit)){
          this.longueurRallonge = getLongueur();
          Imperial tempHauteurRallonge =  getLargeur().divideBy (Math.tan(Math.toRadians(chalet.getAngleToit()))); // largeur / tan(angle) = hauteur
          this.hauteurRallonge = tempHauteurRallonge.subtract(getBigEpaisseur());
      }
    }

    public Imperial getEpaisseur() {
        return chalet.getEpaisseurMur();
    }


    private Imperial getSmallEpaisseur() {
        return getEpaisseur().divideBy(2).subtract(chalet.getDeltaRainure().divideBy(2));
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

    public Imperial getHauteurRallonge() {
        return hauteurRallonge;
    }

    public Imperial getLongueurRallonge() {
        return longueurRallonge;
    }
}
