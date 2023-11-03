package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;




public class AfficheurPlan extends Afficheur {
    private Drawable chalet;


    public AfficheurPlan(Drawable chalet) {
        super();
        this.chalet = chalet;
        //String vue = chalet.getVue();
        //drawPlan();
    }


    public void drawPlan(Graphics g) throws Exception {
        ArrayList<Coordonnee> sommetsPlan;

        //On jugera de la pertinence de cette exception ensemble
        //if (chalet.getSensDuToit() == null) {
            //throw new Exception("L'orientation du toit doit être définie");
        //}
        if (chalet.getSensDuToit() == Orientation.FACADE || chalet.getSensDuToit() == Orientation.ARRIERE) {
            sommetsPlan = chalet.calculateSommetsPlan1();
        }
        else {
            sommetsPlan = chalet.calculateSommetsPlan2();

            //On boucle pour les 4 murs
            for (int i = 0; i < 4; i++) {
                Polygon poly = new Polygon();

                //On boucle cette fois-ci sur chacun des sommets(8)
                for (int j = 0; j < 8; j++) {
                    //poly.addPoint(sommetsPlan.get(i + j).getoffsetX(), sommetsPlan.get(i + j).getoffsetY());
                }

                // Murs Façades
                if (i == 0 || i == 2) {
                    g.setColor(Color.ORANGE);
                }
                //Murs côtés
                else {
                    g.setColor(Color.BLUE);
                }

                g.fillPolygon(poly);

            }
        }
    }
}