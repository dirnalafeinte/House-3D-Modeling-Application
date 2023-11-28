package ca.ulaval.glo2004.domain;

public record DrawableState(boolean isValid, String errorMessage) {
    public DrawableState(boolean isValid) {
        this(isValid, "");
    }
}
