package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import java.util.List;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public abstract class Drawable {
    private UUID id ;
    protected final Map<Vue, List<Coordonnee>> sommets = new HashMap<>();
    private Color couleur;
    protected Chalet chalet;

    public Drawable(Chalet chalet) {
        this.id = UUID.randomUUID();
        this.couleur = Color.BLACK;
        this.chalet = chalet;
        //calculateSommets(); // Remplir la hashmap avec les Vues et sommets
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
    public boolean EstContenu(Vue vue, Coordonnee coordonnee){
        return false;
    }

    //briefly, this method is used to check if a Drawable is inside another Drawable in the given Vue
    public boolean EstContenu(Drawable that){
        return false;
    }

    //briefly,
    public Imperial getDistanceMinEntre(Drawable that){
        return null;
    }

    public abstract void calculateSommets();

    //public void calculateSommets(){
//definition:
    //    if (chalet.getSensDuToit() == Orientation.FACADE || chalet.getSensDuToit() == Orientation.ARRIERE){
    //        sommets.put(Vue.PLAN, calculateSommetsPlan1());
    //        sommets.put(Vue.FACADE, calculateSommetsFacade1());
    //        sommets.put(Vue.GAUCHE, calculateSommetsGauche1());
    //        sommets.put(Vue.ARRIERE, calculateSommetsArriere1());
    //        sommets.put(Vue.DROITE, calculateSommetsDroite1());
    //    }
    //    else if (chalet.getSensDuToit() == Orientation.DROITE || chalet.getSensDuToit() == Orientation.GAUCHE){
    //        sommets.put(Vue.PLAN, calculateSommetsPlan2());
    //        sommets.put(Vue.FACADE, calculateSommetsFacade2());
    //        sommets.put(Vue.GAUCHE, calculateSommetsGauche2());
    //        sommets.put(Vue.ARRIERE, calculateSommetsArriere2());
    //        sommets.put(Vue.DROITE, calculateSommetsDroite2());
    //    }
    //}

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is FACADE or ARRIERE
    public ArrayList<ArrayList<Imperial>> calculateSommetsPlan1(){
        int moitie_epaisseur = (int)(Imperial.feetAndInchesAndFractionsToDouble(chalet.getEpaisseurMur().add(chalet.getDeltaRainure().divide(2))));
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 8 sommets de la facade en plan
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur * 2));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur * 2));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(chalet.getLongueur());
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add((chalet.getLongueur()));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));

        // 8 sommets de l'arriere en plan
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getLargeur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(chalet.getLongueur());
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(chalet.getLongueur());
        myListY.add(chalet.getLargeur());

        // 8 sommets de la gauche en plan
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));

        // 8 sommets de la droite en plan
        myListX.add(chalet.getLongueur());
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(chalet.getLongueur());
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));


        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is DROITE or GAUCHE
    public ArrayList<ArrayList<Imperial>> calculateSommetsPlan2(){
        int moitie_epaisseur = (int)(Imperial.feetAndInchesAndFractionsToDouble(chalet.getEpaisseurMur().add(chalet.getDeltaRainure().divide(2))));
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 8 sommets de la facade en plan
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));

        // 8 sommets de l'arriere en plan
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur());

        // 8 sommets de la gauche en plan
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getLargeur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getLargeur());

        // 8 sommets de la droite en plan
        myListX.add(chalet.getLongueur());
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur*2)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getLargeur());
        myListX.add(chalet.getLongueur());
        myListY.add(chalet.getLargeur());

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is FACADE and the sensDuToit is FACADE or ARRIERE
    private ArrayList<ArrayList<Imperial>> calculateSommetsFacade1(){
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur());
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur());
        myListY.add(chalet.getHauteur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getHauteur());

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is FACADE and the sensDuToit is DROITE or GAUCHE
    private ArrayList<ArrayList<Imperial>> calculateSommetsFacade2(){
        int moitie_epaisseur = (int)(Imperial.feetAndInchesAndFractionsToDouble(chalet.getEpaisseurMur().add(chalet.getDeltaRainure().divide(2))));
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 4 sommets des bordures de gauche
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getHauteur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getHauteur());

        // 4 sommets du mur de facade
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getHauteur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getHauteur());

        // 4 sommets des bordures de droite
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur());
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLongueur());
        myListY.add(chalet.getHauteur());
        myListX.add(chalet.getLongueur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getHauteur());

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is GAUCHE and the sensDuToit is FACADE or ARRIERE
    private ArrayList<ArrayList<Imperial>> calculateSommetsGauche1(){
        int moitie_epaisseur = (int)(Imperial.feetAndInchesAndFractionsToDouble(chalet.getEpaisseurMur().add(chalet.getDeltaRainure().divide(2))));
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 4 sommets des bordures de gauche
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getHauteur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getHauteur());

        // 4 sommets du mur de cote
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getHauteur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur));
        myListY.add(chalet.getHauteur());

        // 4 sommets des bordures de droite
        myListX.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLargeur());
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLargeur());
        myListY.add(chalet.getHauteur());
        myListX.add(chalet.getLargeur().substract(Imperial.DoubleToFeetAndInchesAndFractions(moitie_epaisseur)));
        myListY.add(chalet.getHauteur());

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is GAUCHE and the sensDuToit is DROITE or GAUCHE
    private ArrayList<ArrayList<Imperial>> calculateSommetsGauche2(){
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLargeur());
        myListY.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListX.add(chalet.getLargeur());
        myListY.add(chalet.getHauteur());
        myListX.add(Imperial.DoubleToFeetAndInchesAndFractions(0));
        myListY.add(chalet.getHauteur());

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is ARRIERE and the sensDuToit is FACADE or ARRIERE
    private ArrayList<ArrayList<Imperial>> calculateSommetsArriere1(){
        return calculateSommetsFacade1();
    }

    //briefly, this method is used to calculate the sommets when the Vue is ARRIERE and the sensDuToit is DROITE or GAUCHE
    private ArrayList<ArrayList<Imperial>> calculateSommetsArriere2(){
        return calculateSommetsFacade2();
    }

    // Same as calculateSommetsGauche1() but when we will implement Toit, we will need to adapt it
    //briefly, this method is used to calculate the sommets when the Vue is DROIT and the sensDuToit is FACADE or ARRIERE
    private ArrayList<ArrayList<Imperial>> calculateSommetsDroite1(){
        return calculateSommetsGauche1();
    }

    // Same as calculateSommetsGauche2() but when we will implement Toit, we will need to add to it
    //briefly, this method is used to calculate the sommets when the Vue is DROIT and the sensDuToit is DROITE or GAUCHE
    private ArrayList<ArrayList<Imperial>> calculateSommetsDroite2(){
        return calculateSommetsGauche2();
    }

    public void ajouterAccessoireSommets(Vue currentVue, Accessoire accessoire) {
        //sommet1 = accessoire.getCoordonnee();
        //sommets.get(currentVue).add();

    }
}
