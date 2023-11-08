package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.util.UnitConverter;

public abstract class Drawable {
    protected static Color DEFAULT_ERROR_COLOR = Color.RED;
    protected final String id;
    protected final Map<Vue, List<Coordonnee>> sommetsByVue = new HashMap<>();
    protected boolean isValid = true;
    protected final Chalet chalet;
    protected final UnitConverter unitConverter = new UnitConverter();

    public Drawable(Chalet chalet) {
        this.chalet = chalet;
        this.id = UUID.randomUUID().toString();
    }

    public Drawable(String id, Chalet chalet) {
        this.chalet = chalet;
        this.id = id;
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
}

