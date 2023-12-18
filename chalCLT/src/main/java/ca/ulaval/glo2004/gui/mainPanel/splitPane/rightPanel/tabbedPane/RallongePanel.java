package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.domain.dtos.ChaletDTO;
import ca.ulaval.glo2004.gui.MainWindow;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RallongePanel extends JPanel implements Observer {
    private final MainWindow mainWindow;

    private JLabel errorLabel;

    private JTextField longueurField, hauteurField;

    private ChaletDTO chaletDTO;

    public RallongePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        init();
        updatefields();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));


        setBorder(BorderFactory.createTitledBorder("Modifier Rallonge:"));

        JLabel longueurLabel = new JLabel("Longueur:");
        longueurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);

        addFocusListenerToTextField(longueurField);
        addFocusListenerToTextField(hauteurField);


        addComponentToPanel(longueurLabel);
        addComponentToPanel(longueurField);
        addComponentToPanel(hauteurLabel);
        addComponentToPanel(hauteurField);

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

        String longueur = longueurField.getText();
        String hauteur = hauteurField.getText();

        ChaletDTO nouveauChalet = new ChaletDTO(chaletDTO.largeur(), longueur, hauteur, chaletDTO.deltaRainure(), chaletDTO.epaisseurMur(), chaletDTO.distanceMin());

        mainWindow.getController().updateDimensions(nouveauChalet);
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
            longueurField.setText(chaletDTO.longueur());
            hauteurField.setText(chaletDTO.hauteur());
        }
    }
}

