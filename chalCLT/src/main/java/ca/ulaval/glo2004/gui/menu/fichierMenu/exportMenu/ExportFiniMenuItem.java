package ca.ulaval.glo2004.gui.menu.fichierMenu.exportMenu;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExportFiniMenuItem extends JMenuItem {
    private static final String TEXT_EXPORT_FINI = "Panneaux finis";
    private final MainWindow mainWindow;

    public ExportFiniMenuItem(MainWindow mainWindow) {
        super(TEXT_EXPORT_FINI);
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        // Add an ActionListener to the menu item
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Export Panneaux Finis");

                // Set default file name and filter if needed
                fileChooser.setSelectedFile(new File("ChalCLT_Fini_" + ".stl")); // TODO

                int userSelection = fileChooser.showSaveDialog(mainWindow);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file path
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println(filePath);
                    mainWindow.getController().exportPanneauxFinis(filePath);

                } else if (userSelection == JFileChooser.CANCEL_OPTION) {
                    // User canceled the operation
                }
            }
        });
    }
}
