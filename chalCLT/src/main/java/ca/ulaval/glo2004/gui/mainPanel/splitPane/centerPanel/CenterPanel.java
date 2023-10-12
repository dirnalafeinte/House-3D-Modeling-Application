package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import javax.swing.*;

public class CenterPanel extends JPanel {
    private final DrawingPanel drawingPanel = new DrawingPanel();

    public CenterPanel() {
        init();
    }

    private void init() {
        add(drawingPanel);
    }
}
