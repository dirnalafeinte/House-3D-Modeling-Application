package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import ca.ulaval.glo2004.domain.Afficheur;
import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel implements Observer {
    private static final Color BACKGROUD_COLOR = Color.PINK;
    private final MainWindow mainWindow;

    public DrawingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        this.setLayout(new FlowLayout());

        setBackground(BACKGROUD_COLOR);

        mainWindow.getController().registerObserver(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mainWindow.getController().getAfficheur().draw(g);
    }

    @Override
    public void update() {
        repaint();
    }
}
