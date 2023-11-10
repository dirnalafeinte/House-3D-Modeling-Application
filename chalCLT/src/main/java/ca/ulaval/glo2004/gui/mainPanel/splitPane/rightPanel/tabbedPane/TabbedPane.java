package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import ca.ulaval.glo2004.gui.MainWindow;

import javax.swing.*;

public class TabbedPane extends JTabbedPane {
    private static final String CHALET_PANEL_TITLE = "Chalet";
    private static final String MUR_PANEL_TITLE = "Mur";
    private static final String TOIT_PANEL_TITLE = "Toit";
    private static final String PIGNON_PANEL_TITLE = "Pignon";
    private static final String RALLONGE_PANEL_TITLE = "Rallonge";
    private static final String PORTE_PANEL_TITLE = "Porte";
    private static final String FENETRE_PANEL_TITLE = "Fenêtre";
    private final MainWindow mainWindow;
    private final ChaletPanel chaletPanel;
    private final MurPanel murPanel;
    private final ToitPanel toitPanel;
    private final PignonPanel pignonPanel;
    private final RallongePanel rallongePanel;
    private final PortePanel portePanel;
    private final FenetrePanel fenetrePanel;

    public TabbedPane(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        chaletPanel = new ChaletPanel(mainWindow);
        murPanel = new MurPanel(mainWindow);
        toitPanel = new ToitPanel(mainWindow);
        pignonPanel = new PignonPanel(mainWindow);
        rallongePanel = new RallongePanel(mainWindow);
        portePanel = new PortePanel(mainWindow);
        fenetrePanel = new FenetrePanel(mainWindow);
        init();
    }

    private void init() {
        setTabPlacement(JTabbedPane.LEFT);

        addTab(CHALET_PANEL_TITLE, chaletPanel);
        addTab(MUR_PANEL_TITLE, murPanel);
        addTab(TOIT_PANEL_TITLE, toitPanel);
        addTab(PIGNON_PANEL_TITLE, pignonPanel);
        addTab(RALLONGE_PANEL_TITLE, rallongePanel);
        addTab(PORTE_PANEL_TITLE, portePanel);
        addTab(FENETRE_PANEL_TITLE, fenetrePanel);
    }
}
