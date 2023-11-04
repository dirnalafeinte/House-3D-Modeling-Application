package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mur extends Drawable{
    private Orientation cote;
    private List<Accessoire> accessoires;

    public Mur(Orientation cote, Chalet chalet) {
        super(chalet);
        this.cote = cote;
    }

    public void ajouterFenetre(Coordonnee coordonnee, Imperial largeur, Imperial hauteur){

    }
    public void ajouterPorte(Coordonnee coordonnee, Imperial largeur, Imperial hauteur){

    }

    public Orientation getCote() {
        return cote;
    }

    public void setCote(Orientation cote) {
        this.cote = cote;
    }

    public List<Accessoire> getAccessoires() {
        return accessoires;
    }

    @Override
    public void calculateSommets() {
        sommets.clear();
        calculateSommetsPlan();
        calculateSommetsMur();
    }

    private void calculateSommetsMur() {
        calculateSommetsMurInSameView();
        if (isCoteLong()) {
            calculateSommetsMurOverflowGauche();
            calculateSommetsMurOverflowDroite();
        }
    }

    private void calculateSommetsMurInSameView() {

    }

    private void calculateSommetsMurOverflowGauche() {

    }

    private void calculateSommetsMurOverflowDroite() {

    }

    private void calculateSommetsPlan() {
        switch (cote) {
            case FACADE:
                calculateSommetsPlanFacade();
                break;
            case ARRIERE:
                calculateSommetsPlanArriere();
                break;
            case GAUCHE:
                calculateSommetsPlanGauche();
                break;
            case DROITE:
                calculateSommetsPlanDroite();
                break;
        }
    }

    private void calculateSommetsPlanFacade() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(getStartMur(), getLargeur()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()), getLargeur()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()), getLargeur().substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getLargeur().substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getLargeur().substract(getEpaisseur())));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getSmallEpaisseur()), getLargeur().substract(getEpaisseur())));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getSmallEpaisseur()), getLargeur().substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getStartMur(), getLargeur().substract(getSmallEpaisseur())));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanArriere() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(getStartMur(), new Imperial()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()), new Imperial()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getEpaisseur()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getSmallEpaisseur()), getEpaisseur()));
        sommetsPlan.add(new Coordonnee(getStartMur().add(getSmallEpaisseur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getStartMur(), getSmallEpaisseur()));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanGauche() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(new Imperial(), getStartMur()));
        sommetsPlan.add(new Coordonnee(new Imperial(), getStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getEpaisseur(), getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getEpaisseur(), getStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getStartMur()));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanDroite() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(getLongueur(), getStartMur()));
        sommetsPlan.add(new Coordonnee(getLongueur(), getStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getEpaisseur()), getStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getEpaisseur()), getStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getStartMur()));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private Imperial getLongueurMur() {
        if (isCoteLong()) {
            return getSideLongueur();
        } else {
            return getSideLongueur().substract(chalet.getEpaisseurMur());
        }
    }

    private Imperial getStartMur() {
        if (isCoteLong()) {
            return new Imperial();
        } else {
            return getSmallEpaisseur();
        }
    }

    private boolean isCoteLong() {
        return cote == chalet.getSensDuToit() || cote == chalet.getSensDuToit().getOpposite();
    }

    private Imperial getSideLongueur() {
        if (cote == Orientation.FACADE || cote == Orientation.ARRIERE) {
            return chalet.getLongueur();
        } else {
            return chalet.getLargeur();
        }
    }

    private Imperial getEpaisseur() {
        return chalet.getEpaisseurMur();
    }

    private Imperial getSmallEpaisseur() {
        return getEpaisseur().divide(2).substract(chalet.getDeltaRainure().divide(2));
    }

    private Imperial getBigEpaisseur() {
        return getEpaisseur().divide(2).add(chalet.getDeltaRainure().divide(2));
    }

    private Imperial getLongueur() {
        return chalet.getLongueur();
    }

    private Imperial getLargeur() {
        return chalet.getLargeur();
    }
}
