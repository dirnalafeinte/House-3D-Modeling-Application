package ca.ulaval.glo2004.gui.menu.vueMenu;

import ca.ulaval.glo2004.domain.ChaletController;
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
    private final ChaletController controller;
    private final VueMenuItem vuePlanMenuItem = new VueMenuItem(TEXT_VUE_PLAN, Vue.PLAN);
    private final VueMenuItem vueFacadeMenuItem = new VueMenuItem(TEXT_VUE_FACADE, Vue.FACADE);
    private final VueMenuItem vueGaucheMenuItem = new VueMenuItem(TEXT_VUE_GAUCHE, Vue.GAUCHE);
    private final VueMenuItem vueArriereMenuItem = new VueMenuItem(TEXT_VUE_ARRIERE, Vue.ARRIERE);
    private final VueMenuItem vueDroiteMenuItem = new VueMenuItem(TEXT_VUE_DROITE, Vue.DROITE);

    public VueMenu(ChaletController controller) {
        super(TEXT_VUE_MENU);
        this.controller = controller;
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
