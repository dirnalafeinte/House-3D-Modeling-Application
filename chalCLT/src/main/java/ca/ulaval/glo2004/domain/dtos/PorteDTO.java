package ca.ulaval.glo2004.domain.dtos;

import ca.ulaval.glo2004.domain.DrawableState;

public record PorteDTO(String id, String largeur, String hauteur, String coordonneeX, String orientation, DrawableState state) {
}
