package ca.ulaval.glo2004.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

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

    public void stlAccessoryOrganizer(){
        /*
        Deuxieme Algo (plus simple):

        Tout d'abord, des listes sont initialisées pour stocker les lignes horizontales, verticales et
        les points d'intersection potentiels. Ensuite, il détermine les lignes manquantes pour créer des intersections,
        générant ainsi une liste de points d'intersection. Chaque accessoire est représenté par des points délimitant
        ses coins, qui sont regroupés dans une liste. Les points d'intersection à l'intérieur des accessoires sont
        supprimés. Les points d'intersection restants sont triés par coordonnée x. Enfin, une fonction crée des
        rectangles à partir des points d'intersection triés. Toutefois, il est mentionné qu'une fonctionnalité pour
        éliminer les rectangles redondants, ceux déjà représentés par les accessoires, manque dans ce code.

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
            listPointsOfAccessoire.add(new Coordonnee(accessoire.getCoordonnee().getX() + accessoire.getLargeur(), accessoire.getCoordonnee().getY()));
            listPointsOfAccessoire.add(new Coordonnee(accessoire.getCoordonnee().getX(), accessoire.getCoordonnee().getY() + accessoire.getHauteur()));
            listPointsOfAccessoire.add(new Coordonnee(accessoire.getCoordonnee().getX() + accessoire.getLargeur(), accessoire.getCoordonnee().getY() + accessoire.getHauteur()));
            listListPointsOfAccessoire.add(listPointsOfAccessoire);
        }
        for listPointsOfAccessoire in listListPointsOfAccessoire{
            for point in listPointsOfAccessoire{
                if(point.getX() == listPointsOfAccessoire[0].getX() or point.getX() == listPointsOfAccessoire[1].getX()){
                    if(point.getY() > listPointsOfAccessoire[0].getY() and point.getY() < listPointsOfAccessoire[2].getY()){
                        listIntersectionPoints.remove(point);
                    }
                }
                else if (point.getY() == listPointsOfAccessoire[0].getY() or point.getY() == listPointsOfAccessoire[2].getY()){
                    if(point.getX() > listPointsOfAccessoire[0].getX() and point.getX() < listPointsOfAccessoire[1].getX()){
                        listIntersectionPoints.remove(point);
                    }
                }
            }
        }
        Collection.sort(listIntersectionPoints, Comparator.comparingInt(o -> o.getX()));
        List<int[]> rectangles=createRectangles(listIntersectionPoints);
        // TODO: manque la fonction pour enlever les rectangles qui sont equivalent aux accessoires (fonction de comparaison) pour les enlever de la liste de rectangles



    public static List<int[]> createRectangles(ArrayList<Coordonnee> listIntersectionPoints) {
        List<int[]> rectangles = new ArrayList<>();

        int size = listIntersectionPoints.size();

        for (int i = 0; i < size - 3; i++) {
            // Check if the coordinates do not match the excluded points
            if (!(listIntersectionPoints.get(i).getX() == 1 && listIntersectionPoints.get(i).getY() == 0 &&
                  listIntersectionPoints.get(i + 1).getX() == 1 && listIntersectionPoints.get(i + 1).getY() == 1 &&
                  listIntersectionPoints.get(i + 3).getX() == 2 && listIntersectionPoints.get(i + 3).getY() == 1)) {

                int x = listIntersectionPoints.get(i).getX();
                int y = listIntersectionPoints.get(i).getY();
                int width = listIntersectionPoints.get(i + 3).getX() - x;
                int height = listIntersectionPoints.get(i + 3).getY() - y;

                int[] rect = new int[]{x, y, width, height};
                rectangles.add(rect);
            }
        }

        return rectangles;
    }





        */
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
