package ca.ulaval.glo2004.domain;

import javax.swing.*;
import java.awt.*;
public class Afficheur {

    private ChaletController controller;
    private Dimension intitialDimension;
    private Vue vue;
    private double dernierFacteurZoom;
    private double offsetX;
    private double offsetY;
    private boolean isGrilleVisible;


    public Afficheur(ChaletController controller, Dimension initialDimension, Vue vue) {
        this.controller = controller;
        this.intitialDimension = initialDimension;
        this.vue = vue;
    }

    public void draw(Graphics g) {

        switch(vue) {
            case "Mur":
                drawMur(g);
            case "Dessus":
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
