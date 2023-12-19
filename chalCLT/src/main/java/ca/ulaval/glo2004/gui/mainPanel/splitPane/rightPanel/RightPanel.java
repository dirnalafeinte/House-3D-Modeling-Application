package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel;

import ca.ulaval.glo2004.gui.MainWindow;
import ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane.TabbedPane;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private final MainWindow mainWindow;
    private final TabbedPane tabbedPane;
    private JTextArea textArea;


    public RightPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        tabbedPane = new TabbedPane(mainWindow);

        init();
    }

    private void init() {

        setPreferredSize(new Dimension(300, 600));
        setLayout(new BorderLayout());

        add(tabbedPane, BorderLayout.NORTH);

        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textAreaPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(textAreaPanel, BorderLayout.CENTER);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
