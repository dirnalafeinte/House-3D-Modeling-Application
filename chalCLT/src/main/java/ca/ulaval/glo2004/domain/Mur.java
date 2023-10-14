package ca.ulaval.glo2004.domain;

public class Mur extends Chalet{
    protected int nbPorte;
    protected int nbFenetre;
    public Mur(int largeur, int longueur, int hauteur, int deltaRainure, int epaisseurMur, String sensToit, int nbPorte, int nbFenetre) {
        super(largeur, longueur, hauteur, deltaRainure, epaisseurMur, sensToit);
        this.nbPorte = nbPorte;
        this.nbFenetre = nbFenetre;
    }

    public int getNbPorte() {
        return nbPorte;
    }

    public int getNbFenetre() {
        return nbFenetre;
    }

    /**
     * À PARLER DE CA À PARLER DE CA À PARLER DE CA À PARLER DE CA À PARLER DE CA À PARLER DE CA À PARLER DE CA À PARLER DE CA À PARLER DE CA
     * @param nbPorte the nbPorte to set
     */
}
