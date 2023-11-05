package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportBrutMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_BRUT = "Panneaux bruts";
    private final ChaletController controller;

    public ExportBrutMenuItem(ChaletController controller) {
        super(TEXT_EXPORT_BRUT);
        this.controller = controller;
        init();
    }

    private void init() {
    }
}
