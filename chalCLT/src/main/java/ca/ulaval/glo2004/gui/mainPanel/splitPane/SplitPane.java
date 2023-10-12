package ca.ulaval.glo2004.gui.mainPanel.splitPane;

import ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel.CenterPanel;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.RightPanel;

import javax.swing.*;

public class SplitPane extends JSplitPane {
    private static final Double RESIZE_WEIGHT = 0.8;
    private final CenterPanel centerPanel = new CenterPanel();
    private final RightPanel rightPanel = new RightPanel();
    
    public SplitPane() {
        init();
    }

    private void init() {
        setResizeWeight(RESIZE_WEIGHT);

        setLeftComponent(centerPanel);
        setRightComponent(rightPanel);
    }
}
