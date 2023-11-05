package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.UnitConverter;

import java.awt.*;
import java.util.List;


public class Afficheur {
    private static final Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
    private final Chalet chalet;
    private Vue vue;
    private final UnitConverter unitConverter = new UnitConverter();
    private double zoomFactor = 0.2;
    private double lastZoomFactor = 1.0;
    private double xOffset = 0.0;
    private double yOffset = 0.0;

    public Afficheur(Chalet chalet, Vue vue) {
        this.chalet = chalet;
        this.vue = vue;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public void draw(Graphics g) {
        switch (vue) {
            case PLAN -> drawPlan(g);
            case FACADE, ARRIERE, GAUCHE, DROITE -> drawMur(g);
        }
    }

    private void drawPlan(Graphics g) {
        for (Mur mur : chalet.getMapMur().values()) {
            drawDrawable(g, mur);
        }
    }

    private void drawMur(Graphics g) {
        for (Mur mur : chalet.getMapMur().values()) {
            drawDrawable(g, mur);
        }
    }

    private void drawDrawable(Graphics g, Drawable drawable) {
        List<Coordonnee> sommets = drawable.getSommetsByVue(vue);
        int[] sommetsX = getScaledSommetsX(sommets);
        int[] sommetsY = getScaledSommetsY(sommets);
        g.setColor(DEFAULT_OUTLINE_COLOR);
        g.drawPolygon(sommetsX, sommetsY, sommets.size());
        g.setColor(drawable.getColor());
        g.fillPolygon(sommetsX, sommetsY, sommets.size());
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
