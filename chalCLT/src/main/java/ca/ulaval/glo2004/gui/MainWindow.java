package ca.ulaval.glo2004.gui;

import ca.ulaval.glo2004.domain.ChaletController;
import ca.ulaval.glo2004.gui.mainPanel.MainPanel;
import ca.ulaval.glo2004.gui.menu.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private final MenuBar menuBar;
    private final MainPanel mainPanel;
    private final ChaletController controller = new ChaletController();

    public MainWindow() {
        menuBar = new MenuBar(controller);
        mainPanel = new MainPanel(controller);
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

    private void initListeners() {
        ActionListener menuItemListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //controller.
            }
        };

        for(JMenuItem item : menuBar.getVueMenu().getMenuItems()){
            item.addActionListener(menuItemListener);
        }
    }
}
