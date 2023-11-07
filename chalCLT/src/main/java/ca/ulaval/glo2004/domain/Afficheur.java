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
    private double zoomFactor = 0.25;
    private double lastZoomFactor = 1.0;
    private double xOffset = 24;
    private double yOffset = 9;

    public Afficheur(Chalet chalet, Vue vue) {
        this.chalet = chalet;
        this.vue = vue;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public Vue getVue() {
        return vue;
    }

    public void draw(Graphics g) {
        switch (vue) {
            case PLAN -> drawPlan(g);
            case FACADE, ARRIERE, GAUCHE, DROITE -> drawVisible(g);
        }
    }

    private void drawPlan(Graphics g) {
        for (Mur mur : chalet.getMurs()) {
            drawDrawable(g, mur);
        }
    }

    private void drawVisible(Graphics g) {
        for (Mur mur : chalet.getMurs()) {
            if (mur.isVisible(vue)) {
                drawDrawable(g, mur);
                if (mur.getCote().toVue().equals(vue)){
                    for (Accessoire accessoire : mur.getAccessoires()){
                        drawDrawable(g, accessoire);
                    }
                }
            }
        }
    }

    private void drawDrawable(Graphics g, Drawable drawable) {
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
