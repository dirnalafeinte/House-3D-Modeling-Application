package ca.ulaval.glo2004.gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitterSelectAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
