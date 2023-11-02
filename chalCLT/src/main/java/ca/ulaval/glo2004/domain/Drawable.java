package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import ca.ulaval.glo2004.domain.Vue;

import ca.ulaval.glo2004.domain.util.Coordonnee;



public abstract class Drawable {
    private UUID id ;
    private Map<Vue, List<Coordonnee>> sommets;
    private Color couleur;

    public Drawable() {
        this.id = UUID.randomUUID();
        this.sommets = new HashMap<>();
        this.couleur = Color.BLACK;
    }

    public Map<Vue, List<Coordonnee>> getSommets() {
        return sommets;
    }

    public UUID getId() {
        return id;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

//    public void setSommets(Map<Vue, List<Coordonnee>> sommets) {
//        this.sommets = sommets;
//    } // do we want to be able to change the sommets here ?

    public boolean contient(Vue vue, Coordonnee coordonnee){
        return false;
    }


    private void calculateSommets(){

    }




}