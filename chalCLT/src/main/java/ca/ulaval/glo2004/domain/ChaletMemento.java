package ca.ulaval.glo2004.domain;

public class ChaletMemento {
    private final Chalet originator;
    private final Chalet state;

    public ChaletMemento(Chalet originator, Chalet state) {
        this.originator = originator;
        this.state = state;
    }

    public void restore() {
        originator.restore(state);
    }
}
