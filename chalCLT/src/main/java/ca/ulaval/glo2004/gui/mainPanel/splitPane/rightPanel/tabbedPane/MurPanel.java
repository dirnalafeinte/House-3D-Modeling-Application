package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.domain.Orientation;
import ca.ulaval.glo2004.domain.dtos.MurDTO;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Map;

public class MurPanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JComboBox murComboBox;
    private JLabel idLabel, orientationLabel;
    private JButton afficherButton;
    private Map<Orientation, MurDTO> mursByOrientation;
    private String selectedId;
    private Orientation selectedOrientation;

    public MurPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        init();
    }

    private void init() {
        mainWindow.getController().registerObserver(this);
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Murs:"));
        JLabel idTitleLabel = new JLabel("ID: ");
        idLabel = new JLabel("");
        JLabel orientationTitleLabel = new JLabel("Orientation: ");
        orientationLabel = new JLabel("");
        murComboBox = new JComboBox();
        murComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Orientation orientation = (Orientation) murComboBox.getSelectedItem();
                MurDTO murDTO = mursByOrientation.get(orientation);
                idLabel.setText(murDTO.id());
                orientationLabel.setText(murDTO.cote().toString());
            }
        });
        afficherButton = new JButton("Afficher mur");
        afficherButton.addActionListener(e -> {
            if (murComboBox.getSelectedItem() != null) {
                Orientation orientation = Orientation.valueOf(orientationLabel.getText());
                String id = mursByOrientation.get(orientation).id();
                mainWindow.getController().afficherDrawable(id, orientation);
            }
        });
        add(murComboBox);
        add(idTitleLabel);
        add(idLabel);
        add(orientationTitleLabel);
        add(orientationLabel);
        add(afficherButton);
        update();
    }

    @Override
    public void update() {
        mursByOrientation = mainWindow.getController().getMursByOrientation();
        murComboBox.removeAllItems();
        for (Orientation orientation : mursByOrientation.keySet()) {
            murComboBox.addItem(orientation);
        }
    }
}
