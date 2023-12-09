package ca.ulaval.glo2004.domain.drawers;

import ca.ulaval.glo2004.domain.accessoires.Accessoire;
import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.Mur;
import ca.ulaval.glo2004.domain.Vue;

import java.awt.*;

public class AfficheurMur extends Afficheur {
    public AfficheurMur(Chalet chalet, Vue vue) {
        super(chalet, vue);
    }

    @Override
    public void draw(Graphics g) {
        drawMur(g);
        drawAccessoires(g);
        drawRallonge(g);
//        drawToit(g);
//        drawPignonDroit(g);
//        drawPignonGauche(g);
    }

    private void drawMur(Graphics g) {
        for (Mur mur : chalet.getMurs()) {
            if (mur.isVisible(vue)) {
                drawDrawable(g, mur);
            }
        }
    }

    private void drawRallonge(Graphics g) {
        if (chalet.getRallonge().isVisible(vue)){
            drawDrawable(g, chalet.getRallonge());
        }
    }

    private void drawToit(Graphics g) {
        if (chalet.getToit().isVisible(vue)){
            drawDrawable(g, chalet.getToit());
        }
    }

    private void drawPignonDroit(Graphics g) {
        if (chalet.getPignonDroit().isVisible(vue)){
            drawDrawable(g, chalet.getPignonDroit());
        }
    }

    private void drawPignonGauche(Graphics g) {
        if (chalet.getPignonGauche().isVisible(vue)){
            drawDrawable(g, chalet.getPignonGauche());
        }
    }

    private void drawAccessoires(Graphics g) {
        for (Accessoire accessoire : chalet.getMurByOrientation(vue.toOrientation()).getAccessoires()) {
            drawDrawable(g, accessoire);
        }
    }
}
