package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.domain.dtos.AddFenetreDTO;
import ca.ulaval.glo2004.domain.dtos.FenetreDTO;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalFenetreException;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChaletPanel extends JPanel {
    private final MainWindow mainWindow;

    private JTextField largeurField, longueurField, hauteurField, epaisseurField;


    public ChaletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        setBorder(BorderFactory.createTitledBorder("Redimentionner Chalet:"));


        JLabel largeurLabel = new JLabel(("Largeur:"));
        largeurField = new JTextField(5);
        JLabel longueurLabel = new JLabel("Longueur:");
        longueurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);
        JLabel epaisseurLabel =  new JLabel("Épaisseur:");
        epaisseurField = new JTextField(5);

        JButton updateButton = new JButton("Update chalet");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double largeur = Double.parseDouble((largeurField.getText()));
                double longueur = Double.parseDouble(longueurField.getText());
                double hauteur = Double.parseDouble(hauteurField.getText());
                double epaisseur = Double.parseDouble(epaisseurField.getText());


                mainWindow.getController().updateDimensions(largeur, longueur, hauteur, epaisseur);
                mainWindow.repaint();

            }
        });

        addComponentToPanel(largeurLabel);
        addComponentToPanel(largeurField);
        addComponentToPanel(longueurLabel);
        addComponentToPanel(longueurField);
        addComponentToPanel(hauteurLabel);
        addComponentToPanel(hauteurField);

        addComponentToPanel(epaisseurLabel);
        addComponentToPanel(epaisseurField);
        addComponentToPanel(updateButton);
    }

    private void addComponentToPanel(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(component);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
    }
}
