package ca.ulaval.glo2004.domain;

public class AccessoireFactory {
    private Mur mur;
    public Porte createPorte(PorteDTO porteDTO, Chalet chalet) {
        this.mur = chalet.getMapMur().get(porteDTO.Orientation);
        return new Porte(porteDTO.Largeur, porteDTO.Hauteur, porteDTO.Coordonnee, chalet, mur);
    }

    public Fenetre createFenetre(FenetreDTO fenetreDTO, Chalet chalet) {
        this.mur = chalet.getMapMur().get(fenetreDTO.Orientation);
        return new Fenetre(fenetreDTO.Largeur, fenetreDTO.Hauteur, fenetreDTO.Coordonnee, chalet, mur);
    }
}
