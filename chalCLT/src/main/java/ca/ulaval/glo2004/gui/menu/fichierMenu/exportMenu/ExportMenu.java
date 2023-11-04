package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportMenu extends JMenu {
    private static final String TEXT_EXPORT = "Exporter";
    private final ChaletController controller;
    private final ExportBrutMenuItem exportBrutMenuItem;
    private final ExportFiniMenuItem exportFiniMenuItem;
    private final ExportRetraitMenuItem exportRetraitMenuItem;
    
    public ExportMenu(ChaletController controller) {
        super(TEXT_EXPORT);
        this.controller = controller;
        exportBrutMenuItem = new ExportBrutMenuItem(controller);
        exportFiniMenuItem = new ExportFiniMenuItem(controller);
        exportRetraitMenuItem = new ExportRetraitMenuItem(controller);
        init();
    }

    private void init() {
        add(exportBrutMenuItem);
        add(exportFiniMenuItem);
        add(exportRetraitMenuItem);
    }
}
