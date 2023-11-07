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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel inputPanel = ajoutPanel();
        add(inputPanel);
        addSeparator();
        JPanel newInputPanel = ModifiePanel();
        add(newInputPanel, BorderLayout.SOUTH);
        mainWindow.getController().registerObserver(this);
        add(newInputPanel);

    }

    private void addSeparator() {
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2)); // Set the separator height
        add(separator);
    }

    private JPanel ajoutPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

        JLabel titreSection = new JLabel(("Ajouter la fenêtre"));
        titreSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreSection.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
        titreSection.setFont(new Font(titreSection.getFont().getName(), Font.BOLD, 12));

        panel.add(titreSection);
        xLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        yLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        largeurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        largeurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hauteurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        String[] idAccessoires = {};
        idComboBox = new JComboBox(idAccessoires);
        JLabel modifierXLabel = new JLabel("Position X:");
        modifierXField = new JTextField(4);
        JLabel modifierYLabel = new JLabel("Position Y:");
        modifierYField = new JTextField(4);
        JLabel modifierLargeurLabel = new JLabel("Largeur:");
        modifierLargeurField = new JTextField(4);
        JLabel modifierHauteurLabel = new JLabel("Hauteur:");
        modifierHauteurField = new JTextField(4);
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

        JLabel titreSection = new JLabel(("Modifier la fenêtre"));
        titreSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreSection.setBorder(BorderFactory.createEmptyBorder(0,15,20,0));
        titreSection.setFont(new Font(titreSection.getFont().getName(), Font.BOLD, 12));

        panel.add(titreSection);
        modifierXLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifierYLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifierLargeurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifierHauteurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

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

