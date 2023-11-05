package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu.ExportMenu;

import javax.swing.*;

public class FichierMenu extends JMenu {
    private static final String TEXT_FICHIER_MENU = "Fichier";
    private final MainWindow mainWindow;
    private final OuvrirMenuItem ouvrirMenuItem;
    private final SauvegarderMenuItem sauvegarderMenuItem;
    private final ExportMenu exportMenu;
    private final QuitterMenuItem quitterMenuItem = new QuitterMenuItem();

    public FichierMenu(MainWindow mainWindow) {
        super(TEXT_FICHIER_MENU);
        this.mainWindow = mainWindow;
        ouvrirMenuItem = new OuvrirMenuItem(mainWindow);
        sauvegarderMenuItem = new SauvegarderMenuItem(mainWindow);
        exportMenu = new ExportMenu(mainWindow);
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
