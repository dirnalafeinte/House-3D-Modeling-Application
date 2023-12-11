package ca.ulaval.glo2004.gui.mouse;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.domain.Drawable;
import ca.ulaval.glo2004.domain.accessoires.Accessoire;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.gui.MainWindow;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class AccessoryMovement implements MouseMotionListener {
    MainWindow mainWindow;

    public AccessoryMovement(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().getMouseListner().setDragging(true);
        List<Drawable> components = controller().getChalet().getVisibleComponents(controller().afficheur.getVue());
        List<Accessoire> accessoires = new ArrayList<>();
        Point mousePoint = e.getPoint();
        Coordonnee mouseCoord = controller().getMousePostionInCoordonnee(mousePoint.x, mousePoint.y);

        for (Drawable drawable : components) {
            if (drawable instanceof Accessoire)
                accessoires.add((Accessoire) drawable);
        }

        for (Accessoire accessoire : accessoires) {
            if (accessoire.isObjectSelected()) { //&& controller().getObjectAtCoord(mouseCoord) == accessoire
                accessoire.updateCoordonnee(mouseCoord.getX(), mouseCoord.getY());
                mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
                accessoire.validate();
                }
            }
        }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private ChaletController controller() {
        return mainWindow.getController();
    }
}
