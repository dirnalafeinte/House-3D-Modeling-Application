package ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel;

import ca.ulaval.glo2004.domain.ChaletController;

import javax.swing.*;
import java.awt.*;

public class TopRightPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.RIGHT);
    private final ChaletController controller;
    private final GrilleCheckBox grilleCheckBox;

    public TopRightPanel(ChaletController controller) {
        this.controller = controller;
        grilleCheckBox = new GrilleCheckBox(controller);
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(grilleCheckBox);
    }
}
