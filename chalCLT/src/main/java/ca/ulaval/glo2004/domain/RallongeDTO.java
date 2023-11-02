package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

public class RallongeDTO {
    private int HauteurRallonge;
    private Chalet Chalet;

    public void RallongeDTO(Rallonge rallonge){
        this.HauteurRallonge = rallonge.getHauteurRallonge();
        this.Chalet = rallonge.getChalet();
    }
}