package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.gui.mainPanel.MainPanel;
import ca.ulaval.glo2004.gui.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final ca.ulaval.glo2004.gui.menu.MenuBar menuBar = new MenuBar();

    private final MainPanel mainPanel = new MainPanel();

    public MainWindow() {
        init();
    }

    private void init() {
        setTitle("ChalCLT");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(true);

        setJMenuBar(menuBar);
        add(mainPanel);

        revalidate();
    }
}
