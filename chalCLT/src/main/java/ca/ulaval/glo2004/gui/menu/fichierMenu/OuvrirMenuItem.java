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
        this.chaletController = chaletController;

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

        if(selection == JFileChooser.APPROVE_OPTION) {
            File fichierSelectionne = fileChooser.getSelectedFile();
            String filePath = fichierSelectionne.getAbsolutePath();

        }
    }


    private Chalet deserialize(String path) throws FileNotFoundException {
        Chalet chalet = null;
        ObjectInputStream oi = null;

        try{
            FileInputStream fichier = new FileInputStream(path);
            oi = new ObjectInputStream(fichier);
            chalet = (Chalet) oi.readObject();

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(mainWindow, "Erreur" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (oi != null) {
                    oi.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainWindow, "Erreur lors de la fermeture du fichier : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

            return chalet;
        }
    }

