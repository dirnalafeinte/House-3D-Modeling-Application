package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.accessoire.Accessoire;
import ca.ulaval.glo2004.domain.accessoire.AccessoireType;
import ca.ulaval.glo2004.domain.accessoire.Fenetre;
import ca.ulaval.glo2004.domain.accessoire.Porte;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mur extends Drawable {
    private static final Color DEFAULT_COLOR_1 = Color.BLUE;
    private static final Color DEFAULT_COLOR_2 = Color.ORANGE;
    private final Orientation cote;
    private final Map<String, Accessoire> accessoiresById = new HashMap();

    public Mur(Chalet chalet, Orientation cote) {
        super(chalet);
        this.cote = cote;
        calculateSommets();
    }

    @Override
    public void calculateSommets() {
        sommetsByVue.clear();
        calculateSommetsPlan();
        calculateSommetsMur();
    }

    @Override
    public Color getColor() {
        return isValid ? isCoteLong() ? DEFAULT_COLOR_1 : DEFAULT_COLOR_2 : DEFAULT_ERROR_COLOR;
    }

    public boolean contains(Accessoire that) {
        boolean isLeft = that.getCoordonnee().getX().add(that.getLargeur().divideBy(2)).lessOrEquals(getDistanceStartMur().add(getLongueurMur()));
        boolean isRight = that.getCoordonnee().getX().subtract(that.getLargeur().divideBy(2)).greaterOrEquals(getDistanceStartMur());
        boolean isTop = that.getCoordonnee().getY().add(that.getHauteur().divideBy(2)).lessOrEquals(getHauteur());
        boolean isBottom = that.getCoordonnee().getY().subtract(that.getHauteur().divideBy(2)).greaterOrEquals(new Imperial());

        return isLeft && isRight && isTop && isBottom;
    }

    public Imperial getMinDistance(Fenetre that) {
        Imperial distanceTop = getHauteur().subtract(that.getCoordonnee().getY().add(that.getHauteur().divideBy(2))).abs();
        Imperial distanceBottom = (new Imperial()).subtract(that.getCoordonnee().getY().subtract(that.getHauteur().divideBy(2))).abs();
        Imperial distanceLeft = getDistanceStartMur().subtract(that.getCoordonnee().getX().subtract(that.getLargeur().divideBy(2))).abs();
        Imperial distanceRight = getDistanceStartMur().add(getLongueurMur()).subtract(that.getCoordonnee().getX().add(that.getLargeur().divideBy(2))).abs();

        return Imperial.min(Imperial.min(distanceTop, distanceBottom), Imperial.min(distanceLeft, distanceRight));
    }

    public Imperial getMinDistance(Porte that) {
        Imperial distanceTop = getHauteur().subtract(that.getCoordonnee().getY().add(that.getHauteur().divideBy(2))).abs();
        Imperial distanceLeft = getDistanceStartMur().subtract(that.getCoordonnee().getX().subtract(that.getLargeur().divideBy(2))).abs();
        Imperial distanceRight = getDistanceStartMur().add(getLongueurMur()).subtract(that.getCoordonnee().getX().add(that.getLargeur().divideBy(2))).abs();

        return Imperial.min(distanceTop, Imperial.min(distanceLeft, distanceRight));
    }

    public Orientation getCote() {
        return cote;
    }

    public List<Accessoire> getAccessoires() {
        return accessoiresById.values().stream().toList();
    }

    public List<Porte> getPortes() {
        List<Porte> portes = new ArrayList<>();
        for (Accessoire accessoire : accessoiresById.values()) {
            if (accessoire.getType() == AccessoireType.PORTE) {
                portes.add((Porte) accessoire);
            }
        }
        return portes;
    }

    public List<Fenetre> getFenetres() {
        List<Fenetre> fenetres = new ArrayList<>();
        for (Accessoire accessoire : accessoiresById.values()) {
            if (accessoire.getType() == AccessoireType.FENETRE) {
                fenetres.add((Fenetre) accessoire);
            }
        }
        return fenetres;
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
        sommetsByVue.put(cote.toVue(), sommetsMur);
    }

    private void calculateSommetsMurOverflowGauche() {
        List<Coordonnee> sommetsMur = new ArrayList<>();
        sommetsMur.add(new Coordonnee(new Imperial(), new Imperial()));
        sommetsMur.add(new Coordonnee(getSmallEpaisseur(), new Imperial()));
        sommetsMur.add(new Coordonnee(getSmallEpaisseur(), getHauteur()));
        sommetsMur.add(new Coordonnee(new Imperial(), getHauteur()));
        sommetsByVue.put(cote.getDroite().toVue(), sommetsMur);
    }

    private void calculateSommetsMurOverflowDroite() {
        List<Coordonnee> sommetsMur = new ArrayList<>();
        sommetsMur.add(new Coordonnee(getLongueurAutreCote(), new Imperial()));
        sommetsMur.add(new Coordonnee(getLongueurAutreCote().subtract(getSmallEpaisseur()), new Imperial()));
        sommetsMur.add(new Coordonnee(getLongueurAutreCote().subtract(getSmallEpaisseur()), getHauteur()));
        sommetsMur.add(new Coordonnee(getLongueurAutreCote(), getHauteur()));
        sommetsByVue.put(cote.getGauche().toVue(), sommetsMur);
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
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), getLargeur().subtract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur()), getLargeur().subtract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur()), getLargeur().subtract(getEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getLargeur().subtract(getEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getLargeur().subtract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur(), getLargeur().subtract(getSmallEpaisseur())));
        sommetsByVue.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanArriere() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(getDistanceStartMur(), new Imperial()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), new Imperial()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur()), getEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur().add(getSmallEpaisseur()), getSmallEpaisseur()));
        sommetsPlan.add(new Coordonnee(getDistanceStartMur(), getSmallEpaisseur()));
        sommetsByVue.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanGauche() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(new Imperial(), getDistanceStartMur()));
        sommetsPlan.add(new Coordonnee(new Imperial(), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getEpaisseur(), getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getEpaisseur(), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getSmallEpaisseur(), getDistanceStartMur()));
        sommetsByVue.put(Vue.PLAN, sommetsPlan);
    }

    private void calculateSommetsPlanDroite() {
        List<Coordonnee> sommetsPlan = new ArrayList<>();
        sommetsPlan.add(new Coordonnee(getLongueur(), getDistanceStartMur()));
        sommetsPlan.add(new Coordonnee(getLongueur(), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getLongueur().subtract(getSmallEpaisseur()), getDistanceStartMur().add(getLongueurMur())));
        sommetsPlan.add(new Coordonnee(getLongueur().subtract(getSmallEpaisseur()), getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().subtract(getEpaisseur()), getDistanceStartMur().add(getLongueurMur()).subtract(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().subtract(getEpaisseur()), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().subtract(getSmallEpaisseur()), getDistanceStartMur().add(getSmallEpaisseur())));
        sommetsPlan.add(new Coordonnee(getLongueur().subtract(getSmallEpaisseur()), getDistanceStartMur()));
        sommetsByVue.put(Vue.PLAN, sommetsPlan);
    }

    private Imperial getLongueurMur() {
        if (isCoteLong()) {
            return getLongueurCote();
        } else {
            return getLongueurCote().subtract(getEpaisseur());
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

    private Imperial getHauteur() {
        return chalet.getHauteur();
    }

    public void addAccessoire(Accessoire accessoire) {
        accessoiresById.put(accessoire.getId(), accessoire);
    }

    public void modifyAccessoire(Accessoire accessoire) {
        accessoiresById.replace(accessoire.getId(), accessoire);
    }

    public void removeAccessoireById(String id) {
        accessoiresById.remove(id);
    }
}
