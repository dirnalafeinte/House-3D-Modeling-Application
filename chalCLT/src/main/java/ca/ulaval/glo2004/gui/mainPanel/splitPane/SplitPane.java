package ca.ulaval.glo2004.gui.mainPanel.splitPane;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel.CenterPanel;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.RightPanel;

import javax.swing.*;

public class SplitPane extends JSplitPane {
    private static final Double RESIZE_WEIGHT = 0.8;
    private final ChaletController controller;
    private final CenterPanel centerPanel;
    private final RightPanel rightPanel;
    
    public SplitPane(ChaletController controller) {
        this.controller = controller;
        centerPanel = new CenterPanel(controller);
        rightPanel = new RightPanel(controller);
        init();
    }

    private void init() {
        setResizeWeight(RESIZE_WEIGHT);

        setLeftComponent(centerPanel);
        setRightComponent(rightPanel);
    }
}
