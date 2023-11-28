package ca.ulaval.glo2004.gui.menu.vueMenu;

import ca.ulaval.glo2004.domain.Vue;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class VueMenu extends JMenu {
    private static final String TEXT_VUE_MENU = "Vue";
    private static final String TEXT_VUE_PLAN = "Plan";
    private static final String TEXT_VUE_FACADE = "Façade";
    private static final String TEXT_VUE_GAUCHE = "Gauche";
    private static final String TEXT_VUE_ARRIERE = "Arrière";
    private static final String TEXT_VUE_DROITE = "Droit";
    private final MainWindow mainWindow;
    private final VueMenuItem vuePlanMenuItem;
    private final VueMenuItem vueFacadeMenuItem;
    private final VueMenuItem vueGaucheMenuItem;
    private final VueMenuItem vueArriereMenuItem;
    private final VueMenuItem vueDroiteMenuItem;

    public VueMenu(MainWindow mainWindow) {
        super(TEXT_VUE_MENU);
        this.mainWindow = mainWindow;
        vuePlanMenuItem = new VueMenuItem(TEXT_VUE_PLAN, mainWindow, Vue.PLAN);
        vueFacadeMenuItem = new VueMenuItem(TEXT_VUE_FACADE, mainWindow, Vue.FACADE);
        vueGaucheMenuItem = new VueMenuItem(TEXT_VUE_GAUCHE, mainWindow, Vue.GAUCHE);
        vueArriereMenuItem = new VueMenuItem(TEXT_VUE_ARRIERE, mainWindow, Vue.ARRIERE);
        vueDroiteMenuItem = new VueMenuItem(TEXT_VUE_DROITE, mainWindow, Vue.DROITE);
        init();
    }

    private void init() {
        add(vuePlanMenuItem);
        add(vueFacadeMenuItem);
        add(vueGaucheMenuItem);
        add(vueArriereMenuItem);
        add(vueDroiteMenuItem);
    }

    public VueMenuItem[] getMenuItems() {
        return new VueMenuItem[]{vuePlanMenuItem, vueFacadeMenuItem, vueGaucheMenuItem, vueArriereMenuItem, vueDroiteMenuItem};
    }
}
