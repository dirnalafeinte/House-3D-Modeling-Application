package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.List;

public class Mur {
    private Orientation cote;
    private List<Accessoire> accessoires;
    private ChaletDTO chalet;

    public Mur(Orientation cote, ChaletDTO chalet) {
        this.cote = cote;
        this.chalet = chalet;
    }



    public void ajouterFenetre(Coordonnee coordonnee, Imperial largeur, Imperial hauteur){

    }
    public void ajouterPorte(Coordonnee coordonnee, Imperial largeur, Imperial hauteur){

    }

    public Orientation getCote() {
        return cote;
    }

    public void setCote(Orientation cote) {
        this.cote = cote;
    }

    public List<Accessoire> getAccessoires() {
        return accessoires;
    }
}
