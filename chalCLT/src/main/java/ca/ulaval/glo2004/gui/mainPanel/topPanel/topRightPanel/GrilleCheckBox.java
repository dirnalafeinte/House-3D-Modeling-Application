package ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel;

import ca.ulaval.glo2004.domain.ChaletController;

import javax.swing.*;

public class GrilleCheckBox extends JCheckBox {
    private static final String TEXT_GRILLE_CHECK_BOX = "Grille";
    private final ChaletController controller;

    public GrilleCheckBox(ChaletController controller) {
        super(TEXT_GRILLE_CHECK_BOX);
        this.controller = controller;
        init();
    }

    private void init() {
    }
}
