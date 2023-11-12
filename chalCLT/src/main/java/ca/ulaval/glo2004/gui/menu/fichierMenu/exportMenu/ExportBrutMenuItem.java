package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportBrutMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_BRUT = "Panneaux bruts";
    private final MainWindow mainWindow;

    public ExportBrutMenuItem(MainWindow mainWindow) {
        super(TEXT_EXPORT_BRUT);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
    }
}
