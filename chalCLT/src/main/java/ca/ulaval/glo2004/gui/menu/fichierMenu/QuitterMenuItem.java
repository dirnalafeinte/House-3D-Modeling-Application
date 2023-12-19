package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.gui.action.QuitterSelectAction;

import javax.swing.*;

public class QuitterMenuItem extends JMenuItem {
    private static final String TEXT_QUITTER = "Quitter";

    public QuitterMenuItem() {
        super(TEXT_QUITTER);

        init();
    }

    private void init() {
        addActionListener(new QuitterSelectAction());
    }
}
