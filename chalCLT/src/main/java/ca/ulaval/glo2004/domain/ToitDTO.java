package ca.ulaval.glo2004.domain;

import java.util.UUID;

public class ToitDTO {
    public UUID id;

    public void ToitDTO(Toit toit){
        id = toit.getId();
    }
}