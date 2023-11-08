package ca.ulaval.glo2004.domain.assemblers;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.accessoire.Fenetre;
import ca.ulaval.glo2004.domain.accessoire.Porte;
import ca.ulaval.glo2004.domain.dtos.*;

public class DTOAssembler {
    public ChaletDTO toChaletDTO(Chalet chalet) {
        return new ChaletDTO(chalet.getLargeur().toString(), chalet.getLongueur().toString(), chalet.getHauteur().toString(), chalet.getDeltaRainure().toString(), chalet.getSensDuToit().toString(), chalet.getAngleToit(), chalet.getEpaisseurMur().toString(), chalet.getDistanceMin().toString());
    }

    public FenetreDTO toFenetreDTO(Fenetre fenetre) {
        return new FenetreDTO(fenetre.getId(), fenetre.getLargeur().toString(), fenetre.getHauteur().toString(), fenetre.getCoordonnee().getX().toString(), fenetre.getCoordonnee().getY().toString(), fenetre.getCote().toString());
    }

    public MurDTO toMurDTO(Mur mur) {
        return new MurDTO(mur.getId(), mur.getCote().toString());
    }

    public PignonDTO toPignonDTO(Pignon pignon) {
        return new PignonDTO(pignon.getId(), pignon.isPignonDroit());
    }

    public PorteDTO toPorteDTO(Porte porte) {
        return new PorteDTO(porte.getId(), porte.getLargeur().toString(), porte.getHauteur().toString(), porte.getCoordonnee().getX().toString(), porte.getCote().toString());
    }

    public RallongeDTO toRallongeDTO(Rallonge rallonge) {
        return new RallongeDTO(rallonge.getId());
    }

    public ToitDTO toToitDTO(Toit toit) {
        return new ToitDTO(toit.getId());
    }
}
