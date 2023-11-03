package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.*;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.util.UUID;

public class ToitDTO {
    public Imperial HauteurToit;
    public Imperial LargeurToit;
    public UUID id;

    public void ToitDTO(ToitSurface toit){
        HauteurToit = toit.getHauteurToit();
        LargeurToit = toit.getLargeurToit();
        id = toit.getId();
    }
}