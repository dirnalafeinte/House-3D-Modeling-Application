package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.domain.Observer;
import ca.ulaval.glo2004.domain.dtos.RallongeDTO;
import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RallongePanel extends JPanel implements Observer {
    private final MainWindow mainWindow;
    private JLabel idLabel;
    private RallongeDTO rallongeDTO;
    private JButton afficherButton;

    public RallongePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        init();
        updateFields();
    }

    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Rallonge:"));
        JLabel idTitleLabel = new JLabel("ID: ");
        idLabel = new JLabel("");
        afficherButton = new JButton("Afficher rallonge");
        afficherButton.addActionListener(e -> {
            if (idLabel.getText() != null) {
                mainWindow.getController().afficherDrawable(idLabel.getText());
            }
        });
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
        rallongeDTO = mainWindow.getController().getRallongeDTO();
        updateFields();
    }

    private void updateFields() {
        if (rallongeDTO != null) {
            idLabel.setText(rallongeDTO.id());
        }
    }
}

