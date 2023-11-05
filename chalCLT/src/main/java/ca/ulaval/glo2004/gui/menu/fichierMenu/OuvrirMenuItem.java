package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class OuvrirMenuItem extends JMenuItem {
    private static final String TEXT_OUVRIR = "Ouvrir";
    private final MainWindow mainWindow;

    public OuvrirMenuItem(MainWindow mainWindow) {
        super(TEXT_OUVRIR);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
    }
}
