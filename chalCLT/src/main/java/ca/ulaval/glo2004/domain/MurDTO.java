package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

import java.util.List;
import java.util.UUID;

public class MurDTO {
    public Orientation Cote;
    public List<Accessoire> Accessoires;
    public Chalet Chalet;
    public UUID id;

    public void MurDTO(Mur mur){
        Cote = mur.getCote();
        Accessoires = mur.getAccessoires();
        //Chalet = mur.getChalet();
        //id = mur.getId();
    }
}