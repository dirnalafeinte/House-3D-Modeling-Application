package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChaletPanel extends JPanel {
    private final MainWindow mainWindow;
    private JTextField largeurField, longueurField, hauteurField, epaisseurField, deltaRainureField;

    public ChaletPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
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

//        JButton updateButton = new JButton("Update chalet");

        addFocusListenerToTextField(largeurField);
        addFocusListenerToTextField(longueurField);
        addFocusListenerToTextField(hauteurField);
        addFocusListenerToTextField(epaisseurField);
        addFocusListenerToTextField(deltaRainureField);

//        updateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                double largeur = parseDoubleorNull(largeurField.getText());
//                double longueur = parseDoubleorNull(longueurField.getText());
//                double hauteur = parseDoubleorNull(hauteurField.getText());
//                double epaisseur = parseDoubleorNull(epaisseurField.getText());
//                double deltaRainure = parseDoubleorNull(deltaRainureField.getText());
//
////                Chalet defaultChalet = mainWindow.getController().getD
//
//
//                mainWindow.getController().updateDimensions(largeur, longueur, hauteur, epaisseur, deltaRainure);
//                mainWindow.repaint();
//            }
//        });



        JButton resetButton = new JButton("Reset to Default");

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
//        addComponentToPanel(updateButton);
        addComponentToPanel(resetButton);
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
        double value;
        if (textField.getText().trim().isEmpty()) {
            value = getDefaultFieldValue(textField);
        } else {
            value = parseDoubleorNull(textField.getText());
        }

        mainWindow.getController().updateDimensions(
                parseDoubleorNull(largeurField.getText()),
                parseDoubleorNull(longueurField.getText()),
                parseDoubleorNull(hauteurField.getText()),
                parseDoubleorNull(epaisseurField.getText()),
                parseDoubleorNull(deltaRainureField.getText())
        );

        mainWindow.repaint();
    }

    private double getDefaultFieldValue(JTextField textField) {

        if (textField == largeurField){
            return 10;
        } else if (textField == longueurField) {
            return 10;
        } else if (textField == hauteurField) {
            return 8;
        } else if (textField == epaisseurField) {
            return 1;
        }else if (textField == deltaRainureField) {
            return 1;
        } else {
            return 0.0;
        }
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

