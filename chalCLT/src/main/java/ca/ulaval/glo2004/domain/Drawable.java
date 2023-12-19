package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Drawable implements Serializable {
    protected static Color DEFAULT_ERROR_COLOR = Color.RED;
    protected final String id;
    protected final Map<Vue, List<Coordonnee>> sommetsByVue = new HashMap<>();
    protected final Chalet chalet;
    protected transient DrawableState state = new DrawableState(true);
    protected boolean objectSelected;

    public Drawable(Chalet chalet) {
        this.chalet = chalet;
        this.id = UUID.randomUUID().toString();
        this.objectSelected = false;
    }

    public Drawable(String id, Chalet chalet) {
        this.chalet = chalet;
        this.id = id;
    }

    public void setObjectSelected(boolean objectSelected) {
        this.objectSelected = objectSelected;
    }

    public boolean isObjectSelected() {
        return objectSelected;
    }

    public abstract void calculateSommets();

    public abstract Color getColor();

    public String getId() {
        return id;
    }

    public List<Coordonnee> getSommetsByVue(Vue vue) {
        return sommetsByVue.get(vue);
    }

    public boolean isVisible(Vue vue) {
        return sommetsByVue.get(vue) != null;
    }

    public DrawableState getState() {
        return state;
    }

    public boolean estContenu(Vue vue, Coordonnee coordonnee) {
        List<Coordonnee> sommets = getSommetsByVue(vue);
        int n = sommets.size();
        boolean estContenu = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            boolean condition1 = sommets.get(i).getY().greaterThan(coordonnee.getY()) != sommets.get(j).getY().greaterThan(coordonnee.getY());
            boolean condition2 = coordonnee.getX().lessThan(sommets.get(j).getX()
                                .subtract(sommets.get(i).getX())
                                .multiply(coordonnee.getY().subtract(sommets.get(i).getY()))
                                .divide(sommets.get(j).getY().subtract(sommets.get(i).getY()))
                                .add(sommets.get(i).getX()));
            if (condition1 && condition2)
                estContenu = !estContenu;
        }

        return estContenu;
    }
}



