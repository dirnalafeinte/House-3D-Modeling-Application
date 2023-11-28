package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

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

public class ChaletPanel extends JPanel {
    private final MainWindow mainWindow;

    private JLabel errorLabel;
    private JTextField largeurField, longueurField, hauteurField, epaisseurField, deltaRainureField;

    public ChaletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
        initialiseDefaultValues();
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
        JLabel epaisseurLabel =  new JLabel("Épaisseur:");
        epaisseurField = new JTextField(5);
        JLabel deltaRainureLabel = new JLabel("Retrait additonnel");
        deltaRainureField= new JTextField(5);


        addFocusListenerToTextField(largeurField);
        addFocusListenerToTextField(longueurField);
        addFocusListenerToTextField(hauteurField);
        addFocusListenerToTextField(epaisseurField);
        addFocusListenerToTextField(deltaRainureField);



        JButton resetButton = new JButton("Reset to Default");

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialiseDefaultValues();

                Chalet defaultChalet = mainWindow.getController().getDefaultChalet();
                largeurField.setText(String.valueOf(defaultChalet.getLargeur().toFeet()));
                longueurField.setText(String.valueOf(defaultChalet.getLongueur().toFeet()));
                hauteurField.setText(String.valueOf(defaultChalet.getHauteur().toFeet()));
                epaisseurField.setText(String.valueOf(defaultChalet.getEpaisseurMur().toInches()));
                deltaRainureField.setText(String.valueOf(defaultChalet.getDeltaRainure().toInches()));

                errorLabel.setText("");
                mainWindow.getController().resetChaletDefaut();
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
        addComponentToPanel(deltaRainureLabel);
        addComponentToPanel(deltaRainureField);
        addComponentToPanel(resetButton);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        addComponentToPanel(errorLabel);

    }

    private void initialiseDefaultValues() {
        Chalet defaultChalet = mainWindow.getController().getDefaultChalet();

        largeurField.setText(formatFeet(defaultChalet.getLargeur()));
        longueurField.setText(formatFeet(defaultChalet.getLongueur()));
        hauteurField.setText(formatFeet(defaultChalet.getHauteur()));
        epaisseurField.setText(formatInches(defaultChalet.getEpaisseurMur()));
        deltaRainureField.setText(formatInches(defaultChalet.getDeltaRainure()));
    }

    private String formatFeet(Imperial imperial) {
        return imperial.toFeet() + "'";
    }

    private String formatInches(Imperial imperial) {
        return imperial.toInches() + "''";
    }

    private void addFocusListenerToTextField (JTextField textField) {
        textField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                updateField(textField);
            }
        });
    }

    private void updateField (JTextField textField) {
        String text = textField.getText().trim();
        double value = parseDoubleorNull(text);

//        if (!isValidValue(textField, value)) {
//            System.out.println("Invalid value: " + textField.getText());
//            return;
//        }

        if (textField == largeurField || textField == longueurField || textField == hauteurField) {
            value *= 12;
        }else if (textField == epaisseurField || textField ==  deltaRainureField) {
            value /= 12;
        }

        System.out.println("Updated value: " + value);

        mainWindow.getController().updateDimensions(
                parseDoubleorNull(largeurField.getText()),
                parseDoubleorNull(longueurField.getText()),
                parseDoubleorNull(hauteurField.getText()),
                parseDoubleorNull(epaisseurField.getText()),
                parseDoubleorNull(deltaRainureField.getText())
        );

        errorLabel.setText("");
        mainWindow.repaint();
    }


    private void addComponentToPanel(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(component);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
    }

//    private boolean isValidValue(JTextField textField, double value) {
//        String text = textField.getText().trim();
//
//        if(text.isEmpty()) {
//            return true;
//        }
//
//        if (text.matches("\\d+'\"")) {
//            return true;
//        }
//
//        if(text.matches("\\d+\\.?\\d*''")) {
//            return true;
//        }
//
//        //errorLabel.setText("Veuillez saisir une valeur en feet (') ou inches('') pour les valeurs correspondantes");
//        return false;



    //}
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
}

