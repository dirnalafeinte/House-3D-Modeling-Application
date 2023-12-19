package ca.ulaval.glo2004.domain;

import java.io.Serializable;

public record DrawableState(boolean isValid, String errorMessage) implements Serializable {
    public DrawableState(boolean isValid) {
        this(isValid, "");
    }
}
