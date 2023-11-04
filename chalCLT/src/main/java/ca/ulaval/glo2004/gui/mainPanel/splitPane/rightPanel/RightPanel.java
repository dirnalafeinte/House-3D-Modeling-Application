package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane.TabbedPane;

import javax.swing.*;

public class RightPanel extends JPanel {
    private final ChaletController controller;
    private final TabbedPane tabbedPane;

    public RightPanel(ChaletController controller) {
        this.controller = controller;
        tabbedPane = new TabbedPane(controller);
        init();
    }

    private void init() {
        add(tabbedPane);
    }
}
