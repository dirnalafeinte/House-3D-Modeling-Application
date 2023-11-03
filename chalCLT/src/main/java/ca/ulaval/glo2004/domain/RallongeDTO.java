package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.UUID;

public class RallongeDTO {
    public Imperial HauteurRallonge;
    public Chalet Chalet;
    public UUID id;

    public void RallongeDTO(Rallonge rallonge){
        HauteurRallonge = rallonge.getHauteurRallonge();
        Chalet = rallonge.getChalet();
        id = rallonge.getId();
    }
}