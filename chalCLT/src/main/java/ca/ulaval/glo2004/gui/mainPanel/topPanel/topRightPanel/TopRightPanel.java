package ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel;

import javax.swing.*;
import java.awt.*;

public class TopRightPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.RIGHT);
    private final GrilleCheckBox grilleCheckBox = new GrilleCheckBox();

    public TopRightPanel() {
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(grilleCheckBox);
    }
}
