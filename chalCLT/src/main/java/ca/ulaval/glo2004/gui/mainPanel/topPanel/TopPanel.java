package ca.ulaval.glo2004.gui.mainPanel.topPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel.TopCenterPanel;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel.TopLeftPanel;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel.TopRightPanel;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new GridLayout(1, 3);
    private final ChaletController controller;
    private final TopLeftPanel topLeftPanel;
    private final TopCenterPanel topCenterPanel;
    private final TopRightPanel topRightPanel;

    public TopPanel(ChaletController controller) {
        this.controller = controller;
        topLeftPanel = new TopLeftPanel(controller);
        topCenterPanel = new TopCenterPanel(controller);
        topRightPanel = new TopRightPanel(controller);
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(topLeftPanel);
        add(topCenterPanel);
        add(topRightPanel);
    }
}
