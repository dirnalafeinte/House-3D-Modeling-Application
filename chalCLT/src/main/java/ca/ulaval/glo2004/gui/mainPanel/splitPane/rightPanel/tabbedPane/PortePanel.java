package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.exceptions.IllegalFenetreException;
import ca.ulaval.glo2004.domain.exceptions.IllegalPorteException;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.gui.MainWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PortePanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JTextField xField, largeurField, hauteurField, modifierXField, modifierLargeurField, modifierHauteurField;
    private JComboBox orientationComboBox;
    private JComboBox idComboBox;
    private Map<String, PorteDTO> portes = new HashMap<>();

    public PortePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();

    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel inputPanel = ajoutPanel();
        add(inputPanel, BorderLayout.NORTH);
        addSeparator();
        JPanel newInputPanel = ModifiePanel();
        add(newInputPanel, BorderLayout.SOUTH);
        mainWindow.getController().registerObserver(this);

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

    private JPanel ajoutPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel xLabel = new JLabel("Position X:");
        xField = new JTextField(5);
        JLabel largeurLabel = new JLabel("Largeur:");
        largeurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);
        String[] orientationMur = {Orientation.FACADE.toString(), Orientation.ARRIERE.toString(), Orientation.GAUCHE.toString(), Orientation.DROITE.toString()};
        orientationComboBox = new JComboBox(orientationMur);
        JButton ajouter = new JButton("Ajouter");

        panel.add(xLabel);
        panel.add(xField);
        panel.add(largeurLabel);
        panel.add(largeurField);
        panel.add(hauteurLabel);
        panel.add(hauteurField);
        panel.add(orientationComboBox);
        panel.add(ajouter);



        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Imperial x = Imperial.stringToImperial(xField.getText());
                    Imperial y = mainWindow.getController().getChalet().getHauteur();
                    Coordonnee coordonnee = new Coordonnee(x, y);
                    Imperial largeur = Imperial.stringToImperial(largeurField.getText());
                    Imperial hauteur = Imperial.stringToImperial(hauteurField.getText());

                    Orientation orientation = Orientation.valueOf(orientationComboBox.getSelectedItem().toString());
                    PorteDTO porteDTO = new PorteDTO(largeur, hauteur, coordonnee, orientation);
                    mainWindow.getController().ajouterPorte(porteDTO);

                    xField.setText("");
                    largeurField.setText("");
                    hauteurField.setText("");

                    //update();

                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides avant d'ajouter une porte.");
                }
                catch (IllegalPorteException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel ModifiePanel() {
        JPanel panel = new JPanel(new FlowLayout());

        idComboBox = new JComboBox();
        JLabel modifierXLabel = new JLabel("Position X:");
        modifierXField = new JTextField(5);
        JLabel modifierLargeurLabel = new JLabel("Largeur:");
        modifierLargeurField = new JTextField(5);
        JLabel modifierHauteurLabel = new JLabel("Hauteur:");
        modifierHauteurField = new JTextField(5);
        JButton modifier = new JButton("Modifier");
        JButton supprimer = new JButton("Supprimer");

        panel.add(idComboBox);
        panel.add(modifierXLabel);
        panel.add(modifierXField);
        panel.add(modifierLargeurLabel);
        panel.add(modifierLargeurField);
        panel.add(modifierHauteurLabel);
        panel.add(modifierHauteurField);


        modifier.addActionListener(e -> {
            try {
                Imperial x = Imperial.stringToImperial(modifierXField.getText());
                Imperial y = mainWindow.getController().getChalet().getHauteur();
                Coordonnee coordonnee = new Coordonnee(x, y);
                Imperial largeur = Imperial.stringToImperial(modifierLargeurField.getText());
                Imperial hauteur = Imperial.stringToImperial(modifierHauteurField.getText());
                UUID id = UUID.fromString(idComboBox.getSelectedItem().toString());

                PorteDTO porte = portes.get(id);
                porte.Coordonnee = coordonnee;
                porte.Largeur = largeur;
                porte.Hauteur = hauteur;
                mainWindow.getController().modifierPorte(porte);

                xField.setText("");
                largeurField.setText("");
                hauteurField.setText("");

            }
            catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides avant d'ajouter une porte.");
            }
            catch (IllegalPorteException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for the new button
            }
        });
        panel.add(modifier);
        panel.add(supprimer);
        return panel;
    }

    @Override
    public void update() {
        for (PorteDTO porte : mainWindow.getController().getPortes()){
            if (!portes.containsKey(porte.id.toString())){
                portes.put(porte.id.toString(), porte);
            }
        }
        updateComboBox();
    }

    public void updateComboBox(){
        idComboBox.removeAllItems();
        for (PorteDTO porte : portes.values()) {
            idComboBox.addItem(porte.id.toString());
        }
    }
}

