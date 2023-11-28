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
    private  Chalet chalet;

    private JLabel errorLabel;

    private JTextField largeurField, longueurField, hauteurField, epaisseurField, deltaRainureField, distanceMinField;

    public ChaletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.chalet = chalet;
        init();
        initDefaultValues();
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
                initDefaultValues();

                Chalet defaultChalet = mainWindow.getController().getDefaultChalet();
                largeurField.setText(String.valueOf(defaultChalet.getLargeur().toFeet()));
                longueurField.setText(String.valueOf(defaultChalet.getLongueur().toFeet()));
                hauteurField.setText(String.valueOf(defaultChalet.getHauteur().toFeet()));
                epaisseurField.setText(String.valueOf(defaultChalet.getEpaisseurMur().toInches()));
                deltaRainureField.setText(String.valueOf(defaultChalet.getDeltaRainure().toInches()));
                distanceMinField.setText(String.valueOf(defaultChalet.getDistanceMin().toInches()));

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
        addComponentToPanel(distanceMinLabel);
        addComponentToPanel(distanceMinField);
        addComponentToPanel(resetButton);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        addComponentToPanel(errorLabel);

    }

    private void initDefaultValues() {
        Chalet defaultChalet = mainWindow.getController().getDefaultChalet();

        largeurField.setText(formatFeet(defaultChalet.getLargeur()));
        longueurField.setText(formatFeet(defaultChalet.getLongueur()));
        hauteurField.setText(formatFeet(defaultChalet.getHauteur()));
        epaisseurField.setText(formatInches(defaultChalet.getEpaisseurMur()));
        deltaRainureField.setText(formatInches(defaultChalet.getDeltaRainure()));
        distanceMinField.setText(formatInches(defaultChalet.getDistanceMin()));
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


        if(isValidImperialFormat(text)) {
            Imperial value = Imperial.fromString(text);

//            if (textField == largeurField || textField == longueurField || textField == hauteurField) {
//                value = value.multiplyBy(12);
//            }else if (textField == epaisseurField || textField ==  deltaRainureField) {
//                value = value.divideBy(12);
//            }
            System.out.println("Updated value: " + value);

            mainWindow.getController().updateDimensions(
                    parseDoubleorNull(largeurField.getText()),
                    parseDoubleorNull(longueurField.getText()),
                    parseDoubleorNull(hauteurField.getText()),
                    parseDoubleorNull(epaisseurField.getText()),
                    parseDoubleorNull(deltaRainureField.getText()),
                    parseDoubleorNull(distanceMinField.getText())

            );
            textField.setText(format(value));
            errorLabel.setText("");
            mainWindow.repaint();

        }else {
            errorLabel.setText("Format invalide pour " + textField.getName());
        }



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
}

