package ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.ClickMode;

import javax.swing.*;

public class ClickModeToggleButton extends JToggleButton {
    private final ClickMode clickmode;
    private final ChaletController controller;

    public ClickModeToggleButton(String text, ChaletController controller, ClickMode clickmode) {
        super(text);
        this.controller = controller;
        this.clickmode = clickmode;
        init();
    }

    private void init() {
    }
}
