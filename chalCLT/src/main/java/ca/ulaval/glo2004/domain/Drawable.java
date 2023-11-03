package ca.ulaval.glo2004.domain;

import java.awt.*;
import java.util.*;
import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

public abstract class Drawable {
    private UUID id ;
    private Map<Vue, ArrayList<ArrayList<Imperial>>> sommets; // { Vue: [[x], [y]] }
    private Color couleur;
    private Chalet chalet;

    public Drawable(Chalet chalet) {
        this.id = UUID.randomUUID();
        this.sommets = new HashMap<>();
        this.couleur = Color.BLACK;
        this.chalet = chalet;
        //calculateSommets(); // Remplir la hashmap avec les Vues et sommets
    }

    public UUID getId() {
        return id;
    }

    public Map<Vue, ArrayList<ArrayList<Imperial>>> getSommets() {
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

    public void calculateSommets(){

        if (chalet.getSensDuToit() == Orientation.FACADE || chalet.getSensDuToit() == Orientation.ARRIERE){
            sommets.put(Vue.PLAN, calculateSommetsPlan1());
            sommets.put(Vue.FACADE, calculateSommetsFacade1());
            sommets.put(Vue.GAUCHE, calculateSommetsGauche1());
            sommets.put(Vue.ARRIERE, calculateSommetsArriere1());
            sommets.put(Vue.DROITE, calculateSommetsDroite1());
        }
        else if (chalet.getSensDuToit() == Orientation.DROITE || chalet.getSensDuToit() == Orientation.GAUCHE){
            sommets.put(Vue.PLAN, calculateSommetsPlan2());
            sommets.put(Vue.FACADE, calculateSommetsFacade2());
            sommets.put(Vue.GAUCHE, calculateSommetsGauche2());
            sommets.put(Vue.ARRIERE, calculateSommetsArriere2());
            sommets.put(Vue.DROITE, calculateSommetsDroite2());
        }
    }

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is FACADE or ARRIERE
    private ArrayList<ArrayList<Imperial>> calculateSommetsPlan1(){
        int moitie_epaisseur = (chalet.getEpaisseurMur().toInt() + chalet.getDeltaRainure().toInt())/2;
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 8 sommets de la facade en plan
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(0));

        // 8 sommets de l'arriere en plan
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));

        // 8 sommets de la gauche en plan
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));

        // 8 sommets de la droite en plan
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));


        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is PLAN and the sensDuToit is DROITE or GAUCHE
    private ArrayList<ArrayList<Imperial>> calculateSommetsPlan2(){
        int moitie_epaisseur = (chalet.getEpaisseurMur().toInt() + chalet.getDeltaRainure().toInt())/2;
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 8 sommets de la facade en plan
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(0));

        // 8 sommets de l'arriere en plan
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur * 2));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));

        // 8 sommets de la gauche en plan
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));

        // 8 sommets de la droite en plan
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur * 2));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(chalet.getLargeur().toInt()));
        
        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is FACADE and the sensDuToit is FACADE or ARRIERE
    private ArrayList<ArrayList<Imperial>> calculateSommetsFacade1(){
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        myListX.add(new Imperial(0));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is FACADE and the sensDuToit is DROITE or GAUCHE
    private ArrayList<ArrayList<Imperial>> calculateSommetsFacade2(){
        int moitie_epaisseur = (chalet.getEpaisseurMur().toInt() + chalet.getDeltaRainure().toInt())/2;
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 4 sommets des bordures de gauche
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

        // 4 sommets du mur de facade
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

        // 4 sommets des bordures de droite
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLongueur().toInt()));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(chalet.getLongueur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is GAUCHE and the sensDuToit is FACADE or ARRIERE
    private ArrayList<ArrayList<Imperial>> calculateSommetsGauche1(){
        int moitie_epaisseur = (chalet.getEpaisseurMur().toInt() + chalet.getDeltaRainure().toInt())/2;
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        // 4 sommets des bordures de gauche
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

        // 4 sommets du mur de cote
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

        // 4 sommets des bordures de droite
        myListX.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLargeur().toInt()));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLargeur().toInt()));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(chalet.getLargeur().toInt() - moitie_epaisseur));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

        myList.add(myListX);
        myList.add(myListY);
        return myList;
    }

    //briefly, this method is used to calculate the sommets when the Vue is GAUCHE and the sensDuToit is DROITE or GAUCHE
    private ArrayList<ArrayList<Imperial>> calculateSommetsGauche2(){
        ArrayList<ArrayList<Imperial>> myList = new ArrayList<>();
        ArrayList<Imperial> myListX = new ArrayList<>();
        ArrayList<Imperial> myListY = new ArrayList<>();

        myListX.add(new Imperial(0));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLargeur().toInt()));
        myListY.add(new Imperial(0));
        myListX.add(new Imperial(chalet.getLargeur().toInt()));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));
        myListX.add(new Imperial(0));
        myListY.add(new Imperial(chalet.getHauteur().toInt()));

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
