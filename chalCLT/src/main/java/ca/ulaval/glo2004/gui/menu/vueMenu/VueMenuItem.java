package ca.ulaval.glo2004.gui.menu.vueMenu;

import ca.ulaval.glo2004.domain.Vue;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.action.QuitterSelectAction;
import ca.ulaval.glo2004.gui.action.SelectVueAction;

import javax.swing.*;

public class VueMenuItem extends JMenuItem {
    private MainWindow mainWindow;
    private final Vue vue;

    public VueMenuItem(String text, MainWindow mainWindow, Vue vue) {
        super(text);
        this.mainWindow = mainWindow;
        this.vue = vue;
        init();
    }

    private void init() {
        addActionListener(new SelectVueAction(mainWindow, vue));
    }
}
