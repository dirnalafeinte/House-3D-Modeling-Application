package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class OuvrirMenuItem extends JMenuItem {
    private static final String TEXT_OUVRIR = "Ouvrir";
    private final MainWindow mainWindow;
    private ChaletController chaletController;

    public OuvrirMenuItem(MainWindow mainWindow) {
        super(TEXT_OUVRIR);
        this.mainWindow = mainWindow;
        this.chaletController = mainWindow.getController();

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
        int selection = fileChooser.showOpenDialog(mainWindow);

        if (selection == JFileChooser.APPROVE_OPTION) {
            File fichierSelectionne = fileChooser.getSelectedFile();
            String filePath = fichierSelectionne.getAbsolutePath();

            Chalet chalet = lire(filePath);
            if (chalet != null) {
                mainWindow.getController().setChalet(chalet);
                mainWindow.getController().notifyObservers();
            }
        }
    }

    private Chalet lire(String path) {
        Chalet chalet = null;
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(path))) {
            chalet = (Chalet) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return chalet;

    }
}

