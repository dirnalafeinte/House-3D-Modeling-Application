package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.mainPanel.MainPanel;
import ca.ulaval.glo2004.gui.menu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final MenuBar menuBar = new MenuBar();
    private final MainPanel mainPanel = new MainPanel();
    private final ChaletController chaletController = new ChaletController();

    public MainWindow() {
        init();
    }
p
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
