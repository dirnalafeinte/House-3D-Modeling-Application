package ca.ulaval.glo2004.domain.drawers;

import ca.ulaval.glo2004.domain.Chalet;
import ca.ulaval.glo2004.domain.Drawable;
import ca.ulaval.glo2004.domain.Vue;

import java.awt.*;

public class AfficheurDrawable extends Afficheur {
    private final Drawable drawable;

    public AfficheurDrawable(Chalet chalet, Vue vue, Drawable drawable) {
        super(chalet, vue);
        this.drawable = drawable;
    }

    @Override
    public void draw(Graphics g) {
        drawDrawable(g, drawable);
    }
}
