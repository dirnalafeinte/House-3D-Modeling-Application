package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.domain.dtos.ChaletDTO;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.domain.Chalet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChaletPanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private  Chalet chalet;

    private JLabel errorLabel;

    private JTextField largeurField, longueurField, hauteurField, epaisseurField, deltaRainureField, distanceMinField;

    private ChaletDTO chaletDTO;

    public ChaletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.chalet = chalet;
        init();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        mainWindow.getController().registerObserver(this);

        setBorder(BorderFactory.createTitledBorder("Redimentionner Chalet:"));

        JLabel largeurLabel = new JLabel("Largeur:");
        largeurField = new JTextField(5);
        JLabel longueurLabel = new JLabel("Longueur:");
        longueurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);
        JLabel epaisseurLabel =  new JLabel("Épaisseur:");
        epaisseurField = new JTextField(5);
        JLabel deltaRainureLabel = new JLabel("Retrait additonnel");
        deltaRainureField= new JTextField(5);
        JLabel distanceMinLabel = new JLabel("Distance minimale");
        distanceMinField= new JTextField(5);

        addFocusListenerToTextField(largeurField);
        addFocusListenerToTextField(longueurField);
        addFocusListenerToTextField(hauteurField);
        addFocusListenerToTextField(epaisseurField);
        addFocusListenerToTextField(deltaRainureField);
        addFocusListenerToTextField(distanceMinField);


        JButton resetButton = new JButton("Reset to Default");

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainWindow.getController().resetChaletDefaut();
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
        addComponentToPanel(resetButton);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        addComponentToPanel(errorLabel);

    }



    private void addFocusListenerToTextField (JTextField textField) {
        textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                updateChalet(textField);
            }
        });
    }

    private void updateChalet (JTextField textField) {

        String largeur = largeurField.getText();
        String longueur = longueurField.getText();
        String hauteur = hauteurField.getText();
        String epaisseur = epaisseurField.getText();
        String distanceMin= distanceMinField.getText();
        String deltaRainure = deltaRainureField.getText();

        ChaletDTO nouveauChalet = new ChaletDTO(largeur, longueur, hauteur,deltaRainure, epaisseur, distanceMin);

        mainWindow.getController().updateDimensions(nouveauChalet);
//            textField.setText(format(value));
//            errorLabel.setText("");
//            mainWindow.repaint();
//
//        }else {
//            errorLabel.setText("Format invalide pour " + textField.getName());
//        }



    }

    private boolean isValidImperialFormat(String value) {
        try {
            Imperial.fromString(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String format(Imperial imperial) {
        return imperial.toString();
    }

    private void addComponentToPanel(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(component);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
    }


    private Double parseDoubleorNull(String value){
        if (value.trim().isEmpty()) {
            return null;
        }
        try{
            return Double.parseDouble(value);
        } catch (NumberFormatException e){
            return null;
        }
    }

    @Override
    public void update() {
        chaletDTO = mainWindow.getController().getChalet();
        updatefields();

    }

    private void updatefields() {
        largeurField.setText(chaletDTO.largeur());
        longueurField.setText(chaletDTO.longueur());
        hauteurField.setText(chaletDTO.hauteur());
        epaisseurField.setText(chaletDTO.epaisseurMur());
        distanceMinField.setText(chaletDTO.distanceMin());
    }
}

