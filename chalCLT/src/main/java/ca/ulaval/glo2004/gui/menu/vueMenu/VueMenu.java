package ca.ulaval.glo2004.gui.menu.vueMenu;

import ca.ulaval.glo2004.domain.Vue;

import javax.swing.*;

public class VueMenu extends JMenu {
    private static final String TEXT_VUE_MENU = "Vue";
    private static final String TEXT_VUE_PLAN = "Plan";
    private final VueMenuItem vuePlanMenuItem = new VueMenuItem(TEXT_VUE_PLAN, Vue.PLAN);
    private static final String TEXT_VUE_FACADE = "Façade";
    private final VueMenuItem vueFacadeMenuItem = new VueMenuItem(TEXT_VUE_FACADE, Vue.FACADE);
    private static final String TEXT_VUE_GAUCHE = "Gauche";
    private final VueMenuItem vueGaucheMenuItem = new VueMenuItem(TEXT_VUE_GAUCHE, Vue.GAUCHE);
    private static final String TEXT_VUE_ARRIERE = "Arrière";
    private final VueMenuItem vueArriereMenuItem = new VueMenuItem(TEXT_VUE_ARRIERE, Vue.ARRIERE);
    private static final String TEXT_VUE_DROIT = "Droit";
    private final VueMenuItem vueDroitMenuItem = new VueMenuItem(TEXT_VUE_DROIT, Vue.DROIT);

    public VueMenu() {
        super(TEXT_VUE_MENU);
        init();
    }

    private void init() {
        add(vuePlanMenuItem);
        add(vueFacadeMenuItem);
        add(vueGaucheMenuItem);
        add(vueArriereMenuItem);
        add(vueDroitMenuItem);
    }
}
