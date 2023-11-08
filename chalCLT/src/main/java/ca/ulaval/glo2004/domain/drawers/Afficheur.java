package ca.ulaval.glo2004.domain.drawers;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.UnitConverter;

import java.awt.*;
import java.util.List;


public abstract class Afficheur {
    protected static final Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
    protected final Chalet chalet;
    protected final Vue vue;
    protected final UnitConverter unitConverter = new UnitConverter();
    protected double zoomFactor = 0.25;
    protected double lastZoomFactor = 1.0;
    protected double xOffset = 24;
    protected double yOffset = 9;

    public Afficheur(Chalet chalet, Vue vue) {
        this.chalet = chalet;
        this.vue = vue;
    }

    public abstract void draw(Graphics g);

    protected void drawDrawable(Graphics g, Drawable drawable) {
        List<Coordonnee> sommets = drawable.getSommetsByVue(vue);
        int[] sommetsX = getScaledSommetsX(sommets);
        int[] sommetsY = getScaledSommetsY(sommets);
        g.setColor(drawable.getColor());
        g.fillPolygon(sommetsX, sommetsY, sommets.size());
        g.setColor(DEFAULT_OUTLINE_COLOR);
        g.drawPolygon(sommetsX, sommetsY, sommets.size());
    }

    private int[] getScaledSommetsX(List<Coordonnee> sommets) {
        return sommets.stream().mapToInt(coordonnee -> unitConverter.inchesToPixel((coordonnee.getX().toInches() * zoomFactor) + xOffset)).toArray();
    }

    private int[] getScaledSommetsY(List<Coordonnee> sommets) {
        return sommets.stream().mapToInt(coordonnee -> unitConverter.inchesToPixel((coordonnee.getY().toInches() * zoomFactor) + yOffset)).toArray();
    }

    public void setZoomFactor(double zoomFactor) {
        lastZoomFactor = this.zoomFactor;
        this.zoomFactor = zoomFactor;
    }
}
