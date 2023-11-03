package ca.ulaval.glo2004.domain;

import javax.swing.*;
import java.awt.*;


public class AfficheurPlan extends Afficheur{
      private final Chalet chalet = new Chalet();

    public AfficheurPlan(ChaletController controller, Dimension initialDimension, Vue vue) {
        super(controller, initialDimension, vue);
    }

    public void draw(Graphics g) {
          String vue = chalet.getVue();
          drawPlan(g, vue);
      }
      public void drawPlan(Graphics g, String vue){
        Map<String, Mur> mapMur;
          mapMur = Mur.getmapMur();

          for (int i=0 ; i<4; i++) {
            Mur mur = Mur.getMur();
            Point[] sommets = mur.getSommet(vue);

            g.draw
            drawMur(g, mur);
        }
    }

      private void drawMur(Graphics g, Mur mur){
        Polygone poly = mur.getPolygone();
        g.drawPolygon(poly.x, poly.y, poly.width);
        g.fillPolygon();
        g.setColor(Color.BLUE);
    }

}
