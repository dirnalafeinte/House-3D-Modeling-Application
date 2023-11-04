package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;




public class AfficheurPlan extends Afficheur {
    private Chalet chalet;
    private Drawable drawable;

    public AfficheurPlan(Drawable drawable, Chalet chalet) {
        super();
        this.chalet = chalet;
        //String vue = drawable.getVue();
        //drawPlan(Graphics g);
    }


    public void drawPlan(Graphics g){

        Map<Vue, ArrayList<ArrayList<Imperial>>> sommetsPlan = drawable.getSommets();
        ArrayList<ArrayList<Imperial>> coordonnees =  sommetsPlan.get(drawable.PLAN);


        ArrayList<Imperial> xCoords = coordonnees.get(0);
        ArrayList<Imperial>  yCoords = coordonnees.get(1);


            //On boucle pour les 4 murs
        for (int k = 0; k < 4; k++) {
            Polygon poly = new Polygon(xCoords, yCoords, );

            //On boucle cette fois-ci sur chacun des sommets(8)
            for (int j = 0; j < 8; j++) {
                poly.addPoint(xCoords.get(k + j).getValue(), yCoords.get(k + j).getValue());
            }

                // Murs Façades
            if (k == 0 || k == 2) {
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