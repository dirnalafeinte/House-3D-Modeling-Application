package ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel;

import ca.ulaval.glo2004.gui.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class TopRightPanel extends JPanel implements ActionListener {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.RIGHT);
    private final MainWindow mainWindow;
    private final GrilleCheckBox grilleCheckBox;

    public TopRightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        grilleCheckBox = new GrilleCheckBox(mainWindow);
        init();
    }

    public boolean isGrille() {
        return grilleCheckBox.isSelected();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);

        add(grilleCheckBox);
        grilleCheckBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
    }
}
