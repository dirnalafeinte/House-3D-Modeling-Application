package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.*;
import java.util.List;
import java.util.Map;


public class ChaletDTO {
    private Imperial Largeur;
    private Imperial Longueur;
    private Imperial Hauteur;
    private Imperial DeltaRainure;
    private Orientation SensDuToit;
    private int AngleToit;
    private Imperial EpaisseurMur;
    private Map<Orientation, Mur> MapMur;
    private Toit Toit;
    private Pignon PignonDroit;
    private Pignon PignonGauche;
    private Rallonge Rallonge;

    public ChaletDTO(Chalet chalet){
        this.Largeur = chalet.getLargeur();
        this.Longueur = chalet.getLongueur();
        this.Hauteur = chalet.getHauteur();
        this.DeltaRainure = chalet.getDeltaRainure();
        this.SensDuToit = chalet.getSensDuToit();
        this.AngleToit = chalet.getAngleToit();
        this.EpaisseurMur = chalet.getEpaisseurMur();
        this.MapMur = chalet.getMapMur();
        this.Toit = chalet.getToit();
        this.PignonDroit = chalet.getPignonDroit();
        this.PignonGauche = chalet.getPignonGauche();
        this.Rallonge = chalet.getRallonge();
    }


}

