package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

import java.util.List;

public class MurDTO {
    private Orientation Cote;
    private List<Accessoire> Accessoires;
    private Chalet Chalet;

    public void MurDTO(Mur mur){
        this.Cote = mur.getCote();
        this.Accessoires = mur.getAccessoires();
        this.Chalet = mur.getChalet();
    }
}