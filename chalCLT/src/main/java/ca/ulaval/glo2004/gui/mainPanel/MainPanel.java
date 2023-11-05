package ca.ulaval.glo2004.gui.mainPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.SplitPane;
import ca.ulaval.glo2004.gui.mainPanel.topPanel.TopPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final MainWindow mainWindow;
    private final TopPanel topPanel;
    private final SplitPane splitPane;

    public MainPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        topPanel = new TopPanel(mainWindow);
        splitPane = new SplitPane(mainWindow);
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }
}
