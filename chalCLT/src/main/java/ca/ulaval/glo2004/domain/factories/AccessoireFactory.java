package ca.ulaval.glo2004.domain.factories;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.Orientation;
import ca.ulaval.glo2004.domain.accessoires.Fenetre;
import ca.ulaval.glo2004.domain.accessoires.Porte;
import ca.ulaval.glo2004.domain.dtos.AddFenetreDTO;
import ca.ulaval.glo2004.domain.dtos.AddPorteDTO;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public class AccessoireFactory {
    public Porte createPorte(AddPorteDTO addPorteDTO, Chalet chalet) {
        return new Porte(Imperial.fromString(addPorteDTO.largeur()),
                Imperial.fromString(addPorteDTO.hauteur()),
                new Coordonnee(Imperial.fromString(addPorteDTO.coordonneeX()),
                        new Imperial()),
                chalet,
                chalet.getMurByOrientation(Orientation.valueOf(addPorteDTO.orientation())));
    }

    public Fenetre createFenetre(AddFenetreDTO addFenetreDTO, Chalet chalet) {
        return new Fenetre(Imperial.fromString(addFenetreDTO.largeur()),
                Imperial.fromString(addFenetreDTO.hauteur()),
                new Coordonnee(Imperial.fromString(addFenetreDTO.coordonneeX()),
                        Imperial.fromString(addFenetreDTO.coordonneeY())),
                chalet,
                chalet.getMurByOrientation(Orientation.valueOf(addFenetreDTO.orientation())));
    }
}
