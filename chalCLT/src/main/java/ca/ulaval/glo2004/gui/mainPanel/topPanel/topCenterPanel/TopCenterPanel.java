package ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.ClickMode;

import javax.swing.*;

public class TopCenterPanel extends JPanel {
    private static final String TEXT_MODE = "Mode: ";
    private static final String TEXT_SELECTION = "Sélection";
    private static final String TEXT_AJOUT_PORTE = "Ajout porte";
    private static final String TEXT_AJOUT_FENETRE = "Ajout fenêtre";
    private final ChaletController controller;
    private final JLabel modeLabel = new JLabel(TEXT_MODE);
    private final ClickModeToggleButton selectionToggleButton;
    private final ClickModeToggleButton ajoutPorteToggleButton;
    private final ClickModeToggleButton ajoutFenetreToggleButton;
    private final ButtonGroup clickModeButtonGroup = new ButtonGroup();

    public TopCenterPanel(ChaletController controller) {
        this.controller = controller;
        selectionToggleButton = new ClickModeToggleButton(TEXT_SELECTION, controller, ClickMode.SELECTION);
        ajoutPorteToggleButton = new ClickModeToggleButton(TEXT_AJOUT_PORTE, controller, ClickMode.AJOUT_PORTE);
        ajoutFenetreToggleButton = new ClickModeToggleButton(TEXT_AJOUT_FENETRE, controller, ClickMode.AJOUT_FENETRE);
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
