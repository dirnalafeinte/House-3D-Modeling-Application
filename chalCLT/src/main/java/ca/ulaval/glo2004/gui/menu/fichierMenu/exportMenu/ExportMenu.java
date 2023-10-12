package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import javax.swing.*;

public class ExportMenu extends JMenu {
    private static final String TEXT_EXPORT = "Exporter";
    private final ExportBrutMenuItem exportBrutMenuItem = new ExportBrutMenuItem();
    private final ExportFiniMenuItem exportFiniMenuItem = new ExportFiniMenuItem();
    private final ExportRetraitMenuItem exportRetraitMenuItem = new ExportRetraitMenuItem();
    
    public ExportMenu() {
        super(TEXT_EXPORT);

        init();
    }

    private void init() {
        add(exportBrutMenuItem);
        add(exportFiniMenuItem);
        add(exportRetraitMenuItem);
    }
}
