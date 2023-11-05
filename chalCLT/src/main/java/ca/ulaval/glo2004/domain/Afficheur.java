package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Unite;

import java.awt.*;
import java.util.List;


public class Afficheur {
    private static final Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
    private final Chalet chalet;
    private Vue vue;

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

    protected void drawDrawable(Graphics g, Drawable drawable) {
        List<Coordonnee> sommets = drawable.getSommetsByVue(vue);
        int[] sommetsX = sommets.stream().mapToInt(coordonnee -> Unite.inchesToPixel(coordonnee.getX().toInches())).toArray();
        int[] sommetsY = sommets.stream().mapToInt(coordonnee -> Unite.inchesToPixel(coordonnee.getY().toInches())).toArray();
        g.setColor(DEFAULT_OUTLINE_COLOR);
        g.drawPolygon(sommetsX, sommetsY, sommets.size());
        g.setColor(drawable.getColor());
        g.fillPolygon(sommetsX, sommetsY, sommets.size());
    }
}
