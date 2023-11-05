package ca.ulaval.glo2004.domain.util;

import java.awt.*;

public class Unite {
    public static int inchesToPixel(double inches) {
        return (int) inches * Toolkit.getDefaultToolkit().getScreenResolution();
    }
}
