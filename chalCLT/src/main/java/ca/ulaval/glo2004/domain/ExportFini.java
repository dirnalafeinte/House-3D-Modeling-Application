package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExportFini extends Export {

    public ExportFini(Chalet chalet, String path) {
        super(chalet, path);
    }

    @Override
    public void export() {
        try {
            for(Panneau panneau : Panneau.values()) {
                String fileName = getFileName("Fini", panneau);

                writeFile(fileName, panneau);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    protected ArrayList<Coordonnee> createAccessoryIntersectionPoints(mur){

        Deuxieme Algo (plus simple):
        1. Faire une liste de tous les points d'intersection entre les accessoires
        2. Faire une liste de tous les points d'intersection entre les accessoires et les murs
        3. Faire une liste de tous les points d'intersection entre les accessoires et les murs et les points d'intersection entre les accessoires
        4. Faire une liste de tous les rectangles possibles en enlevant les rectangles qui sont dans des accessoires


        listHorizontalLines = new ArrayList<>();
        listVerticalLines = new ArrayList<>();
        listIntersectionPoints = new ArrayList<Coordonnee>();
        listHorizontalLines.add(0);
        listHorizontalLines.add(hauteurMur);
        listVerticalLines.add(0);
        listVerticalLines.add(largeurMur);
        for accessoire in listAccessoireDuMur{
            if(!listHorizontalLines.contains(accessoires.getCoordonnee().getY())){
                listHorizontalLines.add(accessoires.getCoordonnee().getY());
            }
            if(!listVerticalLines.contains(accessoires.getCoordonnee().getX())){
                listVerticalLines.add(accessoires.getCoordonnee().getX());
            }
        }

        for verticalLine in listVerticalLines{
            for horizontalLine in listHorizontalLines{
                listIntersectionPoints.add(new Coordonnee(verticalLine, horizontalLine));
            }
        }


        listListPointsOfAccessoire = new ArrayList<ArrayList<Coordonnee>>();
        for accessoire in listAccessoireDuMur{
            listPointsOfAccessoire = new ArrayList<Coordonnee>();
            listPointsOfAccessoire.add(accessoire.getCoordonnee().getX(), accessoire.getCoordonnee().getY());
            listPointsOfAccessoire.add(new Coordonnee(accessoire.getCoordonnee().getX() + accessoire.getLargeur(),
                                        accessoire.getCoordonnee().getY()));
            listPointsOfAccessoire.add(new Coordonnee(accessoire.getCoordonnee().getX(),
                                        accessoire.getCoordonnee().getY() + accessoire.getHauteur()));
            listPointsOfAccessoire.add(new Coordonnee(accessoire.getCoordonnee().getX() + accessoire.getLargeur(),
                                        accessoire.getCoordonnee().getY() + accessoire.getHauteur()));
            listListPointsOfAccessoire.add(listPointsOfAccessoire);
        }

        Collection.sort(listIntersectionPoints, Comparator.comparingInt(o -> o.getX()));
        return listIntersectionPoints;
    }

    protected static List<ArrayList<Coordonnee>> createRectangles(ArrayList<Coordonnee> listIntersectionPoints) {
        List<ArrayList<Coordonnee>> rectangles = new ArrayList<>();

        p1= 0;
        p2= 1;
        while (listIntersectionPoints[p2].getX() != listIntersectionPoints[listIntersectionPoints.size()-1].getX() and
                listIntersectionPoints[p2].getY() != listIntersectionPoints[listIntersectionPoints.size()-1].getY()) {
            if (listIntersectionsPoints[p1].getX()== listIntersectionsPoints[p2].getX()){
                p2++;
            }
            else{
                for accessoire in listListPointsOfAccessoire:
                    if (!(accessoire[0].getX() =<(listIntersectionsPoints[p1].getX() and listIntersectionsPoints[p1+1].getX()
                        and listIntersectionsPoints[p2].getX() and listIntersectionsPoints[p2+1].getX())=< accessoire[3].getX()
                        and accessoire[0].getY() =<(listIntersectionsPoints[p1].getY() and listIntersectionsPoints[p1+1].getY()
                        and listIntersectionsPoints[p2].getY() and listIntersectionsPoints[p2+1].getY()) =< accessoire[3].getY())){
                        debut = listIntersectionsPoints[p1];
                        endRectangleX = listIntersectionsPoints[p2];
                        endRectangleY = listIntersectionsPoints[p1+1];
                        maxRectangle = listIntersectionsPoints[p2+1];
                        rectangles.add(new ArrayList<Coordonnee>(debut, endRectangleX, endRectangleY, maxRectangle));
                        p1++;
                        p2++;
                    }
            }
        }
        return rectangles;
    }
    protected static List<ArrayList<Coordonnee>> prepareRectangleForStl(mur){
           intersectionPoints=createAccessoryIntersectionPoints(mur);
           return createRectangles(intersectionPoints);
    }
        */

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
