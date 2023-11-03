package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;
import ca.ulaval.glo2004.domain.*;
import java.util.List;
import java.util.Map;


public class ChaletDTO {
    public Imperial Largeur;
    public Imperial Longueur;
    public Imperial Hauteur;
    public Imperial DeltaRainure;
    public Orientation SensDuToit;
    public int AngleToit;
    public Imperial EpaisseurMur;
    public Map<Orientation, Mur> MapMur;
    public Toit Toit;
    public Pignon PignonDroit;
    public Pignon PignonGauche;
    public Rallonge Rallonge;

    public ChaletDTO(Chalet chalet){
        Largeur = chalet.getLargeur();
        Longueur = chalet.getLongueur();
        Hauteur = chalet.getHauteur();
        DeltaRainure = chalet.getDeltaRainure();
        SensDuToit = chalet.getSensDuToit();
        AngleToit = chalet.getAngleToit();
        EpaisseurMur = chalet.getEpaisseurMur();
        //MapMur = chalet.getMapMur();
        //Toit = chalet.getToit();
        //PignonDroit = chalet.getPignonDroit();
        //PignonGauche = chalet.getPignonGauche();
        //Rallonge = chalet.getRallonge();
    }


}

