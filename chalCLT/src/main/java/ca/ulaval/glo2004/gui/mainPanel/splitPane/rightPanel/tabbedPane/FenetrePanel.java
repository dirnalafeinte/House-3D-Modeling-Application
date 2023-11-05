package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetrePanel extends JPanel {
    private final MainWindow mainWindow;
    private JTextField xField, yField, largeurField, hauteurField;
    private JTextArea outputTextArea;

    public FenetrePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        // Create input fields and "Add" button
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Create output text area
        outputTextArea = new JTextArea(10, 40);
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JLabel xLabel = new JLabel("X:");
        xField = new JTextField(5);
        JLabel yLabel = new JLabel("Y:");
        yField = new JTextField(5);
        JLabel largeurLabel = new JLabel("Largeur:");
        largeurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);

        JButton addButton = new JButton("Ajouter");

        panel.add(xLabel);
        panel.add(xField);
        panel.add(yLabel);
        panel.add(yField);
        panel.add(largeurLabel);
        panel.add(largeurField);
        panel.add(hauteurLabel);
        panel.add(hauteurField);
        panel.add(addButton);

//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int x = Integer.parseInt(xField.getText());
//                int y = Integer.parseInt(yField.getText());
//                int largeur = Integer.parseInt(largeurField.getText());
//                int hauteur = Integer.parseInt(hauteurField.getText());
//
//                // Create a FenetreDTO with the input data
//                FenetreDTO fenetreDTO = new FenetreDTO(x, y, largeur, hauteur);
//
//                // Call the controller to add the Fenetre
//                // You should implement the controller and the addFenetre method
//                // For this example, we just print the added data to the text area
//                outputTextArea.append(fenetreDTO.toString() + "\n");
//
//                // Clear input fields
//                xField.setText("");
//                yField.setText("");
//                largeurField.setText("");
//                hauteurField.setText("");
//            }
//        });

        return panel;
    }
}
