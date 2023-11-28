package ca.ulaval.glo2004.domain.assemblers;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.Orientation;
import ca.ulaval.glo2004.domain.accessoires.Fenetre;
import ca.ulaval.glo2004.domain.accessoires.Porte;
import ca.ulaval.glo2004.domain.dtos.FenetreDTO;
import ca.ulaval.glo2004.domain.dtos.PorteDTO;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public class AccessoireAssembler {
    public Porte toPorte(PorteDTO porteDTO, Chalet chalet) {
        return new Porte(porteDTO.id(),
                Imperial.fromString(porteDTO.largeur()),
                Imperial.fromString(porteDTO.hauteur()),
                new Coordonnee(Imperial.fromString(porteDTO.coordonneeX()),
                        new Imperial()),
                chalet,
                chalet.getMurByOrientation(Orientation.valueOf(porteDTO.orientation())));
    }

    public Fenetre toFenetre(FenetreDTO fenetreDTO, Chalet chalet) {
        return new Fenetre(fenetreDTO.id(),
                Imperial.fromString(fenetreDTO.largeur()),
                Imperial.fromString(fenetreDTO.hauteur()),
                new Coordonnee(Imperial.fromString(fenetreDTO.coordonneeX()),
                        Imperial.fromString(fenetreDTO.coordonneeY())),
                chalet,
                chalet.getMurByOrientation(Orientation.valueOf(fenetreDTO.orientation())));
    }
}
