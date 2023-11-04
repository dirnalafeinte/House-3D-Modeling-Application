package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import ca.ulaval.glo2004.domain.ChaletController;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private static final Color BACKGROUD_COLOR = Color.WHITE;
    private final ChaletController controller;

    public DrawingPanel(ChaletController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setBackground(BACKGROUD_COLOR);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
