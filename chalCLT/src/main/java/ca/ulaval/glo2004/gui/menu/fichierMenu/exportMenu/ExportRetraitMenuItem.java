package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportRetraitMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_RETRAIT = "Retraits";
    private final MainWindow mainWindow;

    public ExportRetraitMenuItem(MainWindow mainWindow) {
        super(TEXT_EXPORT_RETRAIT);
        this.mainWindow = mainWindow;
    }
}
