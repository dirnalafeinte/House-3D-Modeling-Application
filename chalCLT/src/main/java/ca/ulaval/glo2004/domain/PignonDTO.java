package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;

public class PignonDTO {
    private Chalet Chalet;
    private boolean EstPignonDroit;
    private int LargeurPignon;
    private int HauteurPignon;

    public void PignonDTO(Pignon pignon){
        this.Chalet = pignon.getChalet();
        this.EstPignonDroit = pignon.getEstPignonDroit();
        this.LargeurPignon = pignon.getLargeurPignon();
        this.HauteurPignon = pignon.getHauteurPignon();

    }
}