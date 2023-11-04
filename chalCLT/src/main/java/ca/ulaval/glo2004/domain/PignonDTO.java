package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.UUID;

public class PignonDTO {
    public UUID id;
    public boolean isPignonDroit;

    public void PignonDTO(Pignon pignon){
        id = pignon.getId();
        isPignonDroit = pignon.isPignonDroit();
    }
}