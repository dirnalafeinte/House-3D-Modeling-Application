package ca.ulaval.glo2004.gui.mouse;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.accessoires.Accessoire;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MouseMotionListener implements java.awt.event.MouseMotionListener {
    MainWindow mainWindow;

    public MouseMotionListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        List<Drawable> components = controller().getChalet().getVisibleComponents(controller().afficheur.getVue());
        List<Accessoire> accessoires = new ArrayList<>();
        Point mousePoint = e.getPoint();
        int hauteurPixel = mainWindow.getController().getHauteurChalet();
        int invertedY = hauteurPixel - mousePoint.y;
        Coordonnee mouseCoord = controller().getMousePostionInCoordonnee(mousePoint.x, invertedY);

        for (Drawable drawable : components) {
            if (drawable instanceof Accessoire)
                accessoires.add((Accessoire) drawable);
        }

        for (Accessoire accessoire : accessoires) {
            if (accessoire.isObjectSelected()) { //&& controller().getObjectAtCoord(mouseCoord) == accessoire
                accessoire.updateCoordonnee(mouseCoord.getX(), mouseCoord.getY());
                controller().saveState();
                controller().notifyObservers();
                accessoire.validate();
                }
            }
        }

    @Override
    public void mouseMoved(MouseEvent e) {
        List<Drawable> components = controller().getChalet().getVisibleComponents(controller().afficheur.getVue());
        Point mousePoint = e.getPoint();

        Coordonnee mouseCoord = controller().getMousePostionInCoordonnee(mousePoint.x, mousePoint.y);
        StringBuilder details = new StringBuilder();

        for (Drawable drawable : components) {
            if (controller().getObjectAtCoord(mouseCoord) == drawable) {
                if (drawable instanceof Mur) {
                    details.append("Mur: Hauteur = ").append(((Mur) drawable).getHauteur())
                            .append(", Largeur = ").append(((Mur) drawable).getLargeur())
                            .append(", Épaisseur = ").append(((Mur) drawable).getEpaisseur()).append("\n");
                } else if (drawable instanceof Toit) {
                    details.append("Toit: Hauteur = ").append(((Toit) drawable).getHauteurToit())
                            .append(", Longueur = ").append(((Toit) drawable).getLongueurToit())
                            .append(", Épaisseur = ").append(((Toit) drawable).getEpaisseur().toString()).append("\n");
                } else if (drawable instanceof Rallonge) {
                    details.append("Rallonge: Hauteur = ").append(((Rallonge) drawable).getHauteurRallonge())
                            .append(", Longueur = ").append(((Rallonge) drawable).getLongueurRallonge())
                            .append(", Épaisseur = ").append(((Rallonge) drawable).getEpaisseur().toString()).append("\n");
                } else if (drawable instanceof Pignon) {
                    details.append("Pignon: Hauteur = ").append(((Pignon) drawable).getHauteurPignon())
                            .append(", Longueur = ").append(((Pignon) drawable).getLongueurPignon())
                            .append(", Épaisseur = ").append(((Pignon) drawable).getEpaisseur().toString()).append("\n");
                }
            }
        }

        JTextArea textArea = mainWindow.getMainPanel().getSplitPane().getRightPanel().getTextArea();
        textArea.setText(details.toString());

        mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
    }

    private ChaletController controller() {
        return mainWindow.getController();
    }
}
