package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
                    Imperial x = Imperial.stringToImperial(xField.getText());
                    Imperial y = Imperial.stringToImperial(yField.getText());
                    Coordonnee coordonnee = new Coordonnee(x, y);
                    Imperial largeur = Imperial.stringToImperial(largeurField.getText());
                    Imperial hauteur = Imperial.stringToImperial(hauteurField.getText());


                    FenetreDTO fenetreDTO = new FenetreDTO(largeur, hauteur, coordonnee);
                    mainWindow.getController().ajouterFenetre(fenetreDTO);

                    xField.setText("");
                    yField.setText("");
                    largeurField.setText("");
                    hauteurField.setText("");
                }
                catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides avant d'ajouter une fenetre.");
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

