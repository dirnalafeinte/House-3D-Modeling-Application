package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu.ExportMenu;

import javax.swing.*;

public class FichierMenu extends JMenu {
    private static final String TEXT_FICHIER_MENU = "Fichier";
    private final ChaletController controller;
    private final OuvrirMenuItem ouvrirMenuItem;
    private final SauvegarderMenuItem sauvegarderMenuItem;
    private final ExportMenu exportMenu;
    private final QuitterMenuItem quitterMenuItem = new QuitterMenuItem();

    public FichierMenu(ChaletController controller) {
        super(TEXT_FICHIER_MENU);
        this.controller = controller;
        ouvrirMenuItem = new OuvrirMenuItem(controller);
        sauvegarderMenuItem = new SauvegarderMenuItem(controller);
        exportMenu = new ExportMenu(controller);
        init();
    }

    private void init() {
        add(ouvrirMenuItem);
        add(sauvegarderMenuItem);
        add(exportMenu);
        add(new JSeparator());
        add(quitterMenuItem);
    }
}
