package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OuvrirMenuItem extends JMenuItem {
    private static final String TEXT_OUVRIR = "Ouvrir";
    private final MainWindow mainWindow;

    public OuvrirMenuItem(MainWindow mainWindow) {
        super(TEXT_OUVRIR);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirFichier();
            }
        });
    }

    private void ouvrirFichier() {
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showSaveDialog(mainWindow);

        if(selection == JFileChooser.APPROVE_OPTION) {
            File fichierSelectionne = fileChooser.getSelectedFile();
            String filePath = fichierSelectionne.getAbsolutePath();

            mainWindow.ouvrirFichier(filePath);
        }
    }
}
