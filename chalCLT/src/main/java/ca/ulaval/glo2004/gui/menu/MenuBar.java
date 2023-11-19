package ca.ulaval.glo2004.gui.menu;

import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.menu.fichierMenu.FichierMenu;
import ca.ulaval.glo2004.gui.menu.vueMenu.VueMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final MainWindow mainWindow;
    private final FichierMenu fichierMenu;
    private final VueMenu vueMenu;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        fichierMenu = new FichierMenu(mainWindow);
        vueMenu = new VueMenu(mainWindow);
        init();
    }

    private void init() {
        add(fichierMenu);
        add(vueMenu);
    }

    public VueMenu getVueMenu() {
        return vueMenu;
    }
}
