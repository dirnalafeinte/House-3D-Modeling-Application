package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public abstract class Drawable {
    private UUID id ;
    private Map<Vue, ArrayList<Coordonnee>> sommets;
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

    public Map<Vue, ArrayList<Coordonnee>> getSommets() {
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

        if (chalet.getSensDuToit() == Orientation.FACADE || chalet.getSensDuToit() == Orientation.ARRIERE){
            sommets.put(Vue.PLAN, calculateSommetsPlan1());
            sommets.put(Vue.FACADE, calculateSommetsFacade());
            sommets.put(Vue.GAUCHE, calculateSommetsGauche());
            sommets.put(Vue.ARRIERE, calculateSommetsArriere());
            sommets.put(Vue.DROIT, calculateSommetsDroite());
        }
        else if (chalet.getSensDuToit() == Orientation.DROITE || chalet.getSensDuToit() == Orientation.GAUCHE){
            sommets.put(Vue.PLAN, calculateSommetsPlan2());
            sommets.put(Vue.FACADE, calculateSommetsFacade());
            sommets.put(Vue.GAUCHE, calculateSommetsGauche());
            sommets.put(Vue.ARRIERE, calculateSommetsArriere());
            sommets.put(Vue.DROIT, calculateSommetsDroite());
        }
    }

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is FACADE or ARRIERE
    private ArrayList<Coordonnee> calculateSommetsPlan1(){
        ArrayList<Coordonnee> myList = new ArrayList<Coordonnee>();
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is DROITE or GAUCHE
    private ArrayList<Coordonnee> calculateSommetsPlan2(){
        ArrayList<Coordonnee> myList = new ArrayList<Coordonnee>();
        return myList;

    }

    private ArrayList<Coordonnee> calculateSommetsFacade(){
        ArrayList<Coordonnee> myList = new ArrayList<Coordonnee>();
        myList.add(new Coordonnee(new Imperial(0), new Imperial(0)));
        myList.add(new Coordonnee(new Imperial(chalet.getLongueur().toInt()), new Imperial(0)));
        myList.add(new Coordonnee(new Imperial(0), new Imperial(chalet.getHauteur().toInt())));
        myList.add(new Coordonnee(new Imperial (chalet.getLongueur().toInt()), new Imperial(chalet.getHauteur().toInt())));
        return myList;
    }

    private ArrayList<Coordonnee> calculateSommetsGauche(){
        new ArrayList<Coordonnee>();
    }

    private ArrayList<Coordonnee> calculateSommetsArriere(){
        ArrayList<Coordonnee> myList = new ArrayList<Coordonnee>();
        return myList;
    }

    private ArrayList<Coordonnee> calculateSommetsDroite(){
        ArrayList<Coordonnee> myList = new ArrayList<Coordonnee>();
        return myList;
    }
}
