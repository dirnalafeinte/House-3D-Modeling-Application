package ca.ulaval.glo2004.gui.mainPanel.topPanel.topLeftPanel;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.action.RedoSelectAction;
import ca.ulaval.glo2004.gui.action.UndoSelectAction;

import javax.swing.*;

public class RedoButton extends JButton implements Observer {
    private static final String TEXT_REDO_BUTTON = "Redo";
    private final MainWindow mainWindow;

    public RedoButton(MainWindow mainWindow) {
        super(TEXT_REDO_BUTTON);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setEnabled(false);
        mainWindow.getController().registerObserver(this);
        addActionListener(new RedoSelectAction(mainWindow));
    }

    @Override
    public void update() {
        setEnabled(mainWindow.getController().canRedo());
    }
}
