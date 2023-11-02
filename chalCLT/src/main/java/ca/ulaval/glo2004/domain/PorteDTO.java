package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

public class PorteDTO {
    public Imperial Largeur;
    public Imperial Hauteur;
    public Coordonnee Coordonnee;
    public UUID id;

    public PorteDTO(Porte porte){
        Largeur = porte.getLargeur();
        Hauteur = porte.getHauteur();
        Coordonnee = porte.getCoordonnee();
        id = porte.getId();

    }

}