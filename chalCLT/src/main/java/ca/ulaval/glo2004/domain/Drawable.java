package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.util.UnitConverter;

public abstract class Drawable {
    protected static Color DEFAULT_ERROR_COLOR = Color.RED;
    private final String id;
    protected final Map<Vue, List<Coordonnee>> sommets = new HashMap<>();
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

    public Map<Vue, List<Coordonnee>> getSommets() {
        return sommets;
    }

    public List<Coordonnee> getSommetsByVue(Vue vue) {
        return sommets.get(vue);
    }

    public boolean estContenu(Vue vue, Coordonnee coordonnee){
        // TODO
        return true;
    }

    public boolean estContenu(Vue vue, Drawable that){
        Polygon thatPolygon = createPolygonFromVertices(that.getSommetsByVue(vue));
        Polygon thisPolygon = createPolygonFromVertices(this.getSommetsByVue(vue));


        return thatPolygon.contains(thisPolygon.getBounds()); // Le polygone de this est contenu dans le polygone de that
    }

    public Imperial getDistanceMinEntre(Drawable that){
        // TODO
        return null;
    }

    private Polygon createPolygonFromVertices(List<Coordonnee> my_sommets) {
        int[] xPoints = new int[my_sommets.size()];
        int[] yPoints = new int[my_sommets.size()];

        for (int i = 0; i < my_sommets.size(); i++) {
            Coordonnee vertex = my_sommets.get(i);
            xPoints[i] = unitConverter.inchesToPixel(vertex.getX().toInches());
            yPoints[i] = unitConverter.inchesToPixel(vertex.getY().toInches());
        }

        return new Polygon(xPoints, yPoints, my_sommets.size());
    }

    public boolean isVisible(Vue vue) {
        return sommets.get(vue) != null;
    }
}

