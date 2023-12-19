package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.domain.Orientation;
import ca.ulaval.glo2004.domain.dtos.ChaletDTO;
import ca.ulaval.glo2004.gui.MainWindow;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChaletPanel extends JPanel implements Observer {
    private final MainWindow mainWindow;

    private JLabel errorLabel;

    private JTextField largeurField, longueurField, hauteurField, epaisseurField, deltaRainureField, distanceMinField, angleToitField;

    private JComboBox<Orientation> orientationToitComboBox;
    private ChaletDTO chaletDTO;

    public ChaletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        init();
        updatefields();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));


        setBorder(BorderFactory.createTitledBorder("Redimentionner Chalet:"));

        JLabel largeurLabel = new JLabel("Largeur:");
        largeurField = new JTextField(5);
        JLabel longueurLabel = new JLabel("Longueur:");
        longueurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);
        JLabel epaisseurLabel = new JLabel("Épaisseur:");
        epaisseurField = new JTextField(5);
        JLabel deltaRainureLabel = new JLabel("Retrait additonnel");
        deltaRainureField = new JTextField(5);
        JLabel distanceMinLabel = new JLabel("Distance minimale");
        distanceMinField = new JTextField(5);
        JLabel angleToitLabel = new JLabel("Angle du toit:");
        angleToitField = new JTextField(5);
        JLabel orientationToiLabel = new JLabel("Orientation du Toit:");
        orientationToitComboBox = new JComboBox<>(Orientation.values());

        addFocusListenerToTextField(largeurField);
        addFocusListenerToTextField(longueurField);
        addFocusListenerToTextField(hauteurField);
        addFocusListenerToTextField(epaisseurField);
        addFocusListenerToTextField(deltaRainureField);
        addFocusListenerToTextField(distanceMinField);
        addFocusListenerToTextField(angleToitField);


        JButton resetButton = new JButton("Reset to Default");

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainWindow.getController().resetChaletDefaut();
            }
        });

        orientationToitComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Orientation orientationSelectionnee = (Orientation) orientationToitComboBox.getSelectedItem();
                updateChaletOrientation(orientationSelectionnee);
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
        addComponentToPanel(deltaRainureLabel);
        addComponentToPanel(deltaRainureField);
        addComponentToPanel(distanceMinLabel);
        addComponentToPanel(distanceMinField);
        addComponentToPanel(angleToitLabel);
        addComponentToPanel(angleToitField);
        addComponentToPanel(orientationToiLabel);
        addComponentToPanel(orientationToitComboBox);
        addComponentToPanel(resetButton);


        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        addComponentToPanel(errorLabel);
        mainWindow.getController().registerObserver(this);
    }


    private void addFocusListenerToTextField(JTextField textField) {
        textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                updateChalet(textField);
            }
        });
    }

    private void updateChalet(JTextField textField) {

        String largeur = largeurField.getText();
        String longueur = longueurField.getText();
        String hauteur = hauteurField.getText();
        String epaisseur = epaisseurField.getText();
        String distanceMin = distanceMinField.getText();
        String deltaRainure = deltaRainureField.getText();
        String angleToit = angleToitField.getText();

        ChaletDTO nouveauChalet = new ChaletDTO(largeur, longueur, hauteur, deltaRainure, epaisseur, distanceMin, angleToit, chaletDTO.sensDuToit());

        mainWindow.getController().updateDimensions(nouveauChalet);
    }

    private void updateChaletOrientation(Orientation orientation) {
        if (orientation != null) {
            ChaletDTO chaletDTO = mainWindow.getController().getChaletDTO();

            ChaletDTO nouveauChaletDTO = new ChaletDTO(chaletDTO.largeur(), chaletDTO.longueur(), chaletDTO.hauteur(), chaletDTO.deltaRainure(), chaletDTO.epaisseurMur(), chaletDTO.distanceMin(), chaletDTO.angleToit(), orientation.toString()
            );

            mainWindow.getController().updateDimensions(nouveauChaletDTO);
        }
    }

    private void addComponentToPanel(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(component);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    @Override
    public void update() {
//        chaletDTO = mainWindow.getController().getChalet();
        updatefields();

    }

    private void updatefields() {
        chaletDTO = mainWindow.getController().getChaletDTO();
        if (chaletDTO != null) {
            largeurField.setText(chaletDTO.largeur());
            longueurField.setText(chaletDTO.longueur());
            hauteurField.setText(chaletDTO.hauteur());
            epaisseurField.setText(chaletDTO.epaisseurMur());
            deltaRainureField.setText(chaletDTO.deltaRainure());
            distanceMinField.setText(chaletDTO.distanceMin());
            angleToitField.setText(chaletDTO.angleToit());
        }
    }
}

