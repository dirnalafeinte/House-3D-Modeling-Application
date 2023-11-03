package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

import java.util.UUID;

public class RallongeDTO {
    public int HauteurRallonge;
    public Chalet Chalet;
    public UUID id;

    public void RallongeDTO(Rallonge rallonge){
        HauteurRallonge = rallonge.getHauteurRallonge();
        //Chalet = rallonge.getChalet();
        //id = rallonge.getId();
    }
}