package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportFiniMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_FINI = "Panneaux finis";
    private final ChaletController controller;

    public ExportFiniMenuItem(ChaletController controller) {
        super(TEXT_EXPORT_FINI);
        this.controller = controller;
    }
}
