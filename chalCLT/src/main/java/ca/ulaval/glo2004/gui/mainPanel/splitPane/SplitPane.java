package ca.ulaval.glo2004.gui.mainPanel.splitPane;

import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel.CenterPanel;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.RightPanel;

import javax.swing.*;

public class SplitPane extends JSplitPane {
    private static final Double RESIZE_WEIGHT = 0.7;
    private final MainWindow mainWindow;
    private final CenterPanel centerPanel;
    private final RightPanel rightPanel;


    public SplitPane(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        centerPanel = new CenterPanel(mainWindow);
        rightPanel = new RightPanel(mainWindow);
        init();
    }


    private void init() {
        setResizeWeight(RESIZE_WEIGHT);

        setDividerSize(0);
        setEnabled(false);
        setOneTouchExpandable(false);


        setLeftComponent(centerPanel);
        setRightComponent(rightPanel);

    }
}
