package ca.ulaval.glo2004.gui.mainPanel.topPanel.topRightPanel;

import ca.ulaval.glo2004.gui.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TopRightPanel extends JPanel implements ActionListener {
    private static final LayoutManager LAYOUT_MANAGER = new FlowLayout(FlowLayout.RIGHT);
    private final MainWindow mainWindow;
    private final GrilleCheckBox grilleCheckBox;
    private final TextField textField;

    public TopRightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        grilleCheckBox = new GrilleCheckBox(mainWindow);
        textField = new TextField("6\"", 10);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                mainWindow.getController().setIntervalGrid(textField.getText());
            }
        });
        init();
    }

    public boolean isGrille() {
        return grilleCheckBox.isSelected();
    }

    private void init() {
        setLayout(LAYOUT_MANAGER);
        JLabel label = new JLabel("Distance : ");
        add(label);
        add(textField);
        add(grilleCheckBox);
        grilleCheckBox.addActionListener(this);
    }

    public TextField getTextField() {
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getMainPanel().getSplitPane().getCenterPanel().getDrawingPanel().update();
    }
}
