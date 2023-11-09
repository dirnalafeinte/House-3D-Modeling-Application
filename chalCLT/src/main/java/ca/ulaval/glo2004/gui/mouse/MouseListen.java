package ca.ulaval.glo2004.gui.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListen implements MouseListener {
    private int xPosition;
    private int yPosition;

    @Override
    public void mouseClicked(MouseEvent e) {
        xPosition = e.getX();
        yPosition = e.getY();
        //System.out.println("Mouse clicked at (x=" + xPosition + ", y=" + yPosition + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // You can implement this method if needed
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // You can implement this method if needed
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // You can implement this method if needed
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // You can implement this method if needed
    }
}


