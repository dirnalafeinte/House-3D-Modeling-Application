package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ExportRetraitMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_RETRAIT = "Retraits";
    private final ChaletController controller;

    public ExportRetraitMenuItem(ChaletController controller) {
        super(TEXT_EXPORT_RETRAIT);
        this.controller = controller;
    }
}
