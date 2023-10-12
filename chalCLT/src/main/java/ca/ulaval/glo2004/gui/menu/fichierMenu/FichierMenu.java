package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu.ExportMenu;

import javax.swing.*;

public class FichierMenu extends JMenu {
    private static final String TEXT_FICHIER_MENU = "Fichier";
    private final OuvrirMenuItem ouvrirMenuItem = new OuvrirMenuItem();
    private final SauvegarderMenuItem sauvegarderMenuItem = new SauvegarderMenuItem();
    private final ExportMenu exportMenu = new ExportMenu();
    private final QuitterMenuItem quitterMenuItem = new QuitterMenuItem();

    public FichierMenu() {
        super(TEXT_FICHIER_MENU);
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
