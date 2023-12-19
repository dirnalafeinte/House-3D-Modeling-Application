package ca.ulaval.glo2004.domain;

public class ChaletMemento {
    private final ChaletController originator;
    private final Chalet state;

    public ChaletMemento(ChaletController originator, Chalet state) {
        this.originator = originator;
        this.state = state;
    }

    public void restore() {
        originator.restore(state);
    }
}
