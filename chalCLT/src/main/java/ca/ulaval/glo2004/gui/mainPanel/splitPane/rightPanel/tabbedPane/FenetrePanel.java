package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.exceptions.IllegalFenetreException;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class FenetrePanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JTextField xField, yField, largeurField, hauteurField, modifierXField, modifierYField, modifierLargeurField, modifierHauteurField;
    private JComboBox orientationComboBox, idComboBox;

    private List<FenetreDTO> fenetres;

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
        add(newInputPanel);

    }

    private void addSeparator() {
        JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
//            new JPanel() {

//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.setColor(Color.BLACK);
//                g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
//            }
//        };
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
                    Imperial x = Imperial.stringToImperial(xField.getText());
                    Imperial y = Imperial.stringToImperial(yField.getText());
                    Coordonnee coordonnee = new Coordonnee(x, y);
                    Imperial largeur = Imperial.stringToImperial(largeurField.getText());
                    Imperial hauteur = Imperial.stringToImperial(hauteurField.getText());

                    Orientation orientation = Orientation.valueOf(orientationComboBox.getSelectedItem().toString());
                    FenetreDTO fenetreDTO = new FenetreDTO(largeur, hauteur, coordonnee, orientation);;
                    mainWindow.getController().ajouterFenetre(fenetreDTO);

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
        panel.add(modifier);
        panel.add(supprimer);

        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add action for the new button
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
//        portes = mainWindow.getController().getPorte();
    }
}

