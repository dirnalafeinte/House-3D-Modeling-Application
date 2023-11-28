package ca.ulaval.glo2004.gui.action;

import ca.ulaval.glo2004.domain.Vue;
import ca.ulaval.glo2004.gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectVueAction implements ActionListener {
    private final MainWindow mainWindow;
    private final Vue vue;

    public SelectVueAction(MainWindow mainWindow, Vue vue) {
        this.mainWindow = mainWindow;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getController().setVue(vue);
    }
}
