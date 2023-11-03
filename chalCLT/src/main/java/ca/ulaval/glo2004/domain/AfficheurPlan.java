package ca.ulaval.glo2004.domain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AfficheurPlan extends Afficheur{
    private Drawable chalet;


    public AfficheurPlan(Drawable chalet){
        super();
        this.chalet =  chalet;
        String vue = chalet.getVue();
        drawPlan(g, vue);
    }

    public void drawPlan(Graphics g) {
          ArrayList<Coordonnee> sommetsPlan;

          if (chalet.getSensDuToit() == null) {
              throw new Exception("L'orientation du toit doit être définie")
          }

          else if (chalet.getSensDuToit()== Orientation.FACADE || chalet.getSensDuToit() == Orientation.ARRIERE){
              sommetsPlan = chalet.calculateSommetsPlan1();
          }
          else  {
              sommetsPlan = chalet.calculateSommetsPlan2();




       for (for i = 0; i< 4; i++) {
           Polygon poly = new Polygon();

           for (int j=0; j<8, j++){
                poly.addPoint(sommetsPlan.get(i+j).getX(), coordonnee.Y());
       }
    }
       // Mur Façades
       if (i ==0 || i ==2) {
           g.setColor(Color.ORANGE);
       }
       else{
           g.setColor(Color.BLUE);
       }

       g.fillPolygon(poly);

}
