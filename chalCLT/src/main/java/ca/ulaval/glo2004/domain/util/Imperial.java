package ca.ulaval.glo2004.domain.util;

// Toutes les dimensions sont en valeurs réelles ( **la gestion des nombres fractionnaires n’est pas demandée pour l’instant **)
// Puisqu'on gere seulement les entiers pour l'instant les fonctions utilise juste les entiers. Ca sera a modifier.

import java.lang.reflect.Array;

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


    public int doubleToInt(double d){
        return (int) Math.round(d);
    }

    public Imperial (double chiffre) {
        double df;
        long lUpperPart = 1;
        long lLowerPart = 1;
        int entier = doubleToInt(chiffre);
        double f = chiffre - entier;

        df = (double) lUpperPart / lLowerPart;

        while (df != f) {
            if (df < f) {
                lUpperPart = lUpperPart + 1;
            } else {
                lLowerPart = lLowerPart + 1;
                lUpperPart = (long) (f * lLowerPart);
            }
            df = (double) lUpperPart / lLowerPart;
        }
        this.entier = entier;
        this.numerateur = doubleToInt(lUpperPart);
        this.denominateur = doubleToInt(lLowerPart);
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
    //If I wanna do this: this.hauteurToit =  (chalet.Largeur / Math.cos(Math.toRadians(chalet.AngleToit))); How can I do it?
    //answer: this.hauteurToit =  (chalet.Largeur / Math.cos(Math.toRadians(chalet.AngleToit)));

    public Imperial divide(Imperial that) {
        return new Imperial(this.entier/that.entier);
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
// ????????
    public Imperial toImperial(double entier){
        return new Imperial(this.entier);
    }

    public Imperial toImperial(int entier){
        return new Imperial(this.entier);
    }







}
