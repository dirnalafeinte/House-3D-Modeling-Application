package ca.ulaval.glo2004.domain.util;

public class Coordonnee {
    private final Imperial x;
    private final Imperial y;

    public Coordonnee(Imperial x, Imperial y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String x = this.x.toString();
        String y = this.y.toString();
        return "x: " + x + ", y: " + y;
    }

    public Imperial getX() {
        return x;
    }


    public Imperial getY() {
        return y;
    }

    public Coordonnee setX(Imperial newX) {
        return new Coordonnee(newX, y);
    }

    public Coordonnee setY(Imperial newY) {
        return new Coordonnee(x, newY);
    }
}

