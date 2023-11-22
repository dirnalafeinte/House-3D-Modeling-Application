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
        Explication de l'algorithme:
        Premièrement, on trie les accessoires du mur par position x.
        On commence de la gauche du mur. On crée un premier rectangle qui va de 0 à la position x du premier accessoire du mur.
        Puis, on prend la hauteur du mur pour créer le premier rectangle sans accessoire.
        Par la suite, on prend la position x du premier accessoire du mur et on la met dans une variable debutRectangleX.
        On prend la position x du premier accessoire du mur + la largeur de l'accessoire et on la met dans une variable endRectangleX.
        On prend la position y de la fenetre et on la met dans une variable debutRectangleY.
        On prend la hauteur de la fenetre + la position y et on la met dans une variable endRectangleY.
        Avec ca, on crée un rectangle de dimensions debutRectangleX, endRectangleX, 0, debutRectangleY
               & un autre rectangle de dimensions debutRectangleX, endRectangleX, endRectangleY, hauteurMur.
        Si la position x du prochain accessoire est plus petite que endRectangleX, on met endRectangleX à la position x du prochain accessoire.
        Puis, on crée les rectangles.
        Lors du prochain processus endRectangleX sera la position finale du rectangle précédent et on prendra en compte les positions y des deux accessoires
                dans la formation de ces rectangles.
        Par après, debutRectangleX = endRectangleX et on met la position x + la largeur de l'accessoire dans endRectangleX.

        Collections.sort(listAccessoireDuMur, Comparator.comparingInt(o -> o.getCoordonnee().getX()));
        int debutRectangleX = 0;
        int endRectangleX = listAccessoireDuMur.get(0).getCoordonnee().getX();
        int debutRectangleY = 0;
        int endRectangleY = hauteurMur;
        writeStlForRectangle(writer, debutRectangleX, endRectangleX, debutRectangleY, endRectangleY);
        for(int i = 0; i < listAccessoireDuMur.size(); i++){
            writeStlForRectangle(writer, debutRectangleX, endRectangleX, debutRectangleY, endRectangleY);
            if(listAccessoireDuMur[i-1].getCoordonnee().getX()+ listAccessoireDuMur[i-1].getLargeur() < listAccessoireDuMur[i].getCoordonnee().getX();){
                debutRectangleX = listAccessoireDuMur.get(i).getCoordonnee().getX();
            }
            else{
                debutRectangleX = listAccessoireDuMur[i-1].getCoordonnee().getX() + listAccessoireDuMur.get(i-1).getLargeur();
            }
            if (listAccessoireDuMur[i].getCoordonnee().getX() + listAccessoireDuMur.get(i).getLargeur() > listAccessoireDuMur[i+1].getCoordonnee().getX()){
                endRectangleX = listAccessoireDuMur[i+1].getCoordonnee().getX();
            }
            else{
                endRectangleX = listAccessoireDuMur[i].getCoordonnee().getX() + listAccessoireDuMur.get(i).getLargeur();
            }
            debutRectangleY = listAccessoireDuMur[i].getCoordonnee().getY();
            endRectangleY = listAccessoireDuMur[i].getCoordonnee().getY() + listAccessoireDuMur.get(i).getHauteur();

        }
        writeStlForRectangle(writer, debutRectangleX, endRectangleX, debutRectangleY, endRectangleY);

        Deuxieme Algo:
        On crée des lignes horizontales et verticales pour chaque accessoire du mur selon leur position x et y.
        On crée des rectangles avec les lignes horizontales et verticales.
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
        Collections.sort(listIntersectionPoints, Comparator.comparingInt(o -> o.getX()));
        Collections.sort(listIntersectionPoints, Comparator.comparingInt(o -> o.getY()));




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
