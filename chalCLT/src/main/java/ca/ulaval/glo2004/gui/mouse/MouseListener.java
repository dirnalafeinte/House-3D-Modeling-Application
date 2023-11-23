package ca.ulaval.glo2004.gui.mouse;

import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel.DrawingPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener{

    private final MainWindow mainWindow;

    public MouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component sourceComponent = e.getComponent();
        if (sourceComponent instanceof DrawingPanel) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            mainWindow.getController().afficheur.setxChalet(mouseX);
            mainWindow.getController().afficheur.setyChalet(mouseY);
            mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        Component sourceComponent = e.getComponent();
//        if (sourceComponent instanceof DrawingPanel) {
//            int mouseX = e.getX();
//            int mouseY = e.getY();
//            mainWindow.getController().afficheur.setxOffset(mouseX - mainWindow.getController().afficheur.getxChalet());
//            mainWindow.getController().afficheur.setyOffset(mouseY - mainWindow.getController().afficheur.getyChalet());
//            mainWindow.getController().afficheur.setxChalet(mouseX);
//            mainWindow.getController().afficheur.setyChalet(mouseY);
//            mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
//        }
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//
//    }
}
