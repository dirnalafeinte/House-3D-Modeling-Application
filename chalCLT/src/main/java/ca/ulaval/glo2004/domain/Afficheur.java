package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Unite;

import java.awt.*;
import java.util.List;


public class Afficheur {
    private ChaletController controller;
    private Dimension intitialDimension;
    private Vue vue;
    private double dernierFacteurZoom;
    private double offsetX;
    private double offsetY;
    private boolean isGrilleVisible;


    public Afficheur() {
        this.controller = controller;
        //this.intitialDimension = initialDimension;
        this.vue = vue;
    }

    public void draw(Graphics g) {

        switch(vue) {
            case FACADE:
                drawMur(g);
                break;
            case ARRIERE:
                drawMur(g);
                break;
            case GAUCHE:
                drawMur(g);
                break;
            case DROITE:
                drawMur(g);
                break;
            case PLAN:
                drawPlan(g);
                break;

            default:
                break;
        }

    }

    protected void drawDrawable(Graphics g, Drawable drawable) {
        //g.setColor(drawable.getColor());
        //List<Coordonnee> sommets = drawable.getSommetsByVue(vue);
        //int[] sommetsX = sommets.stream().map(coordonnee -> Unite.inchesToPixel(coordonnee.getX().toInches())).;
        //g.fillPolygon(sommets.stream().map(coordonnee -> Unite.inchesToPixel(coordonnee.getX().toInches())).toArray(), sommets.stream().map(coordonnee -> Unite.inchesToPixel(coordonnee.getY().toInches())).toArray(), sommets.size());
    }

    private void drawMur(Graphics g) {
    }

    private void drawPlan(Graphics g) {
    }







}
