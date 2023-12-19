package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.domain.dtos.PignonDTO;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;

public class PignonPanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JComboBox pignonComboBox;
    private JLabel idLabel;
    private PignonDTO pignonDroitDTO, pignonGaucheDTO;
    private JButton afficherButton;

    public PignonPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        setLayout(new FlowLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Pignons:"));
        pignonComboBox = new JComboBox();
        pignonComboBox.addItem("Droit");
        pignonComboBox.addItem("Gauche");
        pignonComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (pignonComboBox.getSelectedItem().equals("Droit")) {
                    idLabel.setText(pignonDroitDTO.id());
                } else {
                    idLabel.setText(pignonGaucheDTO.id());
                }
            }
        });
        JLabel idTitleLabel = new JLabel("ID: ");
        idLabel = new JLabel("");
        afficherButton = new JButton("Afficher rallonge");
        afficherButton.addActionListener(e -> {
            if (idLabel.getText() != null) {
                mainWindow.getController().afficherDrawable(idLabel.getText());
            }
        });
        addComponentToPanel(pignonComboBox);
        addComponentToPanel(idTitleLabel);
        addComponentToPanel(idLabel);
        addComponentToPanel(afficherButton);
        mainWindow.getController().registerObserver(this);
        update();
    }

    private void addComponentToPanel(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(component);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    @Override
    public void update() {
        pignonDroitDTO = mainWindow.getController().getPignonDroitDTO();
        pignonGaucheDTO = mainWindow.getController().getPignonGaucheDTO();
        updateFields();
    }

    private void updateFields() {
        String side = (String) pignonComboBox.getSelectedItem();
        if (side.equals("Droit")) {
            idLabel.setText(pignonDroitDTO.id());
        } else {
            idLabel.setText(pignonGaucheDTO.id());
        }
    }
}

