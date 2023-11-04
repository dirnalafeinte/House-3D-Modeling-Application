package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import ca.ulaval.glo2004.domain.ChaletController;

import javax.swing.*;
import java.awt.*;

public class TopLeftPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.LEFT);
    private final ChaletController controller;
    private final UndoButton undoButton;
    private final RedoButton redoButton;

    public TopLeftPanel(ChaletController controller) {
        this.controller = controller;
        undoButton = new UndoButton(controller);
        redoButton = new RedoButton(controller);
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(undoButton);
        add(redoButton);
    }
}
