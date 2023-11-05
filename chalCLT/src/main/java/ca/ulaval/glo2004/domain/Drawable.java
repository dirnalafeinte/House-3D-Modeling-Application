package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public abstract class Drawable {
    private final UUID id;
    protected final Map<Vue, List<Coordonnee>> sommets = new HashMap<>();
    protected Color color;
    protected final Chalet chalet;

    public Drawable(Chalet chalet) {
        this.chalet = chalet;
        this.id = UUID.randomUUID();
        setColor();
        calculateSommets();
    }

    protected abstract void setColor();

    public abstract void calculateSommets();

    public UUID getId() {
        return id;
    }

    public Map<Vue, List<Coordonnee>> getSommets() {
        return sommets;
    }

    public List<Coordonnee> getSommetsByVue(Vue vue) {
        return sommets.get(vue);
    }

    public Color getColor() {
        return color;
    }

    public boolean estContenu(Vue vue, Coordonnee coordonnee){
        // TODO
        return false;
    }

    public boolean estContenu(Drawable that){
        // TODO
        return false;
    }

    public Imperial getDistanceMinEntre(Drawable that){
        // TODO
        return null;
    }

    public void ajouterAccessoireSommets(Vue currentVue, Accessoire accessoire) {
        // TODO
    }
}
