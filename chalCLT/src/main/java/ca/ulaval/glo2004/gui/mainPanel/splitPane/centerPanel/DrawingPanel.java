package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel implements Observer {
    private static final Color BACKGROUD_COLOR = Color.WHITE;
    private static final double WIDTH_RATIO = 0.8;
    private static final double HEIGHT_RATIO = 1;
    private final MainWindow mainWindow;

    public DrawingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        this.setLayout(new FlowLayout());

        setBackground(BACKGROUD_COLOR);

        mainWindow.getController().registerObserver(this);

        // TODO: fix manual size
        setPreferredSize(new Dimension(1000, 700));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mainWindow.getController().afficheur.draw(g);
    }

    @Override
    public void update() {
        repaint();
    }
}
