package ca.ulaval.glo2004.domain;

import java.awt.*;


public class Afficheur {

    private AfficheurPlan afficheurPlan;
    //private AfficheurMur afficheurMur;
    private Vue vue;

    private Chalet chalet;



    public Afficheur(Chalet chalet, Vue vue) {
        this.vue = vue;
        this.chalet = chalet;
        afficheurPlan = new AfficheurPlan(chalet.getMapMur().get(Orientation.FACADE));
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

    private void drawMur(Graphics g) {
    }



    private void drawPlan(Graphics g){
        afficheurPlan.drawPlan(g);
    }
}
