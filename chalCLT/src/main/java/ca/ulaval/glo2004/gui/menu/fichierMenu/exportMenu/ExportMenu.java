package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportMenu extends JMenu {
    private static final String TEXT_EXPORT = "Exporter";
    private final MainWindow mainWindow;
    private final ExportBrutMenuItem exportBrutMenuItem;
    private final ExportFiniMenuItem exportFiniMenuItem;
    private final ExportRetraitMenuItem exportRetraitMenuItem;
    
    public ExportMenu(MainWindow mainWindow) {
        super(TEXT_EXPORT);
        this.mainWindow = mainWindow;
        exportBrutMenuItem = new ExportBrutMenuItem(mainWindow);
        exportFiniMenuItem = new ExportFiniMenuItem(mainWindow);
        exportRetraitMenuItem = new ExportRetraitMenuItem(mainWindow);
        init();
    }

    private void init() {
        add(exportBrutMenuItem);
        add(exportFiniMenuItem);
        add(exportRetraitMenuItem);
    }
}
