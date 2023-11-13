package ca.ulaval.glo2004.gui.action;

import ca.ulaval.glo2004.gui.Toast;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FadeOutToastAction implements ActionListener {
    private static final float MIN_OPACITY = 0.1F;
    private final Toast toast;
    private float opacity;

    public FadeOutToastAction(Toast toast, float opacity) {
        this.toast = toast;
        this.opacity = opacity;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        opacity -= 0.1F;
        toast.setOpacity(opacity);
        if (opacity <= MIN_OPACITY) {
            toast.setVisible(false);
            ((Timer) e.getSource()).stop();
        }
    }
}
