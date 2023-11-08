package ca.ulaval.glo2004.domain.factories;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.accessoire.Fenetre;
import ca.ulaval.glo2004.domain.Orientation;
import ca.ulaval.glo2004.domain.accessoire.Porte;
import ca.ulaval.glo2004.domain.dtos.AddFenetreDTO;
import ca.ulaval.glo2004.domain.dtos.AddPorteDTO;
import ca.ulaval.glo2004.domain.dtos.FenetreDTO;
import ca.ulaval.glo2004.domain.dtos.PorteDTO;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public class AccessoireFactory {
    public Porte createPorte(AddPorteDTO addPorteDTO, Chalet chalet) {
        return new Porte(Imperial.fromString(addPorteDTO.largeur()), Imperial.fromString(addPorteDTO.hauteur()), new Coordonnee(Imperial.fromString(addPorteDTO.coordonneeX()), chalet.getHauteur()), chalet, chalet.getMurByOrientation(Orientation.valueOf(addPorteDTO.orientation())));
    }

    public Porte createPorte(PorteDTO porteDTO, Chalet chalet) {
        return new Porte(porteDTO.id(), Imperial.fromString(porteDTO.largeur()), Imperial.fromString(porteDTO.hauteur()), new Coordonnee(Imperial.fromString(porteDTO.coordonneeX()), chalet.getHauteur()), chalet, chalet.getMurByOrientation(Orientation.valueOf(porteDTO.orientation())));
    }

    public Fenetre createFenetre(AddFenetreDTO addFenetreDTO, Chalet chalet) {
        return new Fenetre(Imperial.fromString(addFenetreDTO.largeur()), Imperial.fromString(addFenetreDTO.hauteur()), new Coordonnee(Imperial.fromString(addFenetreDTO.coordonneeX()), Imperial.fromString(addFenetreDTO.coordonneeY())), chalet, chalet.getMurByOrientation(Orientation.valueOf(addFenetreDTO.orientation())));
    }

    public Fenetre createFenetre(FenetreDTO fenetreDTO, Chalet chalet) {
        return new Fenetre(fenetreDTO.id() , Imperial.fromString(fenetreDTO.largeur()), Imperial.fromString(fenetreDTO.hauteur()), new Coordonnee(Imperial.fromString(fenetreDTO.coordonneeX()), Imperial.fromString(fenetreDTO.coordonneeY())), chalet, chalet.getMurByOrientation(Orientation.valueOf(fenetreDTO.orientation())));
    }
}
