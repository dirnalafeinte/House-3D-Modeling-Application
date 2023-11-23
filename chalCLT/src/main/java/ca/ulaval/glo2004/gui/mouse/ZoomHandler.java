package ca.ulaval.glo2004.gui.mouse;

import ca.ulaval.glo2004.domain.drawers.Afficheur;
import ca.ulaval.glo2004.gui.MainWindow;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ZoomHandler implements MouseWheelListener {
    private final MainWindow mainWindow;
    public ZoomHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private Afficheur afficheur() {
        return mainWindow.getController().afficheur;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        double mouseX = e.getX();
        double mouseY = e.getY();

        double scale = (notches < 0) ? 1.05 : 0.95;
        afficheur().setZoomFactor(afficheur().getZoomFactor()*scale);

        Point originalMousePos = new Point(
                (int)(mouseX / afficheur().getLastZoomFactor() - afficheur().getxOffset()),
                (int)(mouseY / afficheur().getLastZoomFactor() - afficheur().getyOffset())
        );

        double newOffsetX = mouseX / afficheur().getZoomFactor() - originalMousePos.getX();
        double newOffsetY = mouseY / afficheur().getZoomFactor() - originalMousePos.getY();
        afficheur().setxOffset(newOffsetX);
        afficheur().setyOffset(newOffsetY);

        mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
    }

}
