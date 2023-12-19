package ca.ulaval.glo2004.domain;

import java.io.Serializable;

public record DrawableState(boolean isValid, String errorMessage) {
    public DrawableState(boolean isValid) {
        this(isValid, "");
    }
}
