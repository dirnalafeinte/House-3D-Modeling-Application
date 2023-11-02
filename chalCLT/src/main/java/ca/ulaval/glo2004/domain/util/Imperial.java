package ca.ulaval.glo2004.domain.util;

// Toutes les dimensions sont en valeurs réelles ( **la gestion des nombres fractionnaires n’est pas demandée pour l’instant **)
// Puisqu'on gere seulement les entiers pour l'instant les fonctions utilise juste les entiers. Ca sera a modifier.

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

    public Imperial add(Imperial that) {
        return new Imperial(that.entier + this.entier);
    }

    public Imperial substract(Imperial that) {
        return new Imperial(this.entier - that.entier);
    }

    public Imperial multiply(Imperial that) {
        return new Imperial(this.entier*that.entier);
    }

    public Imperial getHalf() {
        return new Imperial(this.entier/2);
    }

    public boolean equals(Imperial that){
        return this.entier == that.entier;
    }

    public boolean lessThan(Imperial that){
        return this.entier < that.entier;
    }

    public int toInt(){
        return entier;
    }

    @Override
    public String toString(){
        return Integer.toString(this.entier);
    }



}
