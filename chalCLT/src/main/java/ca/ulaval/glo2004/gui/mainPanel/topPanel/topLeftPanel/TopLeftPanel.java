package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import javax.swing.*;
import java.awt.*;

public class TopLeftPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.LEFT);
    private final UndoButton undoButton = new UndoButton();
    private final RedoButton redoButton = new RedoButton();

    public TopLeftPanel() {
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(undoButton);
        add(redoButton);
    }
}
