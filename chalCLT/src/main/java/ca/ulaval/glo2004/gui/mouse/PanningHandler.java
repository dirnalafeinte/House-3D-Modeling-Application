package ca.ulaval.glo2004.gui.mouse;

import ca.ulaval.glo2004.domain.drawers.Afficheur;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class PanningHandler implements MouseMotionListener {

    private final MainWindow mainWindow;

    public PanningHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private Afficheur afficheur() {
        return mainWindow.getController().afficheur;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Component sourceComponent = e.getComponent();
        if (sourceComponent instanceof DrawingPanel) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            afficheur().setxOffset(mouseX - afficheur().getxChalet());
            afficheur().setyOffset(mouseY - afficheur().getyChalet());
            afficheur().setxChalet(mouseX);
            afficheur().setyChalet(mouseY);
            mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
