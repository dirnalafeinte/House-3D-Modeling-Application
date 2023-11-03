package ca.ulaval.glo2004.domain;

import javax.swing.*;
import java.awt.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;



public class AfficheurPlan extends Afficheur {
    private Drawable drawable;
    private Chalet chalet;


    public AfficheurPlan(Drawable chalet) {
        super();
        this.chalet = chalet;
        String vue = chalet.getVue();
        drawPlan(g, vue);
    }

    public void drawPlan(Graphics g) throws Exception {
        ArrayList<Coordonnee> sommetsPlan;

        if (chalet.getSensDuToit() == null) {
            throw new Exception("L'orientation du toit doit être définie");
        } else if (chalet.getSensDuToit() == Orientation.FACADE || chalet.getSensDuToit() == Orientation.ARRIERE) {
            sommetsPlan = chalet.calculateSommetsPlan1();
        } else {
            sommetsPlan = chalet.calculateSommetsPlan2();


            for (int i = 0; i < 4; i++) {
                Polygon poly = new Polygon();

                for (int j = 0; j < 8; j++) {
                    poly.addPoint(sommetsPlan.get(i + j).getX(), sommetsPlan.get(i + j).getY());
                }

                // Mur Façades
                if (i == 0 || i == 2) {
                    g.setColor(Color.ORANGE);
                } else {
                    g.setColor(Color.BLUE);
                }

                g.fillPolygon(poly);

            }
        }
    }
}