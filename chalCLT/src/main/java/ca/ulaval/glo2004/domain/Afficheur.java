package ca.ulaval.glo2004.domain;

import java.awt.*;


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
        this.intitialDimension = initialDimension;
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
            case DROIT:
                drawMur(g);
                break;
            case PLAN:
                drawPlan(g);
                break;

            default:
                break;
        }

    }

    private void drawMur(Graphics g) {
    }

    private void drawPlan(Graphics g) {
    }







}
