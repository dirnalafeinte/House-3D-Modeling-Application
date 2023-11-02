package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

public class PorteDTO {
    private Imperial Largeur;
    private Imperial Hauteur;
    private Coordonnee Coordonnee;

    public PorteDTO(Porte porte){
        this.Largeur = porte.getLargeur();
        this.Hauteur = porte.getHauteur();
        this.Coordonnee = porte.getCoordonnee();
    }

}