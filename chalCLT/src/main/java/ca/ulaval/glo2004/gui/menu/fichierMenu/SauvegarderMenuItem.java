package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class SauvegarderMenuItem extends JMenuItem {
    private static final String TEXT_SAUVEGARDER = "Sauvegarder";
    private final ChaletController controller;

    public SauvegarderMenuItem(ChaletController controller) {
        super(TEXT_SAUVEGARDER);
        this.controller = controller;
        init();
    }

    private void init() {
    }
}
