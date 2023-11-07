package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.dtos.AddFenetreDTO;
import ca.ulaval.glo2004.domain.dtos.FenetreDTO;
import ca.ulaval.glo2004.domain.error.exceptions.IllegalFenetreException;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class FenetrePanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JTextField xField, yField, largeurField, hauteurField, modifierXField, modifierYField, modifierLargeurField, modifierHauteurField;
    private JComboBox orientationComboBox, idComboBox;

    private Map<String, FenetreDTO> fenetresById;

    public FenetrePanel(MainWindow mainWindow) {
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
        JLabel yLabel = new JLabel("Position Y:");
        yField = new JTextField(5);
        JLabel largeurLabel = new JLabel("Largeur:");
        largeurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);
        String[] orientationMur = {Orientation.FACADE.toString(), Orientation.ARRIERE.toString(), Orientation.GAUCHE.toString(), Orientation.DROITE.toString()};
        orientationComboBox = new JComboBox(orientationMur);
        JButton ajouter = new JButton("Ajouter");

        panel.add(xLabel);
        panel.add(xField);
        panel.add(yLabel);
        panel.add(yField);
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
                    String coordonneeX = xField.getText();
                    String coordonneeY = yField.getText();
                    String largeur = largeurField.getText();
                    String hauteur = hauteurField.getText();
                    String orientation = orientationComboBox.getSelectedItem().toString();
                    AddFenetreDTO fenetreDTO = new AddFenetreDTO(largeur, hauteur, coordonneeX, coordonneeY, orientation);;
                    mainWindow.getController().addFenetre(fenetreDTO);

                    xField.setText("");
                    yField.setText("");
                    largeurField.setText("");
                    hauteurField.setText("");
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides avant d'ajouter une fenetre.");
                }
                catch (IllegalFenetreException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });

        return panel;
    }

    private JPanel ModifiePanel() {
        JPanel panel = new JPanel(new FlowLayout());

        String[] idAccessoires = {};
        idComboBox = new JComboBox(idAccessoires);
        JLabel modifierXLabel = new JLabel("Position X:");
        modifierXField = new JTextField(5);
        JLabel modifierYLabel = new JLabel("Position Y:");
        modifierYField = new JTextField(5);
        JLabel modifierLargeurLabel = new JLabel("Largeur:");
        modifierLargeurField = new JTextField(5);
        JLabel modifierHauteurLabel = new JLabel("Hauteur:");
        modifierHauteurField = new JTextField(5);
        JButton modifier = new JButton("Modifier");
        JButton supprimer = new JButton("Supprimer");

        idComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idComboBox.getSelectedItem() != null) {
                    FenetreDTO fenetre = fenetresById.get(idComboBox.getSelectedItem().toString());
                    modifierXField.setText(fenetre.coordonneeX());
                    modifierYField.setText(fenetre.coordonneeY());
                    modifierLargeurField.setText(fenetre.largeur());
                    modifierHauteurField.setText(fenetre.hauteur());
                } else {
                    modifierXField.setText("");
                    modifierYField.setText("");
                    modifierLargeurField.setText("");
                    modifierHauteurField.setText("");
                }
            }
        });

        panel.add(idComboBox);
        panel.add(modifierXLabel);
        panel.add(modifierXField);
        panel.add(modifierYLabel);
        panel.add(modifierYField);
        panel.add(modifierLargeurLabel);
        panel.add(modifierLargeurField);
        panel.add(modifierHauteurLabel);
        panel.add(modifierHauteurField);

        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idComboBox.getSelectedItem().toString();
                FenetreDTO oldFenetre = fenetresById.get(idComboBox.getSelectedItem().toString());
                String coordonneeX = modifierXField.getText();
                String coordonneeY = modifierYField.getText();
                String largeur = modifierLargeurField.getText();
                String hauteur = modifierHauteurField.getText();
                FenetreDTO fenetre = new FenetreDTO(id, largeur, hauteur, coordonneeX, coordonneeY, oldFenetre.orientation());
                mainWindow.getController().modifyFenetre(fenetre);
            }
        });

        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FenetreDTO fenetre = fenetresById.get(idComboBox.getSelectedItem().toString());
                mainWindow.getController().deleteFenetre(fenetre);
            }
        });

        panel.add(modifier);
        panel.add(supprimer);

        return panel;
    }

    @Override
    public void update() {
        fenetresById = mainWindow.getController().getFenetresById();
        updateComboBox();
    }

    private void updateComboBox() {
        idComboBox.removeAllItems();
        for (String id : fenetresById.keySet()) {
            idComboBox.addItem(id);
        }
    }
}

