package ca.ulaval.glo2004.domain.util;

public class Imperial {
    int entier;
    int numerateur;
    int denominateur;

    public Imperial(){  }

    public Imperial(int entier, int numerateur, int denominateur) {
        this.entier = entier;
        this.numerateur = numerateur;
        this.denominateur = denominateur;
    }

    public Imperial(int entier) {
        this.entier = entier;
    }

    public int toInt(){
        return entier;
    }

}
