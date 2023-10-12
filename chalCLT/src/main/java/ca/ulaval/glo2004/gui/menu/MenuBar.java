package ca.ulaval.glo2004.gui.menu;

import ca.ulaval.glo2004.gui.menu.fichierMenu.FichierMenu;
import ca.ulaval.glo2004.gui.menu.vueMenu.VueMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final FichierMenu fichierMenu = new FichierMenu();
    private final VueMenu vueMenu = new VueMenu();

    public MenuBar() {
        init();
    }

    private void init() {
        add(fichierMenu);
        add(vueMenu);
    }
}
