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
        JPanel inputPanel = AjoutPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Create a separator panel with a black line
        addSeparator();

        // Create a new panel for additional fields and labels
        JPanel newInputPanel = ModifiePanel();
        add(newInputPanel, BorderLayout.CENTER);

        add(new JScrollPane(outputTextArea), BorderLayout.SOUTH);
    }

    private void addSeparator() {
        JPanel separatorPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
            }
        };
        separatorPanel.setPreferredSize(new Dimension(0, 2)); // Set the separator height
        add(separatorPanel, BorderLayout.CENTER);
    }

    private JPanel AjoutPanel() {
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


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                int largeur = Integer.parseInt(largeurField.getText());
                int hauteur = Integer.parseInt(hauteurField.getText());


                //FenetreDTO fenetreDTO = new FenetreDTO(int(x), y, largeur, hauteur);


                // Clear input fields
                xField.setText("");
                yField.setText("");
                largeurField.setText("");
                hauteurField.setText("");
            }
        });


        return panel;
    }

    private JPanel ModifiePanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JLabel newLabel1 = new JLabel("New Label 1:");
        JTextField newField1 = new JTextField(5);
        JLabel newLabel2 = new JLabel("New Label 2:");
        JTextField newField2 = new JTextField(5);
        JButton newButton = new JButton("New Button");

        panel.add(newLabel1);
        panel.add(newField1);
        panel.add(newLabel2);
        panel.add(newField2);
        panel.add(newButton);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for the new button
            }
        });
        return panel;
    }
}
