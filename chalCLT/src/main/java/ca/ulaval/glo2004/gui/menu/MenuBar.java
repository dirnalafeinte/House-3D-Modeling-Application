package ca.ulaval.glo2004.gui.menu;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.menu.fichierMenu.FichierMenu;
import ca.ulaval.glo2004.gui.menu.vueMenu.VueMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final ChaletController controller;
    private final FichierMenu fichierMenu;
    private final VueMenu vueMenu;

    public MenuBar(ChaletController controller) {
        this.controller = controller;
        fichierMenu = new FichierMenu(controller);
        vueMenu = new VueMenu(controller);
        init();
    }

    private void init() {
        add(fichierMenu);
        add(vueMenu);
    }
}
