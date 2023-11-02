package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

public class ToitDTO {
    private Chalet Chalet;

    public void ToitDTO(Toit toit){
        this.Chalet = toit.getChalet();
    }
}