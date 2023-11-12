package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel;

import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane.TabbedPane;

import javax.swing.*;

public class RightPanel extends JPanel {
    private final MainWindow mainWindow;
    private final TabbedPane tabbedPane;

    public RightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        tabbedPane = new TabbedPane(mainWindow);
        init();
    }

    private void init() {
        add(tabbedPane);
    }
}
