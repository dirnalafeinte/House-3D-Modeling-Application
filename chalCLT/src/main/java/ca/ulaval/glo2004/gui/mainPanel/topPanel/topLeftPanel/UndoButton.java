package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class UndoButton extends JButton {
    private static final String TEXT_UNDO_BUTTON = "Undo";
    private final MainWindow mainWindow;

    public UndoButton(MainWindow mainWindow) {
        super(TEXT_UNDO_BUTTON);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
    }
}
