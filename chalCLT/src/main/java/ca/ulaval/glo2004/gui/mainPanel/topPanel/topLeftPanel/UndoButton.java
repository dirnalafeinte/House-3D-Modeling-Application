package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.action.UndoSelectAction;

import javax.swing.*;

public class UndoButton extends JButton implements Observer {
    private static final String TEXT_UNDO_BUTTON = "Undo";
    private final MainWindow mainWindow;

    public UndoButton(MainWindow mainWindow) {
        super(TEXT_UNDO_BUTTON);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setEnabled(false);
        mainWindow.getController().registerObserver(this);
        addActionListener(new UndoSelectAction(mainWindow));
    }

    @Override
    public void update() {
        setEnabled(mainWindow.getController().canUndo());
    }
}
