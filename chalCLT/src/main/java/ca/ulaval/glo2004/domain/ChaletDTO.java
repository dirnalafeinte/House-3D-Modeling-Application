package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Imperial;


public class ChaletDTO {
    public Imperial largeur;
    public Imperial longueur;
    public Imperial hauteur;
    public Imperial deltaRainure;
    public Orientation sensDuToit;
    public int angleToit;
    public Imperial epaisseurMur;

    public ChaletDTO(Chalet chalet){
        largeur = chalet.getLargeur();
        longueur = chalet.getLongueur();
        hauteur = chalet.getHauteur();
        deltaRainure = chalet.getDeltaRainure();
        sensDuToit = chalet.getSensDuToit();
        angleToit = chalet.getAngleToit();
        epaisseurMur = chalet.getEpaisseurMur();
    }
}

