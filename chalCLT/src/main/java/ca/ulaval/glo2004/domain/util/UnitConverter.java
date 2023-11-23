package ca.ulaval.glo2004.domain.util;

import java.awt.*;

public class UnitConverter {
    public int inchesToPixel(double inches) {
        return (int) (inches * Toolkit.getDefaultToolkit().getScreenResolution() / 12);
    }

    public double pixelToInches(double pixels) {
        return pixels / Toolkit.getDefaultToolkit().getScreenResolution();
    }

    public int feetToPixel(double feet) {
        return (int) (feet * Toolkit.getDefaultToolkit().getScreenResolution());
    }
}
