package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import ca.ulaval.glo2004.domain.Afficheur;
import ca.ulaval.glo2004.domain.ChaletController;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private static final Color BACKGROUD_COLOR = Color.WHITE;
    private final MainWindow mainWindow = null;

    public DrawingPanel(ChaletController controller) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setBackground(BACKGROUD_COLOR);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Afficheur afficheur = new Afficheur(mainWindow.chaletController.chalet, mainWindow.chaletController.vue);
        
        afficheur.draw(g);
    }
}
