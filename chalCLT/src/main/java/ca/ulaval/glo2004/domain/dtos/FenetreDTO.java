package ca.ulaval.glo2004.domain.dtos;

import ca.ulaval.glo2004.domain.DrawableState;

public record FenetreDTO(String id, String largeur, String hauteur, String coordonneeX, String coordonneeY,
                         String orientation, DrawableState state) {
}
