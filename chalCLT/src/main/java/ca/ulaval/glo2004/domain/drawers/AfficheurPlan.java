package ca.ulaval.glo2004.domain.drawers;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.Mur;
import ca.ulaval.glo2004.domain.Vue;
import ca.ulaval.glo2004.domain.drawers.Afficheur;

import java.awt.*;

public class AfficheurPlan extends Afficheur {
    public AfficheurPlan(Chalet chalet, Vue vue) {
        super(chalet, vue);
    }

    @Override
    public void draw(Graphics g) {
        drawMur(g);
    }

    private void drawMur(Graphics g) {
        for (Mur mur : chalet.getMurs()) {
            drawDrawable(g, mur);
        }
    }
}
