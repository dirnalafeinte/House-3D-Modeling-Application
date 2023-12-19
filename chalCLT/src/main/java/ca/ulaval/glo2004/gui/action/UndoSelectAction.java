package ca.ulaval.glo2004.gui.action;

import ca.ulaval.glo2004.gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoSelectAction implements ActionListener {
    private final MainWindow mainWindow;

    public UndoSelectAction(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getController().undo();
    }
}
