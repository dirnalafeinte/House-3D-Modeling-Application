package ca.ulaval.glo2004.domain;

import java.awt.*;

public class AfficheurMur extends Afficheur {
    public AfficheurMur(Chalet chalet, Vue vue) {
        super(chalet, vue);
    }

    @Override
    public void draw(Graphics g) {
        drawMur(g);
        drawAccessoires(g);
    }

    private void drawMur(Graphics g) {
        for (Mur mur : chalet.getMurs()) {
            if (mur.isVisible(vue)) {
                drawDrawable(g, mur);
            }
        }
    }

    private void drawAccessoires(Graphics g) {
        for (Accessoire accessoire : chalet.getMurByOrientation(vue.toOrientation()).getAccessoires()) {
            drawDrawable(g, accessoire);
        }
    }
}
