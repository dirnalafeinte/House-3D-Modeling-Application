package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class SauvegarderMenuItem extends JMenuItem {
    private static final String TEXT_SAUVEGARDER = "Sauvegarder";
    private final MainWindow mainWindow;

    public SauvegarderMenuItem(MainWindow mainWindow) {
        super(TEXT_SAUVEGARDER);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
    }
}
