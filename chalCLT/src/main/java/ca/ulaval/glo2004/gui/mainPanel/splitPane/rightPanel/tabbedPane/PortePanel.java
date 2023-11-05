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

public class PortePanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JTextField xField, largeurField, hauteurField;
    private JComboBox orientationField;

    private List<PorteDTO> portes;

    public PortePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JLabel xLabel = new JLabel("Position X:");
        xField = new JTextField(5);
        JLabel largeurLabel = new JLabel("Largeur:");
        largeurField = new JTextField(5);
        JLabel hauteurLabel = new JLabel("Hauteur:");
        hauteurField = new JTextField(5);
        JLabel orientationLabel = new JLabel("Mur:");
        String[] orientationMur = {Orientation.FACADE.toString(), Orientation.ARRIERE.toString(), Orientation.GAUCHE.toString(), Orientation.DROITE.toString()};
        orientationField = new JComboBox(orientationMur);


        JButton ajouter = new JButton("Ajouter");

        panel.add(xLabel);
        panel.add(xField);
        panel.add(largeurLabel);
        panel.add(largeurField);
        panel.add(hauteurLabel);
        panel.add(hauteurField);
        panel.add(orientationField);
        panel.add(ajouter);


        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Imperial x = Imperial.stringToImperial(xField.getText());
                Imperial y = mainWindow.getController().getChalet().getHauteur();
                Coordonnee coordonnee = new Coordonnee(x, y);
                Imperial largeur = Imperial.stringToImperial(largeurField.getText());
                Imperial hauteur = Imperial.stringToImperial(hauteurField.getText());

                PorteDTO porteDTO = new PorteDTO(largeur, hauteur, coordonnee);
                mainWindow.getController().ajouterPorte(porteDTO);

                xField.setText("");
                largeurField.setText("");
                hauteurField.setText("");
            }
        });

        return panel;
    }

    @Override
    public void update() {
//        portes = mainWindow.getController().getPorte();
    }
}

