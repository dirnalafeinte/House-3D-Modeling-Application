package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

public class FenetreDTO {
    private Imperial Largeur;
    private Imperial Hauteur;
    private Coordonnee Coordonnee;

    public FenetreDTO(Fenetre fenetre){
        this.Largeur = fenetre.getLargeur();
        this.Hauteur = fenetre.getHauteur();
        this.Coordonnee = fenetre.getCoordonnee();
    }

}