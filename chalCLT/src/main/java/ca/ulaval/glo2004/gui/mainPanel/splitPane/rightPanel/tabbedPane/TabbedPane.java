package ca.ulaval.glo2004.gui.mainPanel.splitPane.rightPanel.tabbedPane;

import javax.swing.*;

public class TabbedPane extends JTabbedPane {
    private static final String CHALET_PANEL_TITLE = "Chalet";
    private final ChaletPanel chaletPanel= new ChaletPanel();
    private static final String MUR_PANEL_TITLE = "Mur";
    private final MurPanel murPanel = new MurPanel();
    private static final String TOIT_PANEL_TITLE = "Toit";
    private final ToitPanel toitPanel = new ToitPanel();
    private static final String PIGNON_PANEL_TITLE = "Pignon";
    private final PignonPanel pignonPanel = new PignonPanel();
    private static final String RALLONGE_PANEL_TITLE = "Rallonge";
    private final RallongePanel rallongePanel = new RallongePanel();
    private static final String PORTE_PANEL_TITLE = "Porte";
    private final PortePanel portePanel = new PortePanel();
    private static final String FENETRE_PANEL_TITLE = "Fenêtre";
    private final FenetrePanel fenetrePanel = new FenetrePanel();

    public TabbedPane() {
        init();
    }

    private void init() {
        addTab(CHALET_PANEL_TITLE, chaletPanel);
        addTab(MUR_PANEL_TITLE, murPanel);
        addTab(TOIT_PANEL_TITLE, toitPanel);
        addTab(PIGNON_PANEL_TITLE, pignonPanel);
        addTab(RALLONGE_PANEL_TITLE, rallongePanel);
        addTab(PORTE_PANEL_TITLE, portePanel);
        addTab(FENETRE_PANEL_TITLE, fenetrePanel);
    }
}
