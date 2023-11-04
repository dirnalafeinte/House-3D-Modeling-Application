package ca.ulaval.glo2004.domain.util;

public class Coordonnee {
    private Imperial x;
    private Imperial y;

    public Coordonnee(Imperial x, Imperial y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String X = x.toString();
        String Y = y.toString();
        return "x: " + X + " Y: " + Y;
    }

    public Imperial getX() {
        return x;
    }

    public void setX(Imperial x) {
        this.x = x;
    }

    public Imperial getY() {
        return y;
    }

    public void setY(Imperial y) {
        this.y = y;
    }
}

