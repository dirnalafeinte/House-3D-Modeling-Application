package ca.ulaval.glo2004.gui.menu.fichierMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SauvegarderMenuItem extends JMenuItem {
    private static final String TEXT_SAUVEGARDER = "Sauvegarder";
    private final MainWindow mainWindow;

    public SauvegarderMenuItem(MainWindow mainWindow) {
        super(TEXT_SAUVEGARDER);
        this.mainWindow = mainWindow;
        init();


        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvegarderEnFichier();
            }
        });
    }

    private void init() {
    }

    private void sauvegarderEnFichier() {
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showSaveDialog(mainWindow);

        if (selection
         == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            String fileName = fileChooser.getSelectedFile().getName();
           try{
               mainWindow.getController().sauvegarderFichier(filePath, fileName);
           } catch (RuntimeException err) {
               err.printStackTrace();
           } catch (IOException e) {
               throw new RuntimeException(e);
           }

        }
    }
}
