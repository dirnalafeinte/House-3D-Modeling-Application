package ca.ulaval.glo2004.domain.util;

import java.awt.*;
import java.io.Serializable;

public class UnitConverter implements Serializable {
    public int inchesToPixel(double inches) {
        return (int) (inches * Toolkit.getDefaultToolkit().getScreenResolution() / 12);
    }

    public Imperial pixelToInches(int pixels) {
        int inches = pixels * 12 / Toolkit.getDefaultToolkit().getScreenResolution();
        return new Imperial(0,inches,0,0);
    }

    public int feetToPixel(double feet) {
        return (int) (feet * Toolkit.getDefaultToolkit().getScreenResolution());
    }
}
