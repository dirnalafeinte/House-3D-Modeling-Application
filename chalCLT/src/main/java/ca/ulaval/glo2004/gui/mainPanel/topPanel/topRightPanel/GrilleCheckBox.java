package ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class GrilleCheckBox extends JCheckBox {
    private static final String TEXT_GRILLE_CHECK_BOX = "Grille";
    private final MainWindow mainWindow;

    public GrilleCheckBox(MainWindow mainWindow) {
        super(TEXT_GRILLE_CHECK_BOX);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setEnabled(true);
    }

}
