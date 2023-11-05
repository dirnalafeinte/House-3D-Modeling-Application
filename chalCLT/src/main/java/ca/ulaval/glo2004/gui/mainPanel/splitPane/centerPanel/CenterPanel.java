package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import ca.ulaval.glo2004.domain.ChaletController;

import javax.swing.*;

public class CenterPanel extends JPanel {
    private final ChaletController controller;
    private final DrawingPanel drawingPanel;

    public CenterPanel(ChaletController controller) {
        this.controller = controller;
        drawingPanel = new DrawingPanel(controller);
        init();
    }

    private void init() {
        add(drawingPanel);
    }
}
