package ca.ulaval.glo2004.gui.mouse;

import ca.ulaval.glo2004.domain.Drawable;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalObjectSelection;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.gui.MainWindow;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {
    private final MainWindow mainWindow;
    public MouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Coordonnee mouseCoord = mainWindow.getController().getMousePostionInCoordonnee(x, y);
        Drawable drawable = mainWindow.getController().getObjectAtCoord(mouseCoord);
        singleSelection(drawable);
        toggleSelection(drawable);
        mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
    }

    private void toggleSelection(Drawable drawable) {
        if (drawable != null) {
            drawable.setObjectSelected(!drawable.isObjectSelected());
        }
    }

    private void singleSelection(Drawable drawable) {
        Drawable selectedObject = getObjectSelected();
        if (selectedObject != null && selectedObject != drawable) {
            selectedObject.setObjectSelected(false);
        }
    }

    private Drawable getObjectSelected() {
        for (Drawable object : mainWindow.getController().getChalet().getVisibleComponents(mainWindow.getController().afficheur.getVue())) {
            if (object.isObjectSelected()) {
                System.out.println(object);
                return object;
            }
        }
        return null;
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



}
