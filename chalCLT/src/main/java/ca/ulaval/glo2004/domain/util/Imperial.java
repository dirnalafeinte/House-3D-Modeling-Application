package ca.ulaval.glo2004.domain.util;

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

    private Imperial(int feet, int inches, int numerator, int denominator) {
        this.feet = feet;
        this.inches = inches;
        this.numerator = numerator;
        this.denominator = denominator == 0 ? 1 : denominator;
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

    public static Imperial fromInches(double chiffre) {
        double df;
        long lUpperPart = 1;
        long lLowerPart = 1;
        int entier = (int) chiffre;
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
        int feet = entier / 12;
        int inches = entier % 12;
        int numerateur = (int) lUpperPart;
        int denominateur = (int) lLowerPart;
        return new Imperial(feet, inches, numerateur, denominateur);
    }

    public static Imperial fromString(String string) {
        int feet = 0;
        int inches = 0;
        int numerator = 0;
        int denominator = 1;

        String[] parts = string.split(" ");

        for (String part : parts) {
            if (part.contains("'")) {
                feet = Integer.parseInt(part.replace("'", ""));
            } else if (part.contains("\"")) {
                inches = Integer.parseInt(part.replace("\"", ""));
            } else if (part.contains("/")) {
                String[] fractionParts = part.split("/");
                numerator = Integer.parseInt(fractionParts[0]);
                denominator = Integer.parseInt(fractionParts[1]);
            }
        }
        return new Imperial(feet, inches, numerator, denominator);
    }

    public double toFeet() {
        return (double) feet + (double) inches / 12.0 + (double) numerator / (double) denominator / 12.0;
    }

    public double toInches() {
        return (double) feet * 12.0 + (double) inches + (double) numerator / (double) denominator;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if (feet != 0) {
            sb.append(feet).append("' ");
        } if (inches != 0) {
            sb.append(inches).append("\" ");
        } if (numerator != 0) {
            sb.append(numerator).append("/").append(denominator);
        }

        return sb.toString().trim();
    }

    public Imperial add(Imperial that) {
        double inches = this.toInches() + that.toInches();
        return Imperial.fromInches(inches);
    }

    public Imperial subtract(Imperial that) {
        double inches = this.toInches() - that.toInches();
        return Imperial.fromInches(inches);
    }

    public Imperial multiply(Imperial that) {
        double inches = this.toInches() * that.toInches();
        return Imperial.fromInches(inches);
    }

    public Imperial multiplyBy(int factor) {
        double inches = this.toInches() * factor;
        return Imperial.fromInches(inches);
    }

    public Imperial multiplyBy(double factor) {
        double inches = this.toInches() * factor;
        return Imperial.fromInches(inches);
    }

    public Imperial divide(Imperial that) {
        double inches = this.toInches()* this.toInches();
        return Imperial.fromInches(inches);
    }

    public Imperial divideBy(int divisor) {
        double inches = this.toInches() / divisor;
        return Imperial.fromInches(inches);
    }

    public Imperial divideBy(double divisor) {
        double inches = this.toInches() / divisor;
        return Imperial.fromInches(inches);
    }

    public Imperial abs() {
        return new Imperial(Math.abs(feet), Math.abs(inches), Math.abs(numerator), Math.abs(denominator));
    }

    public static Imperial min(Imperial a, Imperial b) {
        return a.lessThan(b) ? a : b;
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
