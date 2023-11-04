package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;

import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.util.Coordonnee;




public class AfficheurPlan extends Afficheur {
    private Chalet chalet;

    public AfficheurPlan(Chalet chalet) {
        this.chalet = chalet;
    }


    public void drawPlan(Graphics g){

       List<Coordonnee> coordonnees = drawable.getSommets().get(Vue.PLAN);


            //On boucle pour les 4 murs
        for (int k = 0; k < 4; k++) {
            Polygon poly = new Polygon(xCoords, yCoords, sommets.size());

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