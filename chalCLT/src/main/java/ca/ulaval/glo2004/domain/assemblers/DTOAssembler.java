package ca.ulaval.glo2004.domain.assemblers;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.accessoires.Fenetre;
import ca.ulaval.glo2004.domain.accessoires.Porte;
import ca.ulaval.glo2004.domain.dtos.*;

public class DTOAssembler {
    public ChaletDTO toChaletDTO(Chalet chalet) {
        return new ChaletDTO(chalet.getLargeur().toString(),
                chalet.getLongueur().toString(),
                chalet.getHauteur().toString(),
                chalet.getDeltaRainure().toString(),
                chalet.getEpaisseurMur().toString(),
                chalet.getDistanceMin().toString(),
                90 - chalet.getAngleToit(),
                chalet.getSensDuToit().toString()
        );
    }

    public FenetreDTO toFenetreDTO(Fenetre fenetre) {
        return new FenetreDTO(fenetre.getId(),
                fenetre.getLargeur().toString(),
                fenetre.getHauteur().toString(),
                fenetre.getCoordonnee().getX().toString(),
                fenetre.getCoordonnee().getY().toString(),
                fenetre.getCote().toString(),
                fenetre.getState());
    }

    public MurDTO toMurDTO(Mur mur) {
        return new MurDTO(mur.getId(), mur.getCote().toString(), mur.getState());
    }

    public PignonDTO toPignonDTO(Pignon pignon) {
        return new PignonDTO(pignon.getId(), pignon.isPignonDroit(), pignon.getState());
    }

    public PorteDTO toPorteDTO(Porte porte) {
        return new PorteDTO(porte.getId(),
                porte.getLargeur().toString(),
                porte.getHauteur().toString(),
                porte.getCoordonnee().getX().toString(),
                porte.getCote().toString(),
                porte.getState());
    }

    public RallongeDTO toRallongeDTO(Rallonge rallonge) {
        return new RallongeDTO(rallonge.getId(), rallonge.getState());
    }

    public ToitDTO toToitDTO(Toit toit) {
        return new ToitDTO(toit.getId(), toit.getState());
    }
}
