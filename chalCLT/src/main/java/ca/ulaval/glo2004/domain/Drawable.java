package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import ca.ulaval.glo2004.domain.*;

public abstract class Drawable {
    private UUID id ;
    private Map<Vue, List<Coordonnee>> sommets;
    private Color couleur;
    private Chalet chalet;

    public Drawable(Chalet chalet) {
        this.id = UUID.randomUUID();
        this.sommets = new HashMap<>();
        this.couleur = Color.BLACK;
        this.chalet = chalet;
        calculateSommets(); // Remplir la hashmap avec les Vues et sommets
    }

    public UUID getId() {
        return id;
    }

    public Map<Vue, List<Coordonnee>> getSommets() {
        return sommets;
    }

    public Color getCouleur() {
        return couleur;
    }

    public Chalet getChalet() {
        return chalet;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

//    public void setSommets(Map<Vue, List<Coordonnee>> sommets) {
//        this.sommets = sommets;
//    } // do we want to be able to change the sommets ?

    //briefly, this method is used to check if a point is inside a Drawable in the given Vue
    public boolean contient(Vue vue, Coordonnee coordonnee){
        return false;
    }

    //briefly, this method is used to check if a Drawable is inside another Drawable in the given Vue
    public boolean contient(Drawable that){
        return false;
    }

    //briefly,
    public Imperial getDistanceMinEntre(Drawable that){
        return null;
    }

    private void calculateSommets(){

        if chalet.getToit().getSensDuToit() == Orientation.FACADE || chalet.getToit().getSensDuToit() == Orientation.ARRIERE{
            sommets.put(Vue.PLAN, calculateSommetsPlan1());
            sommets.put(Vue.FACADE, calculateSommetsFacade());
            sommets.put(Vue.GAUCHE, calculateSommetsGauche());
            sommets.put(Vue.ARRIERE, calculateSommetsArriere());
            sommets.put(Vue.DROITE, calculateSommetsDroite());
        }
        else if chalet.getToit().getSensDuToit() == Orientation.DROITE || chalet.getToit().getSensDuToit() == Orientation.GAUCHE{
            sommets.put(Vue.PLAN, calculateSommetsPlan2());
            sommets.put(Vue.FACADE, calculateSommetsFacade());
            sommets.put(Vue.GAUCHE, calculateSommetsGauche());
            sommets.put(Vue.ARRIERE, calculateSommetsArriere());
            sommets.put(Vue.DROITE, calculateSommetsDroite());
        }
    }

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is FACADE or ARRIERE
    private List<Coordonnee> calculateSommetsPlan1(){
        new ArrayList<Coordonnee>();

    }

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is DROITE or GAUCHE
    private List<Coordonnee> calculateSommetsPlan2(){
        new ArrayList<Coordonnee>();

    }

    private List<Coordonnee> calculateSommetsFacade(){
        ArrayList<Coordonnee> myList = new ArrayList<Coordonnee>();
        myList.add(new Coordonnee(0,0));
        myList.add(new Coordonnee(chalet.getLongueur().toInt(), 0));
        myList.add(new Coordonnee(0, chalet.getHauteur().toInt()));
        myList.add(new Coordonnee(chalet.getLongueur().toInt(), chalet.getHauteur().toInt()));
        return myList;
    }

    private List<Coordonnee> calculateSommetsGauche(){
        new ArrayList<Coordonnee>();
    }

    private List<Coordonnee> calculateSommetsArriere(){
        new ArrayList<Coordonnee>();
    }

    private List<Coordonnee> calculateSommetsDroite(){
        new ArrayList<Coordonnee>();
    }
}
