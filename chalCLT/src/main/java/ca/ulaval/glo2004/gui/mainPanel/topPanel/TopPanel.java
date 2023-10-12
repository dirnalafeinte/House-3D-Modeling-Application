package ca.ulaval.glo2004.gui.mainPanel.topPanel;

import ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel.TopCenterPanel;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel.TopLeftPanel;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel.TopRightPanel;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new GridLayout(1, 3);
    private final TopLeftPanel topLeftPanel = new TopLeftPanel();
    private final TopCenterPanel topCenterPanel = new TopCenterPanel();
    private final TopRightPanel topRightPanel = new TopRightPanel();

    public TopPanel() {
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(topLeftPanel);
        add(topCenterPanel);
        add(topRightPanel);
    }
}
