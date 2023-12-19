package ca.ulaval.glo2004.gui.action;

import ca.ulaval.glo2004.gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoSelectAction implements ActionListener {
    private final MainWindow mainWindow;

    public RedoSelectAction(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getController().redo();
    }
}
