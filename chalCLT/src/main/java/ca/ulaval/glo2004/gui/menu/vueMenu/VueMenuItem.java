package ca.ulaval.glo2004.gui.menu.vueMenu;

import ca.ulaval.glo2004.domain.Vue;

import javax.swing.*;

public class VueMenuItem extends JMenuItem {
    private final Vue vue;

    public VueMenuItem(String text, Vue vue) {
        super(text);
        this.vue = vue;
        
        init();
    }

    private void init() {
    }
}
