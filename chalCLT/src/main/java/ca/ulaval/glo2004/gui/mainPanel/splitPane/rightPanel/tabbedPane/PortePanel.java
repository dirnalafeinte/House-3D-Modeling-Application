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
import java.util.List;

public class PortePanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JTextField xField, largeurField, hauteurField, modifierXField, modifierLargeurField, modifierHauteurField;
    private JComboBox orientationComboBox;
    private JComboBox idComboBox;
    private String[] idAccessoires = {};
    private List<Porte> portes;

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
        separatorPanel.setPreferredSize(new Dimension(10, 50)); // Set the separator height
        add(separatorPanel, BorderLayout.CENTER);
    }

    private JPanel ajoutPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel xLabel = new JLabel("Position X:");
        xField = new JTextField(4);
        JLabel largeurLabel = new JLabel("Largeur:");
        largeurField = new JTextField(4);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(4);
        String[] orientationMur = {Orientation.FACADE.toString(), Orientation.ARRIERE.toString(), Orientation.GAUCHE.toString(), Orientation.DROITE.toString()};
        orientationComboBox = new JComboBox(orientationMur);
        JButton ajouter = new JButton("Ajouter");

        JLabel titreSection = new JLabel(("Ajouter la porte"));
        titreSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreSection.setBorder(BorderFactory.createEmptyBorder(10,5,20,5));
        titreSection.setFont(new Font(titreSection.getFont().getName(), Font.BOLD, 12));

        panel.add(titreSection);
        xLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        largeurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        largeurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hauteurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


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

                    String [] newArray = new String[idAccessoires.length+1];
                    System.arraycopy(idAccessoires, 0, newArray, 0, idAccessoires.length);
                    newArray[idAccessoires.length] = porteDTO.id.toString();
                    idAccessoires = newArray;
                    update();

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
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        idComboBox = new JComboBox(idAccessoires);
        JLabel modifierXLabel = new JLabel("Position X:");
        modifierXField = new JTextField(4);
        JLabel modifierLargeurLabel = new JLabel("Largeur:");
        modifierLargeurField = new JTextField(4);
        JLabel modifierHauteurLabel = new JLabel("Hauteur:");
        modifierHauteurField = new JTextField(4);
        JButton modifier = new JButton("Modifier");
        JButton supprimer = new JButton("Supprimer");

        JLabel titreSection = new JLabel(("Modifier la porte"));
        titreSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreSection.setBorder(BorderFactory.createEmptyBorder(10,10,20,5));
        titreSection.setFont(new Font(titreSection.getFont().getName(), Font.BOLD, 12));

        panel.add(titreSection);


        panel.add(idComboBox);
        panel.add(modifierXLabel);
        panel.add(modifierXField);
        panel.add(modifierLargeurLabel);
        panel.add(modifierLargeurField);
        panel.add(modifierHauteurLabel);
        panel.add(modifierHauteurField);
        panel.add(modifier);
        panel.add(supprimer);


        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Imperial x = Imperial.stringToImperial(modifierXField.getText());
                    Imperial y = mainWindow.getController().getChalet().getHauteur();
                    Coordonnee coordonnee = new Coordonnee(x, y);
                    Imperial largeur = Imperial.stringToImperial(modifierLargeurField.getText());
                    Imperial hauteur = Imperial.stringToImperial(modifierHauteurField.getText());

                    Orientation orientation = Orientation.valueOf(orientationComboBox.getSelectedItem().toString());
                    PorteDTO porteDTO = new PorteDTO(largeur, hauteur, coordonnee, orientation);
                    mainWindow.getController().modifierPorte(porteDTO);

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
            }
        });
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for the new button
            }
        });
        return panel;
    }

    @Override
    public void update() {
        portes = mainWindow.getController().getChalet().getPortes();
        updateComboBox();
    }

    public void updateComboBox(){
        idComboBox.removeAllItems();
        for (Porte porte : portes) {
            idComboBox.addItem(porte.getId());
        }
    }
}

