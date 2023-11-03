package ca.ulaval.glo2004.domain.util;

import java.awt.*;

public class Unite {

    public Imperial pixelToPouce(int pixel) {
        int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        return new Imperial(pixel/screenResolution);
    }
    public int pouceToPixel(Imperial pouce) {
        int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        return screenResolution * pouce.toInt();
    }
}
