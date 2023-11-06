package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import java.util.UUID;

public class PorteDTO {
    public UUID id;
    public Imperial Largeur;
    public Imperial Hauteur;
    public Coordonnee Coordonnee;
    public Orientation Orientation;

    public PorteDTO(Porte porte){
        Largeur = porte.getLargeur();
        Hauteur = porte.getHauteur();
        Coordonnee = porte.getCoordonnee();
        id = porte.getId();
    }

    public PorteDTO(Imperial largeur, Imperial hauteur, Coordonnee coordonnee, Orientation orientation) {
        Largeur = largeur;
        Hauteur = hauteur;
        Coordonnee = coordonnee;
        Orientation = orientation;
    }
}