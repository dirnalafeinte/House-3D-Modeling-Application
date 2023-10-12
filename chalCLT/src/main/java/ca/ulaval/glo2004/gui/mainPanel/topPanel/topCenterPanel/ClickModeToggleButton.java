package ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel;

import ca.ulaval.glo2004.gui.ClickMode;

import javax.swing.*;

public class ClickModeToggleButton extends JToggleButton {
    private final ClickMode clickmode;

    public ClickModeToggleButton(String text, ClickMode clickmode) {
        super(text);
        this.clickmode = clickmode;
        
        init();
    }

    private void init() {
    }
}
