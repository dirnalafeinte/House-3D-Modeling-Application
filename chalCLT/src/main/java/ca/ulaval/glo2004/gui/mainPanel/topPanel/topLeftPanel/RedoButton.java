package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class RedoButton extends JButton {
    private static final String TEXT_REDO_BUTTON = "Redo";
    private final MainWindow mainWindow;

    public RedoButton(MainWindow mainWindow) {
        super(TEXT_REDO_BUTTON);
        this.mainWindow = mainWindow;
    }
}
