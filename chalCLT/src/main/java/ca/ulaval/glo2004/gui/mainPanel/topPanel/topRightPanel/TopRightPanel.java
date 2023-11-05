package ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class TopRightPanel extends JPanel {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.RIGHT);
    private final MainWindow mainWindow;
    private final GrilleCheckBox grilleCheckBox;

    public TopRightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        grilleCheckBox = new GrilleCheckBox(mainWindow);
        init();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(grilleCheckBox);
    }
}
