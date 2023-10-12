package ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel;

import ca.ulaval.glo2004.gui.ClickMode;

import javax.swing.*;

public class TopCenterPanel extends JPanel {
    private static final String TEXT_MODE = "Mode: ";
    private final JLabel modeLabel = new JLabel(TEXT_MODE);
    private static final String TEXT_SELECTION = "Sélection";
    private final ClickModeToggleButton selectionToggleButton = new ClickModeToggleButton(TEXT_SELECTION, ClickMode.SELECTION);
    private static final String TEXT_AJOUT_PORTE = "Ajout porte";
    private final ClickModeToggleButton ajoutPorteToggleButton = new ClickModeToggleButton(TEXT_AJOUT_PORTE, ClickMode.AJOUT_PORTE);
    private static final String TEXT_AJOUT_FENETRE = "Ajout fenêtre";
    private final ClickModeToggleButton ajoutFenetreToggleButton = new ClickModeToggleButton(TEXT_AJOUT_FENETRE, ClickMode.AJOUT_FENETRE);
    private final ButtonGroup clickModeButtonGroup = new ButtonGroup();

    public TopCenterPanel() {
        init();
    }

    private void init() {
        add(modeLabel);
        add(selectionToggleButton);
        add(ajoutPorteToggleButton);
        add(ajoutFenetreToggleButton);

        clickModeButtonGroup.add(selectionToggleButton);
        clickModeButtonGroup.add(ajoutPorteToggleButton);
        clickModeButtonGroup.add(ajoutFenetreToggleButton);

        selectionToggleButton.setSelected(true);
    }
}
