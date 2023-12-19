package ca.ulaval.glo2004.domain.drawers;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.util.UnitConverter;

import java.awt.*;
import java.util.List;

public abstract class Afficheur {
    protected static final Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
    protected Chalet chalet;
    protected final Vue vue;
    protected final UnitConverter unitConverter = new UnitConverter();
    protected double zoomFactor = 0.25;
    protected double lastZoomFactor = 1.0;
    protected double xOffset = 500;
    protected double yOffset = 1000;
    protected double yChalet = 0;
    protected double xChalet = 0;
    protected String intervalLigne = "10\"";


    public Afficheur(Chalet chalet, Vue vue) {
        this.chalet = chalet;
        this.vue = vue;
    }

    public abstract void draw(Graphics g);

    protected void drawDrawable(Graphics g, Drawable drawable) {
        List<Coordonnee> sommets = drawable.getSommetsByVue(vue);
        int[] sommetsX = getScaledSommetsX(sommets);
        int[] sommetsY = getScaledSommetsY(sommets);
        g.setColor(getObjectColor(drawable));
        g.fillPolygon(sommetsX, sommetsY, sommets.size());
        g.setColor(DEFAULT_OUTLINE_COLOR);
        g.drawPolygon(sommetsX, sommetsY, sommets.size());
    }

    private int[] getScaledSommetsX(List<Coordonnee> sommets) {
        return sommets.stream().mapToInt(coordonnee -> (unitConverter.inchesToPixel(coordonnee.getX().toInches()))).toArray();
    }

    private int[] getScaledSommetsY(List<Coordonnee> sommets) {
        return sommets.stream().mapToInt(coordonnee -> (unitConverter.inchesToPixel(coordonnee.getY().toInches()))).toArray();
    }

    public void drawGrille(Graphics g, int largeur, int hauteur)
    {
        g.setColor(Color.LIGHT_GRAY);

        int intervalle = unitConverter.inchesToPixel(Imperial.fromString(intervalLigne).toInches());

        for (int x = 0; x < largeur; x += intervalle) {
            g.drawLine(x, 0, x, hauteur);
        }

        for (int y = 0; y < hauteur; y += intervalle) {
            g.drawLine(0, y, largeur, y);
        }
    }

    public Color getObjectColor(Drawable drawable) {
        if (drawable.isObjectSelected() && drawable.getState().isValid())
            return (Color.MAGENTA);
        return (drawable.getColor());
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public double getLastZoomFactor() {
        return lastZoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        lastZoomFactor = this.zoomFactor;
        this.zoomFactor = zoomFactor;
    }

    public Vue getVue() {
        return vue;
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    public double getyChalet() {
        return yChalet;
    }

    public void setyChalet(double yChalet) {
        this.yChalet = yChalet;
    }

    public double getxChalet() {
        return xChalet;
    }

    public void setxChalet(double xChalet) {
        this.xChalet = xChalet;
    }

    public void setChalet(Chalet chalet) {
        this.chalet = chalet;
    }

    public void setIntervalLigne(String intervalLigne) {
        this.intervalLigne = intervalLigne;
    }

    public String getIntervalLigne() {
        return intervalLigne;
    }

    public UnitConverter getUnitConverter() {
        return unitConverter;
    }
}
