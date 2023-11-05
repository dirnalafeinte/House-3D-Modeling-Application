package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.List;

import ca.ulaval.glo2004.domain.util.Coordonnee;




public class AfficheurPlan{

    private final Mur mur;

    public AfficheurPlan(Mur mur) {
        this.mur = mur;
    }

    public void drawPlan(Graphics g){

       List<Coordonnee> coordonnees = mur.getSommets().get(Vue.PLAN);

       if (coordonnees != null && coordonnees.size()==8) {


           //On boucle pour les 4 murs
           for (int k = 0; k < 4; k++) {
           Polygon poly = new Polygon();

            //On boucle cette fois-ci sur chacun des sommets(8)
            for (int j = k*2; j < k*2 +4; j++) {
                int index = j % coordonnees.size();
                Coordonnee coord = coordonnees.get(index);
                double xValue = coord.getX().toInches();
                double yValue = coord.getY().toInches();
                poly.addPoint((int)xValue, (int)yValue);
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