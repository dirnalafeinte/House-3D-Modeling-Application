package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class TopLeftPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.LEFT);
    private final MainWindow mainWindow;
    private final UndoButton undoButton;
    private final RedoButton redoButton;

    public TopLeftPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        undoButton = new UndoButton(mainWindow);
        redoButton = new RedoButton(mainWindow);
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(undoButton);
        add(redoButton);
    }
}
