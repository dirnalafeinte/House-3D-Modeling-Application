package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.UUID;

public class FenetreDTO {
    public UUID id;
    public Imperial Largeur;
    public Imperial Hauteur;
    public Coordonnee Coordonnee;

    public FenetreDTO(Fenetre fenetre){
        Largeur = fenetre.getLargeur();
        Hauteur = fenetre.getHauteur();
        Coordonnee = fenetre.getCoordonnee();
        id = fenetre.getId();
    }

    public FenetreDTO(Imperial largeur, Imperial hauteur, Coordonnee coordonnee) {
        Largeur = largeur;
        Hauteur = hauteur;
        Coordonnee = coordonnee;
    }
}