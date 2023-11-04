package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import ca.ulaval.glo2004.domain.ChaletController;

import javax.swing.*;

public class UndoButton extends JButton {
    private static final String TEXT_UNDO_BUTTON = "Undo";
    private final ChaletController controller;

    public UndoButton(ChaletController controller) {
        super(TEXT_UNDO_BUTTON);
        this.controller = controller;
        init();
    }

    private void init() {
    }
}
