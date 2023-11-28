package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExportRetraitMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_RETRAIT = "Retraits";
    private final MainWindow mainWindow;

    public ExportRetraitMenuItem(MainWindow mainWindow) {
        super(TEXT_EXPORT_RETRAIT);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Export Retraits");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(mainWindow);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    mainWindow.getController().exportRetraits(selectedDirectory.getPath());
                } else if (userSelection == JFileChooser.CANCEL_OPTION) {
                    // User canceled the operation
                }
            }
        });
    }
}
