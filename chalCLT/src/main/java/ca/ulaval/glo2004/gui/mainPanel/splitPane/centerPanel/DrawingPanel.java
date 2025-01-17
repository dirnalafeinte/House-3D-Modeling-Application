package ca.ulaval.glo2004.gui.mainPanel.splitPane.centerPanel;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.domain.drawers.Afficheur;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mouse.MouseMotionListener;
import ca.ulaval.glo2004.gui.mouse.MouseListener;
import ca.ulaval.glo2004.gui.mouse.ZoomHandler;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel implements Observer {
    private static final Color BACKGROUD_COLOR = Color.WHITE;
    private static final double WIDTH_RATIO = 0.6;
    private static final double HEIGHT_RATIO = 1;
    private final MainWindow mainWindow;
    private final ZoomHandler zoomHandler;
    private final MouseListener mouseListner;
    private final MouseMotionListener accessoryMovement;
    //private final PanningHandler panningHandler;


    public DrawingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        zoomHandler = new ZoomHandler(mainWindow);
        mouseListner = new MouseListener(mainWindow);
        accessoryMovement = new MouseMotionListener(mainWindow);
        //panningHandler = new PanningHandler(mainWindow);
        init();
    }

    private void init() {
        this.setLayout(new FlowLayout());
        addMouseWheelListener(zoomHandler);
        addMouseListener(mouseListner);
        addMouseMotionListener(accessoryMovement);
        //addMouseMotionListener(panningHandler);
        setBackground(BACKGROUD_COLOR);

        mainWindow.getController().registerObserver(this);

        // TODO: fix manual size
        // setPreferredSize(new Dimension(1000, 700));
    }

    @Override
    public Dimension getPreferredSize() {
        if (mainWindow != null && mainWindow.isDisplayable()) {
            Dimension size = mainWindow.getSize();
            int preferredWidth = (int) (size.width * WIDTH_RATIO);
            int preferredHeight = (int) (size.height * HEIGHT_RATIO);
            return new Dimension(preferredWidth, preferredHeight);
        }
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(afficheur().getZoomFactor(), afficheur().getZoomFactor());
        g2d.translate(afficheur().getxOffset(), afficheur().getyOffset());
//        g2d.translate(afficheur().getxChalet(), afficheur().getyChalet());
        afficheur().draw(g2d);
        afficherGrille(g2d);
        g2d.dispose();
    }

    @Override
    public void update() {
        repaint();
    }

    private void afficherGrille(Graphics2D g2d) {
        boolean isGrille = mainWindow.getMainPanel().getTopPanel().getTopRightPanel().isGrille();
        afficheur().setIntervalLigne(mainWindow.getMainPanel().getTopPanel().getTopRightPanel().getTextField().getText());
        if (isGrille) {
            afficheur().drawGrille(g2d);
        }
    }

    public MouseListener getMouseListner() {
        return mouseListner;
    }

    private Afficheur afficheur(){
        return mainWindow.getController().afficheur;
    }

}
