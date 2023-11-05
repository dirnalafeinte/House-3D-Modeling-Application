package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mur extends Drawable {
    private static final Color DEFAULT_COLOR_1 = Color.BLUE;
    private static final Color DEFAULT_COLOR_2 = Color.ORANGE;
    private final Orientation cote;
    private final List<Accessoire> accessoires = new ArrayList<>();

    public Mur(Chalet chalet, Orientation cote) {
        super(chalet);
        this.cote = cote;
    }

    public Chalet getChalet() {
        return chalet;
    }

    @Override
    protected void setColor() {
        if (cote == Orientation.FACADE || cote == Orientation.ARRIERE) {
            color = DEFAULT_COLOR_1;
        } else {
            color = DEFAULT_COLOR_2;
        }
    }


    @Override
    public void calculateSommets() {
        sommets.clear();
        calculateSommetsPlan();
        calculateSommetsMur();
    }

    public void ajouterFenetre(Coordonnee coordonnee, Imperial largeur, Imperial hauteur){
        Fenetre fenetre = new Fenetre(largeur, hauteur, coordonnee, chalet, this);
        accessoires.add(fenetre);
    }

    public void ajouterPorte(Coordonnee coordonnee, Imperial largeur, Imperial hauteur){
        Porte porte = new Porte(largeur, hauteur, coordonnee, chalet, this);
        accessoires.add(porte);
    }

    public Orientation getCote() {
        return cote;
    }

    public List<Accessoire> getAccessoires() {
        return accessoires;
    }

    private void calculateSommetsMur() {
        calculateSommetsMurInItsView();
        if (isCoteLong()) {
            calculateSommetsMurOverflowGauche();
            calculateSommetsMurOverflowDroite();
        }
    }

    private void calculateSommetsMurInItsView() {
        List<Coordonnee> sommetsMur = new ArrayList<>();
        sommetsMur.add(new Coordonnee(getDistanceStartMur(), new Imperial()));
        sommetsMur.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), new Imperial()));
        sommetsMur.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), getHauteur()));
        sommetsMur.add(new Coordonnee(getDistanceStartMur(), getHauteur()));
        sommets.put(cote.toVue(), sommetsMur);
    }

    private void calculateSommetsMurOverflowGauche() {
        List<Coordonnee> sommetsMur = new ArrayList<>();
        sommetsMur.add(new Coordonnee(new Imperial(), new Imperial()));
        sommetsMur.add(new Coordonnee(getSmallEpaisseur(), new Imperial()));
        sommetsMur.add(new Coordonnee(getSmallEpaisseur(), getHauteur()));
        sommetsMur.add(new Coordonnee(new Imperial(), getHauteur()));
        sommets.put(cote.getDroite().toVue(), sommetsMur);
    }

    private void calculateSommetsMurOverflowDroite() {
        List<Coordonnee> sommetsMur = new ArrayList<>();
        sommetsMur.add(new Coordonnee(getLongueurAutreCote(), new Imperial()));
        sommetsMur.add(new Coordonnee(getLongueurAutreCote().substract(getSmallEpaisseur()), new Imperial()));
        sommetsMur.add(new Coordonnee(getLongueurAutreCote().substract(getSmallEpaisseur()), getHauteur()));
        sommetsMur.add(new Coordonnee(getLongueurAutreCote(), getHauteur()));
        sommets.put(cote.getGauche().toVue(), sommetsMur);
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
        sommetsPlan.add(new Coordonnee(getDistanceStartMur(), getLargeur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), getLargeur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), getLargeur().substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getLargeur().substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getLargeur().substract(getEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getLargeur().substract(getEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getLargeur().substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur(), getLargeur().substract(getSmallEpaisseur())));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanArriere() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(getDistanceStartMur(), new Imperial()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), new Imperial()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur()), getEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur(), getSmallEpaisseur()));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanGauche() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(new Imperial(), getDistanceStartMur()));
        sommetsPlan.add(new Coordonnee(new Imperial(), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getEpaisseur(), getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getEpaisseur(), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur()));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanDroite() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(getLongueur(), getDistanceStartMur()));
        sommetsPlan.add(new Coordonnee(getLongueur(), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getEpaisseur()), getDistanceStartMur().add(getLongueurMur()).substract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getEpaisseur()), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().substract(getSmallEpaisseur()), getDistanceStartMur()));
        sommets.put(Vue.PLAN, sommetsPlan);
    }

    private Imperial getLongueurMur() {
        if (isCoteLong()) {
            return getLongueurCote();
        } else {
            return getLongueurCote().substract(getEpaisseur());
        }
    }

    private Imperial getDistanceStartMur() {
        if (isCoteLong()) {
            return new Imperial();
        } else {
            return getSmallEpaisseur();
        }
    }

    private boolean isCoteLong() {
        return cote == chalet.getSensDuToit() || cote == chalet.getSensDuToit().getOpposite();
    }

    private Imperial getLongueurCote() {
        if (cote == Orientation.FACADE || cote == Orientation.ARRIERE) {
            return getLongueur();
        } else {
            return getLargeur();
        }
    }

    private Imperial getLongueurAutreCote() {
        if (cote == Orientation.FACADE || cote == Orientation.ARRIERE) {
            return getLargeur();
        } else {
            return getLongueur();
        }
    }

    private Imperial getEpaisseur() {
        return chalet.getEpaisseurMur();
    }

    private Imperial getSmallEpaisseur() {
        return getEpaisseur().divideBy(2).substract(chalet.getDeltaRainure().divideBy(2));
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

    private Imperial getHauteur() {
        return chalet.getHauteur();
    }
}
