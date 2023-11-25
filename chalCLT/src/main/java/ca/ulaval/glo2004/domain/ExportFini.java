package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.HashSet;

public class ExportFini extends Export {

    public ExportFini(Chalet chalet, String path) {
        super(chalet, path);
    }

    @Override
    public void export() {
        try {
            for (int i=0;i<chalet.getMurs().size();i++) {
                List<ArrayList<Coordonnee>> rectangles = prepareRectangleForStl(chalet.getMurs().get(i));
                }
            for(Panneau panneau : Panneau.values()) {
                String fileName = getFileName("Fini", panneau);

                writeFile(fileName, panneau);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static ArrayList<Coordonnee> createAccessoryIntersectionPoints(Mur mur){
        /*
        Deuxieme Algo (plus simple):
        1. Ajouter les points des murs dans une liste
        2. Ajouter les points des accessoires dans une liste
        3. Enlever les doublons
        4. Ajouter les points d'intersection des lignes horizontales et verticales dans une liste
        5. Tri des points d'intersection par le X
        6. Creer les rectangles
        */


        // Initialisation des variables
        ArrayList<Imperial>  listHorizontalLines = new ArrayList<Imperial>();
        ArrayList<Imperial>  listVerticalLines = new ArrayList<Imperial>();
        ArrayList<Coordonnee> listIntersectionPoints = new ArrayList<Coordonnee>();

        // Ajout des points des murs
        for(int i=0; i<mur.getSommetsByVue(mur.getCote().toVue()).size(); i++){
            listVerticalLines.add(mur.getSommetsByVue(mur.getCote().toVue()).get(i).getY());
            listHorizontalLines.add(mur.getSommetsByVue(mur.getCote().toVue()).get(i).getX());
        }
        // Ajout des points des accessoires
        for (int i=0; i<mur.getAccessoires().size(); i++) {
            for (int j=0; j<mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue()).size(); j++) {
                listVerticalLines.add(mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue()).get(j).getY());
                listHorizontalLines.add(mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue()).get(j).getX());
            }
        }
        // Enlever les doublons
        HashSet<Imperial> set = new HashSet<>(listHorizontalLines);
        listHorizontalLines.clear();
        listHorizontalLines.addAll(set);

        set= new HashSet<>(listVerticalLines);
        listVerticalLines.clear();
        listVerticalLines.addAll(set);

        // Ajout des points d'intesection des lignes horizontales et verticales
        for (int i=0; i<listHorizontalLines.size(); i++) {
            for (int j=0; j<listVerticalLines.size(); j++) {
                listIntersectionPoints.add(new Coordonnee(listHorizontalLines.get(i), listVerticalLines.get(j)));
            }
        }

        // Tri des points d'intersection par le X
        listIntersectionPoints.sort(Comparator.comparingDouble(o -> o.getY().toInches()));
        listIntersectionPoints.sort(Comparator.comparingDouble(o -> o.getX().toInches()));
        return listIntersectionPoints;
    }

    protected static List<ArrayList<Coordonnee>> createRectangles(ArrayList<Coordonnee> listIntersectionPoints) {

        List<ArrayList<Coordonnee>> rectangles = new ArrayList<>();
        int p1= 0; //pointer 1
        int p2= 1; //pointer 2

        while (listIntersectionPoints.get(p2).getX() != listIntersectionPoints.get(listIntersectionPoints.size() - 1).getX() ||
                listIntersectionPoints.get(p2).getY() != listIntersectionPoints.get(listIntersectionPoints.size() - 1).getY()) {
            if (listIntersectionPoints.get(p1).getX()== listIntersectionPoints.get(p2).getX()){
                p2++;
            }
            if (listIntersectionPoints.get(p1+1).getX()== listIntersectionPoints.get(p2).getX()){
                p1++;
                p2++;
            }
            else{
                Coordonnee debut = listIntersectionPoints.get(p1); // Top left
                Coordonnee endRectangleX = listIntersectionPoints.get(p2); // Top right
                Coordonnee maxRectangle = listIntersectionPoints.get(p2 + 1); // Bottom right
                Coordonnee endRectangleY = listIntersectionPoints.get(p1 + 1);// Bottom left
                rectangles.add(new ArrayList<Coordonnee>(Arrays.asList(debut, endRectangleX, endRectangleY, maxRectangle)));
                p1++;
                p2++;
            }
        }
        return rectangles;
    }

    protected static void removeRectanglesInAccessories(List<ArrayList<Coordonnee>> rectangles, Mur mur) {
        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = 0; j < mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue()).size(); j++) {
                List<Coordonnee> accessoryPoints = mur.getAccessoires().get(j).getSommetsByVue(mur.getCote().toVue());
                if (rectangles.get(i).get(0).getX().equals( accessoryPoints.get(0).getX()) && // rectangles coordonnees est egal a accessoire coordonnees
                        rectangles.get(i).get(1).getX().equals( accessoryPoints.get(1).getX()) &&
                                rectangles.get(i).get(2).getY().greaterOrEquals( accessoryPoints.get(2).getY()) &&
                                        rectangles.get(i).get(0).getY().lessOrEquals(accessoryPoints.get(0).getY())) {
                    rectangles.remove(i);
                }
            }
        }
    }
    protected List<ArrayList<Coordonnee>> prepareRectangleForStl(Mur mur){
           ArrayList<Coordonnee> intersectionPoints = createAccessoryIntersectionPoints(mur);
           List <ArrayList<Coordonnee>> rectangles = createRectangles(intersectionPoints);
           removeRectanglesInAccessories(rectangles,mur);
        return rectangles;
    }

    @Override
    protected void writeStlForF(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau F");
    }

    @Override
    protected void writeStlForA(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau A");
    }

    @Override
    protected void writeStlForG(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau G");
    }

    @Override
    protected void writeStlForD(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau D");
    }

    @Override
    protected void writeStlForT(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau T");
    }

    @Override
    protected void writeStlForR(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau R");
    }

    @Override
    protected void writeStlForPG(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau PG");
    }

    @Override
    protected void writeStlForPD(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau PD");
    }
}
