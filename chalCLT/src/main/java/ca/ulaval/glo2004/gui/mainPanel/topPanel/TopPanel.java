package ca.ulaval.glo2004.gui.mainPanel.topPanel;

import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topCenterPanel.TopCenterPanel;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel.TopLeftPanel;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel.TopRightPanel;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new GridLayout(1, 3);
    private final MainWindow mainWindow;
    private final TopLeftPanel topLeftPanel;
    private final TopCenterPanel topCenterPanel;
    private final TopRightPanel topRightPanel;

    public TopPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        topLeftPanel = new TopLeftPanel(mainWindow);
        topCenterPanel = new TopCenterPanel(mainWindow);
        topRightPanel = new TopRightPanel(mainWindow);
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(topLeftPanel);
        add(topCenterPanel);
        add(topRightPanel);
    }

    public TopRightPanel getTopRightPanel() {
        return topRightPanel;
    }
}
