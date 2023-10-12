package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel;

import ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane.TabbedPane;

import javax.swing.*;

public class RightPanel extends JPanel {
    private final TabbedPane tabbedPane = new TabbedPane();

    public RightPanel() {
        init();
    }

    private void init() {

        add(tabbedPane);
    }
}
