package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.UUID;

public class PignonDTO {
    public Chalet Chalet;
    public boolean EstPignonDroit;
    public Imperial LargeurPignon;
    public Imperial HauteurPignon;
    public UUID id;

    public void PignonDTO(Pignon pignon){
        Chalet = pignon.getChalet();
        LargeurPignon = pignon.getLargeurPignon();
        HauteurPignon = pignon.getHauteurPignon();
        id = pignon.getId();

    }
}