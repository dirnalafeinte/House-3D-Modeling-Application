package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class OuvrirMenuItem extends JMenuItem {
    private static final String TEXT_OUVRIR = "Ouvrir";
    private final ChaletController controller;

    public OuvrirMenuItem(ChaletController controller) {
        super(TEXT_OUVRIR);
        this.controller = controller;
        init();
    }

    private void init() {
    }
}
