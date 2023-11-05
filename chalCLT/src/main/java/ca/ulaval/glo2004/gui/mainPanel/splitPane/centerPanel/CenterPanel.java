package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class CenterPanel extends JPanel {
    private final MainWindow mainWindow;
    private final DrawingPanel drawingPanel;

    public CenterPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        drawingPanel = new DrawingPanel(mainWindow);
        init();
    }

    private void init() {
        add(drawingPanel);
    }
}
