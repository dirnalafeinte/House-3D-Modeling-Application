package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportFiniMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_FINI = "Panneaux finis";
    private final MainWindow mainWindow;

    public ExportFiniMenuItem(MainWindow mainWindow) {
        super(TEXT_EXPORT_FINI);
        this.mainWindow = mainWindow;
    }
}
