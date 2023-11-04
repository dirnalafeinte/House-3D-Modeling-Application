package ca.ulaval.glo2004.domain;

import java.util.UUID;

public class RallongeDTO {
    public UUID id;

    public void RallongeDTO(Rallonge rallonge){
        id = rallonge.getId();
    }
}