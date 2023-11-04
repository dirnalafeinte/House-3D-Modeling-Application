package ca.ulaval.glo2004.domain.util;

// Toutes les dimensions sont en valeurs réelles ( **la gestion des nombres fractionnaires n’est pas demandée pour l’instant **)
// Puisqu'on gere seulement les entiers pour l'instant les fonctions utilise juste les entiers. Ca sera a modifier.

public class Imperial {
    private int feet;
    private int inches;
    private int numerator;
    private int denominator;

    public Imperial() {
        this.feet = 0;
        this.inches = 0;
        this.numerator = 0;
        this.denominator = 1;
    }

    public Imperial(int feet, int inches, int numerator, int denominator) {
        this.feet = feet;
        this.inches = inches;
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }


    public static Imperial fromInches(int inches) {
        return new Imperial(0, inches, 0, 1);
    }

    public static Imperial fromFeet(int feet) {
        return new Imperial(feet, 0, 0, 1);
    }

    public static Imperial fromFeetAndInches(int feet, int inches) {
        return new Imperial(feet, inches, 0, 1);
    }

    private int doubleToInt(double d){
        return (int) Math.round(d);
    }
    /*
    DoubleToFeetAndInchesAndFractions: prend en paramètre un chiffre de type double et retourne un objet de type Imperial
     */
    public static Imperial InchesToImperial(double chiffre) {
        double df;
        long lUpperPart = 1;
        long lLowerPart = 1;
        int entier = (int)(chiffre);
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
        int feet = entier/12;
        int inches = entier%12;
        int numerateur = (int)lUpperPart;
        int denominateur = (int)lLowerPart;
        return new Imperial(feet, inches, numerateur, denominateur);
    }
    /*
    FeetToInt: prend en paramètre un objet de type Imperial et retourne un chiffre de type double
     */
    public static double ImperialToInches(Imperial imperial) {
        int feet = imperial.feet;
        int inches = imperial.inches;
        int numerator = imperial.numerator;
        int denominator = imperial.denominator;
        int totalInches = feet * 12 + inches;
        return totalInches + (double) numerator /denominator;
    }



    public Imperial add(Imperial that) {
        return new Imperial(feet + that.feet, inches + that.inches, numerator + that.numerator, denominator + that.denominator);
    }

    public Imperial substract(Imperial that) {
        return new Imperial(feet - that.feet, inches - that.inches, numerator - that.numerator, denominator - that.denominator);
    }

    public Imperial multiply(Imperial that) {
        double x = ImperialToInches(this)* ImperialToInches(that);
        return InchesToImperial(x);
    }

    public Imperial divide(Imperial that) {
        double x = ImperialToInches(this)/ ImperialToInches(that);
        return InchesToImperial(x);
    }
    public Imperial divide(int that) {
        double x = ImperialToInches(this)/that;
        return InchesToImperial(x);
    }

    @Override
    public boolean equals(Object that){
        if (this == that) {
            return true;
        }
        if (!(that instanceof Imperial)) {
            return false;
        }
        return equals((Imperial) that);
    }

    public boolean equals(Imperial that){
        return this.feet == that.feet && this.inches == that.inches && this.numerator == that.numerator && this.denominator == that.denominator;
    }

    public boolean notEquals(Object that){
        return !this.equals(that);
    }

    public boolean notEquals(Imperial that){
        return !this.equals(that);
    }

    public boolean lessThan(Imperial that){
        return this.toInches() < that.toInches();
    }

    public boolean greaterThan(Imperial that){
        return this.toInches() > that.toInches();
    }

    public boolean lessOrEquals(Imperial that){
        return this.toInches() <= that.toInches();
    }

    public boolean greaterOrEquals(Imperial that){
        return this.toInches() >= that.toInches();
    }

    public double toFeet() {
        return (double) feet + (double) inches / 12.0 + (double) numerator / (double) denominator / 12.0;
    }

    public double toInches() {
        return (double) feet * 12.0 + (double) inches + (double) numerator / (double) denominator;
    }

    @Override
    public String toString(){
        String feetString = feet == 0 ? "" : feet + "' ";
        String inchesString = inches == 0 ? "" : inches + "\" ";
        String fractionString = numerator == 0 ? "" : numerator + "/" + denominator;
        return (feetString + inchesString + fractionString).trim();
    }
/*
simplify
 */
    private void simplify() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        inches += numerator / denominator;
        numerator %= denominator;
        if (numerator == 0) {
            denominator = 1;
        }
        feet += inches / 12;
        inches %= 12;
    }

    private int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}
