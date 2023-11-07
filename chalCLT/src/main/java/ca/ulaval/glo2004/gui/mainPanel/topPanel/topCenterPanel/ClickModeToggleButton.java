package ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.ClickMode;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class ClickModeToggleButton extends JToggleButton {
    private final MainWindow mainWindow;
    private final ClickMode clickmode;

    public ClickModeToggleButton(String text, MainWindow mainWindow, ClickMode clickmode) {
        super(text);
        this.mainWindow = mainWindow;
        this.clickmode = clickmode;
        init();
    }

    private void init() {
        setEnabled(false);
    }
}
