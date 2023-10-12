package ca.ulaval.glo2004.gui.mainPanel;

import ca.ulaval.glo2004.gui.mainPanel.splitPane.SplitPane;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.TopPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final TopPanel topPanel = new TopPanel();
    private final SplitPane splitPane = new SplitPane();

    public MainPanel() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }
}
