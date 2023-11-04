package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;


public class AfficheurPlan extends Afficheur {
    private Drawable chalet;


    public AfficheurPlan(Drawable chalet) {
        super();
        this.chalet = chalet;
        //String vue = chalet.getVue();
        //drawPlan();
    }


    public void drawPlan(Graphics g) throws Exception {
        ArrayList<Coordonnee> sommetsPlan = new ArrayList<Coordonnee>();
        int i;

        //On jugera de la pertinence de cette exception ensemble
        //if (chalet.getSensDuToit() == null) {
            //throw new Exception("L'orientation du toit doit être définie");
        //}
        if (chalet.getChalet().getSensDuToit() == Orientation.FACADE || chalet.getChalet().getSensDuToit() == Orientation.ARRIERE) {
            for (i=0;i<chalet.calculateSommetsPlan1().size();i++){
                sommetsPlan.add(new Coordonnee( chalet.calculateSommetsPlan1().get(i).get(0),chalet.calculateSommetsPlan1().get(i).get(1)));
            }
        }
        else {
            for (i=0;i<chalet.calculateSommetsPlan2().size();i++){
                sommetsPlan.add(new Coordonnee( chalet.calculateSommetsPlan2().get(i).get(0),chalet.calculateSommetsPlan2().get(i).get(1)));
            }

            //On boucle pour les 4 murs
            for (int k = 0; k < 4; k++) {
                Polygon poly = new Polygon();

                //On boucle cette fois-ci sur chacun des sommets(8)
                for (int j = 0; j < 8; j++) {
                    //poly.addPoint(sommetsPlan.get(i + j).getX(), sommetsPlan.get(i + j).getY());
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