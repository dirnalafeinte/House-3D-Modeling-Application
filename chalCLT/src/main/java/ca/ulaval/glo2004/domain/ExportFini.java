package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;
import ca.ulaval.glo2004.domain.util.Imperial;

import java.io.File;
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
            for(Panneau panneau : Panneau.values()) {
                String fileName = getFileName("Fini", panneau);

                writeFile(fileName, panneau);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeFile(String fileName, Panneau panneau) throws IOException {
        File file = new File(path, fileName);
        try (FileWriter writer = new FileWriter(file)) {
            switch (panneau) {
                case F:
                    writeStlForF(writer);
                    break;
                case A:
                    writeStlForA(writer);
                    break;
                case G:
                    writeStlForG(writer);
                    break;
                case D:
                    writeStlForD(writer);
                    break;
                case PD:
                    writeStlForPD(writer);
                    break;
                case PG:
                    writeStlForPG(writer);
                    break;
                case R:
                    writeStlForR(writer);
                    break;
                case T:
                    writeStlForT(writer);
                    break;
            }
        }
    }

    protected ArrayList<Coordonnee> createAccessoryIntersectionPoints(Mur mur){
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
        ArrayList<Imperial>  listHorizontalLines = new ArrayList<>();
        ArrayList<Imperial>  listVerticalLines = new ArrayList<>();
        ArrayList<Coordonnee> listIntersectionPoints = new ArrayList<>();

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
        for (Imperial listHorizontalLine : listHorizontalLines) {
            for (Imperial listVerticalLine : listVerticalLines) {
                listIntersectionPoints.add(new Coordonnee(listHorizontalLine, listVerticalLine));
            }
        }

        // Tri des points d'intersection par le X
        listIntersectionPoints.sort(Comparator.comparingDouble(o -> o.getY().toInches()));
        listIntersectionPoints.sort(Comparator.comparingDouble(o -> o.getX().toInches()));
        return listIntersectionPoints;
    }

    protected List<ArrayList<Coordonnee>> createRectangles(ArrayList<Coordonnee> listIntersectionPoints) {

        List<ArrayList<Coordonnee>> rectangles = new ArrayList<>();
        int p1= 0; //pointer 1
        int p2= 1; //pointer 2

        while (listIntersectionPoints.get(p2).getX() != listIntersectionPoints.get(listIntersectionPoints.size() - 1).getX() ||
                listIntersectionPoints.get(p2).getY() != listIntersectionPoints.get(listIntersectionPoints.size() - 1).getY()) {
            if (listIntersectionPoints.get(p1).getX()== listIntersectionPoints.get(p2).getX()){
                p2++;
            }
            else if (listIntersectionPoints.get(p1+1).getX()== listIntersectionPoints.get(p2).getX() && listIntersectionPoints.size()!=4){
                p1++;
                p2++;
            }
            else{
                Coordonnee debut = listIntersectionPoints.get(p1); // Top left
                Coordonnee endRectangleX = listIntersectionPoints.get(p2); // Top right
                Coordonnee maxRectangle = listIntersectionPoints.get(p2 + 1); // Bottom right
                Coordonnee endRectangleY = listIntersectionPoints.get(p1 + 1);// Bottom left
                rectangles.add(new ArrayList<>(Arrays.asList(debut, endRectangleX, endRectangleY, maxRectangle)));
                p1++;
                p2++;
            }
        }
        return rectangles;
    }

    protected void removeRectanglesInAccessories(List<ArrayList<Coordonnee>> rectangles, Mur mur) {
        if (mur.getAccessoires().isEmpty()) {
            return;
        }
        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = 0; j < mur.getAccessoires().size(); j++) {
                List<Coordonnee> accessoryPoints = mur.getAccessoires().get(j).getSommetsByVue(mur.getCote().toVue());
                boolean condition1=rectangles.get(i).get(0).getX().greaterOrEquals( accessoryPoints.get(0).getX());
                boolean condition2=rectangles.get(i).get(1).getX().lessOrEquals(accessoryPoints.get(1).getX());
                boolean condition3=rectangles.get(i).get(2).getY().lessOrEquals(accessoryPoints.get(2).getY());
                boolean condition4=rectangles.get(i).get(0).getY().greaterOrEquals(accessoryPoints.get(0).getY());
                if (condition1 && condition2 && condition3 && condition4) {
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


    protected void writeStlForF(FileWriter writer) throws IOException {
        Mur mur = chalet.getMurByOrientation(Orientation.FACADE);
        List<ArrayList<Coordonnee>> rectangles=prepareRectangleForStl(mur);
        writer.write("solid Panneau F\n");
        double premiereEpaisseurDuMurRainure= mur.getSmallEpaisseur().toInches();
        for (ArrayList<Coordonnee> rectangle : rectangles) {
                writeStlForFace(writer, rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), 0, normalAvant);
        }

        for (ArrayList<Coordonnee> rectangle : rectangles) {
            writeStlForFace(writer, rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), premiereEpaisseurDuMurRainure, normalArriere);
        }

        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalAvant);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalArriere);
            writeStlForCote(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalGauche);
            writeStlForCote(writer, accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalDroite);
        }

        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalGauche);
        writeStlForCote(writer, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalDroite);
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), 0, 0, premiereEpaisseurDuMurRainure, normalHaut);
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalBas);

        //Rainure
        double distanceXRainure= mur.getBigEpaisseur().toInches();
        double distanceXRainure2= chalet.getLongueur().toInches()-distanceXRainure;
        for (ArrayList<Coordonnee> rectangle : rectangles) {


            if (rectangle.get(0).getX().toInches() < distanceXRainure) {
                if (rectangle.get(1).getX().toInches() > (distanceXRainure2)){
                    writeStlForFace(writer, distanceXRainure,distanceXRainure2, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
                }
                else{
                    writeStlForFace(writer, distanceXRainure,rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
                }
            }
            else if (rectangle.get(1).getX().toInches() > (chalet.getLongueur().toInches() - chalet.getEpaisseurMur().toInches())) {
                writeStlForFace(writer, rectangle.get(0).getX().toInches(),distanceXRainure2, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
            }
            else {
                writeStlForFace(writer, rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
            }
        }
        writeStlForCote(writer, distanceXRainure, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForCote(writer, distanceXRainure2, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, distanceXRainure, distanceXRainure2, 0, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, distanceXRainure, distanceXRainure2, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);

        writer.write("endsolid Panneau F\n");
    }


    protected void writeStlForA(FileWriter writer) throws IOException {
        Mur mur = chalet.getMurByOrientation(Orientation.ARRIERE);
        List<ArrayList<Coordonnee>> rectangles=prepareRectangleForStl(mur);
        writer.write("solid Panneau A\n");
        double premiereEpaisseurDuMurRainure= mur.getSmallEpaisseur().toInches();
        double deuxiemeEpaisseurDuMurRainure= mur.getBigEpaisseur().toInches();
        for (ArrayList<Coordonnee> rectangle : rectangles) {
            writeStlForFace(writer, rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getLargeur().toInches(), normalAvant);
        }

        for (ArrayList<Coordonnee> rectangle : rectangles) {
            writeStlForFace(writer, rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure, normalArriere);
        }

        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), chalet.getLargeur().toInches(), chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalAvant);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getLargeur().toInches(), chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalArriere);
            writeStlForCote(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getLargeur().toInches(), chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalGauche);
            writeStlForCote(writer, accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getLargeur().toInches(), chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalDroite);
        }

        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure, chalet.getLargeur().toInches(), normalGauche);
        writeStlForCote(writer, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure, chalet.getLargeur().toInches(), normalDroite);
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), 0, chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure, chalet.getLargeur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure, chalet.getLargeur().toInches(), normalBas);

        //Rainure
        double distanceXRainure= mur.getBigEpaisseur().toInches();
        double distanceXRainure2= chalet.getLongueur().toInches()-distanceXRainure;
        for (ArrayList<Coordonnee> rectangle : rectangles) {


            if (rectangle.get(0).getX().toInches() < distanceXRainure) {
                if (rectangle.get(1).getX().toInches() > (distanceXRainure2)){
                    writeStlForFace(writer, distanceXRainure,distanceXRainure2, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalAvant);
                }
                else{
                    writeStlForFace(writer, distanceXRainure,rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(),chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalAvant);
                }
            }
            else if (rectangle.get(1).getX().toInches() > (chalet.getLongueur().toInches() - chalet.getEpaisseurMur().toInches())) {
                writeStlForFace(writer, rectangle.get(0).getX().toInches(),distanceXRainure2, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalAvant);
            }
            else {
                writeStlForFace(writer, rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalAvant);
            }
        }
        writeStlForCote(writer, distanceXRainure, 0, chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure,chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches()  , normalGauche);
        writeStlForCote(writer, distanceXRainure2, 0, chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure,chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, distanceXRainure, distanceXRainure2, 0, chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure,chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, distanceXRainure, distanceXRainure2, chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-deuxiemeEpaisseurDuMurRainure,chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), normalHaut);
        writer.write("endsolid Panneau A\n");
    }


    protected void writeStlForG(FileWriter writer) throws IOException {
        Mur mur = chalet.getMurByOrientation(Orientation.GAUCHE);
        List<ArrayList<Coordonnee>> rectangles=prepareRectangleForStl(mur);
        double premiereEpaisseurDuMurRainure = mur.getSmallEpaisseur().toInches();
        writer.write("solid Panneau G\n");
        for (ArrayList<Coordonnee> rectangle : rectangles) {
            writeStlForCote(writer,0, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(),rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), normalAvant);
            writeStlForCote(writer,premiereEpaisseurDuMurRainure, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(),rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), normalAvant);
        }
        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            writeStlForUpAndDown(writer,0 ,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), normalAvant);
            writeStlForUpAndDown(writer,0 ,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(2).getY().toInches(), accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), normalAvant);
            writeStlForFace(writer,0,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(),  accessoryPoints.get(0).getX().toInches(), normalGauche);
            writeStlForFace(writer, 0,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(),  accessoryPoints.get(2).getX().toInches(), normalDroite);
        }

        writeStlForFace(writer,0 , premiereEpaisseurDuMurRainure, 0,chalet.getHauteur().toInches(), mur.getBigEpaisseur().toInches(), normalGauche);
        writeStlForFace(writer,0 , premiereEpaisseurDuMurRainure, 0,chalet.getHauteur().toInches(), chalet.getLargeur().toInches() - mur.getBigEpaisseur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, 0,premiereEpaisseurDuMurRainure,0,mur.getBigEpaisseur().toInches(), chalet.getLargeur().toInches() - mur.getBigEpaisseur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, 0,premiereEpaisseurDuMurRainure,chalet.getHauteur().toInches(),mur.getBigEpaisseur().toInches(), chalet.getLargeur().toInches() - mur.getBigEpaisseur().toInches(),  normalBas);

        //Rainure
        double distanceXRainure= mur.getBigEpaisseur().toInches() * 2;
        double distanceXRainure2= chalet.getLargeur().toInches()-distanceXRainure;
        for (ArrayList<Coordonnee> rectangle : rectangles) {


            if (rectangle.get(0).getX().toInches() < distanceXRainure) {
                if (rectangle.get(1).getX().toInches() > (distanceXRainure2)){ // si les deux parties du rectangle sont superieur a la rainure
                    writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), distanceXRainure,distanceXRainure2, normalAvant);
                }
                else{
                    writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), distanceXRainure,rectangle.get(1).getX().toInches(), normalAvant);
                }
            }
            else if (rectangle.get(1).getX().toInches() > (distanceXRainure2)) {
                writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), rectangle.get(0).getX().toInches(),distanceXRainure2, normalAvant);
            }
            else {
                writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), rectangle.get(0).getX().toInches(),rectangle.get(1).getX().toInches(), normalAvant);
            }
        }
        writeStlForFace(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(),0, chalet.getHauteur().toInches(),distanceXRainure, normalGauche);
        writeStlForFace(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(),0, chalet.getHauteur().toInches(),distanceXRainure2, normalGauche);
        writeStlForUpAndDown(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), 0 ,distanceXRainure, distanceXRainure2, normalHaut);
        writeStlForUpAndDown(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), chalet.getHauteur().toInches() ,distanceXRainure, distanceXRainure2, normalHaut);

        writer.write("endsolid Panneau G\n");
    }


    protected void writeStlForD(FileWriter writer) throws IOException {//TODO: A changer
        Mur mur = chalet.getMurByOrientation(Orientation.GAUCHE);
        List<ArrayList<Coordonnee>> rectangles=prepareRectangleForStl(mur);
        double premiereEpaisseurDuMurRainure = mur.getSmallEpaisseur().toInches();
        writer.write("solid Panneau G\n");
        for (ArrayList<Coordonnee> rectangle : rectangles) {
            writeStlForCote(writer,0, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(),rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), normalAvant);
            writeStlForCote(writer,premiereEpaisseurDuMurRainure, rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(),rectangle.get(0).getX().toInches(), rectangle.get(1).getX().toInches(), normalAvant);
        }
        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            writeStlForUpAndDown(writer,0 ,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), normalAvant);
            writeStlForUpAndDown(writer,0 ,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(2).getY().toInches(), accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), normalAvant);
            writeStlForFace(writer,0,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(),  accessoryPoints.get(0).getX().toInches(), normalGauche);
            writeStlForFace(writer, 0,chalet.getEpaisseurMur().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(),  accessoryPoints.get(2).getX().toInches(), normalDroite);
        }

        writeStlForFace(writer,0 , premiereEpaisseurDuMurRainure, 0,chalet.getHauteur().toInches(), mur.getBigEpaisseur().toInches(), normalGauche);
        writeStlForFace(writer,0 , premiereEpaisseurDuMurRainure, 0,chalet.getHauteur().toInches(), chalet.getLargeur().toInches() - mur.getBigEpaisseur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, 0,premiereEpaisseurDuMurRainure,0,mur.getBigEpaisseur().toInches(), chalet.getLargeur().toInches() - mur.getBigEpaisseur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, 0,premiereEpaisseurDuMurRainure,chalet.getHauteur().toInches(),mur.getBigEpaisseur().toInches(), chalet.getLargeur().toInches() - mur.getBigEpaisseur().toInches(),  normalBas);

        //Rainure
        double distanceXRainure= mur.getBigEpaisseur().toInches() * 2;
        double distanceXRainure2= chalet.getLargeur().toInches()-distanceXRainure;
        for (ArrayList<Coordonnee> rectangle : rectangles) {


            if (rectangle.get(0).getX().toInches() < distanceXRainure) {
                if (rectangle.get(1).getX().toInches() > (distanceXRainure2)){ // si les deux parties du rectangle sont superieur a la rainure
                    writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), distanceXRainure,distanceXRainure2, normalAvant);
                }
                else{
                    writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), distanceXRainure,rectangle.get(1).getX().toInches(), normalAvant);
                }
            }
            else if (rectangle.get(1).getX().toInches() > (distanceXRainure2)) {
                writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), rectangle.get(0).getX().toInches(),distanceXRainure2, normalAvant);
            }
            else {
                writeStlForCote(writer,chalet.getEpaisseurMur().toInches() , rectangle.get(0).getY().toInches(), rectangle.get(2).getY().toInches(), rectangle.get(0).getX().toInches(),rectangle.get(1).getX().toInches(), normalAvant);
            }
        }
        writeStlForFace(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(),0, chalet.getHauteur().toInches(),distanceXRainure, normalGauche);
        writeStlForFace(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(),0, chalet.getHauteur().toInches(),distanceXRainure2, normalGauche);
        writeStlForUpAndDown(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), 0 ,distanceXRainure, distanceXRainure2, normalHaut);
        writeStlForUpAndDown(writer, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), chalet.getHauteur().toInches() ,distanceXRainure, distanceXRainure2, normalHaut);

        writer.write("endsolid Panneau G\n");
    }




    protected void writeStlForT(FileWriter writer) throws IOException {
        Imperial oppose= chalet.rallonge.getHauteurRallonge().subtract(chalet.rallonge.calculerYSommetTriangulation()).negate();

        writer.write("solid Panneau T\n");

        writer.write("facet normal " + normalBas + "\n");//haut face
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + -chalet.toit.getHauteurToit().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.toit.getHauteurToitSecondaire().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + 0 + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");//haut face
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + -chalet.toit.getHauteurToit().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.toit.getHauteurToit().toInches()-getSmallEpaisseur().toInches()) + " " + 0 + "\n"); //point 1
        writer.write("vertex " + 0 + " " + 0 + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");//haut face
        writer.write("outer loop\n");
        writer.write("facet normal " + normalBas + "\n");//haut face
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + 0 + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + 0 + " " + (-chalet.toit.getLongueurToit().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + getSmallEpaisseur().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");//haut face
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + 0 + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + getSmallEpaisseur().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + getSmallEpaisseur().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");//haut face
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.toit.getHauteurToitSecondaire().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + getSmallEpaisseur().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + getSmallEpaisseur().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");


        //writer.write("vertex " + 0 + " " + chalet.toit.getHauteurToitSecondaire().toInches() + " " + (-chalet.toit.getLongueurToit().toInches()-getSmallEpaisseur().toInches()) + "\n");
//
//
//
        //writer.write("vertex " + 0 + " " + 0 + " " + -chalet.toit.getLongueurToit().toInches() + "\n");
        //writer.write("vertex " + 0 + " " + getSmallEpaisseur().toInches() + " " + -chalet.toit.getLongueurToit().toInches() + "\n");
        //writer.write("vertex " + 0 + " " + -chalet.toit.getHauteurToit().toInches() + " " + 0 + "\n");
        //
        //
//
//
//
//
//
        //writer.write("endloop\n");
        //writer.write("endfacet\n");
        //writer.write("facet normal " + normalBas + "\n");
        //writer.write("outer loop\n");
        //writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        //writer.write("vertex " + chalet.getLongueur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        //writer.write("vertex " + chalet.getLongueur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        //writer.write("endloop\n");
        //writer.write("endfacet\n");
//
        //writer.write("facet normal " + normalBas + "\n");//haut rallonge
        //writer.write("outer loop\n");
        //writer.write("vertex " + 0 + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        //writer.write("vertex " + 0 + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        //writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        //writer.write("endloop\n");
        //writer.write("endfacet\n");
        //writer.write("facet normal " + normalBas + "\n");
        //writer.write("outer loop\n");
        //writer.write("vertex " + 0 + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        //writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        //writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        //writer.write("endloop\n");
        //writer.write("endfacet\n");
//
        ////writer.write("facet normal " + normalBas + "\n");//haut rallonge
        ////writer.write("outer loop\n");
        ////writer.write("vertex " + 0 + " " + (-chalet.rallonge.getHauteurRallonge().toInches()+ oppose.toInches()) + " " + chalet.getLargeur().toInches() + "\n");
        ////writer.write("vertex " + 0 + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        ////writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        ////writer.write("endloop\n");
        ////writer.write("endfacet\n");
        //writer.write("facet normal " + normalBas + "\n");
        //writer.write("outer loop\n");
        //writer.write("vertex " + 0 + " " + (-chalet.rallonge.getHauteurRallonge().toInches()+oppose.toInches()) + " " + chalet.getLargeur().toInches() + "\n");
        //writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches()+380+ " " + chalet.getLargeur().toInches() + "\n");
        //writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches()+380 + " " + (getSmallEpaisseur().toInches()) + "\n");
        //writer.write("endloop\n");
        //writer.write("endfacet\n");
//
        //writer.write("endsolid Panneau T\n");
   }
//
//
    protected void writeStlForR(FileWriter writer) throws IOException {
        Imperial oppose= chalet.rallonge.getHauteurRallonge().subtract(chalet.rallonge.calculerYSommetTriangulation()).negate();
        writer.write("STL data for Panneau R");
        writer.write("solid Panneau R\n");
        writeStlForFace(writer, 0, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(),-chalet.rallonge.getHauteurRallonge().toInches(), chalet.getLargeur().toInches(), normalArriere);
        writeStlForFace(writer, 0, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(),-chalet.rallonge.calculerYSommetTriangulation().toInches(), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), normalArriere);
        writeStlForFace(writer, getBigEpaisseur().toInches(), chalet.getLongueur().toInches()-getBigEpaisseur().toInches(), chalet.getHauteur().toInches(),-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches(), chalet.getLargeur().toInches()-getEpaisseur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), chalet.getLargeur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, getBigEpaisseur().toInches(), chalet.getLongueur().toInches()-getBigEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), chalet.getLargeur().toInches()-getEpaisseur().toInches(), normalHaut);

        writer.write("facet normal " + normalDroite + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("vertex " + 0 + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writeStlForCote(writer, 0, chalet.getHauteur().toInches(), -chalet.rallonge.calculerYSommetTriangulation().toInches(), chalet.getLargeur().toInches(), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), normalDroite);

        writer.write("facet normal " + normalGauche + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writeStlForCote(writer, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(), -chalet.rallonge.calculerYSommetTriangulation().toInches(), chalet.getLargeur().toInches(), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), normalGauche);

        writer.write("facet normal " + normalDroite + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + getBigEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (chalet.getLargeur().toInches()-getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getBigEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getBigEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writeStlForCote(writer, getBigEpaisseur().toInches(), chalet.getHauteur().toInches(), -chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches(), (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()), chalet.getLargeur().toInches()-getEpaisseur().toInches(), normalDroite);

        writer.write("facet normal " + normalGauche + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + (chalet.getLongueur().toInches()-getBigEpaisseur().toInches()) + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (chalet.getLargeur().toInches()-getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + (chalet.getLongueur().toInches()-getBigEpaisseur().toInches()) + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + (chalet.getLongueur().toInches()-getBigEpaisseur().toInches()) + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writeStlForCote(writer, chalet.getLongueur().toInches()-getBigEpaisseur().toInches(), chalet.getHauteur().toInches(), -chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches(), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), chalet.getLargeur().toInches()-getEpaisseur().toInches(), normalGauche);

        writer.write("facet normal " + normalBas + "\n");//haut rallonge
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("vertex " + 0 + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");//haut retrait
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("endsolid Panneau R\n");
    }



    protected void writeStlForPG(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau PG");
        writer.write("solid Panneau PG\n");
        Imperial oppose= chalet.rallonge.getHauteurRallonge().subtract(chalet.rallonge.calculerYSommetTriangulation()).negate();

        writer.write("facet normal " + normalBas + "\n");//haut retrait
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writeStlForCote(writer,(getSmallEpaisseur().toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()),chalet.getLargeur().toInches()- getSmallEpaisseur().toInches(),getSmallEpaisseur().toInches(),normalArriere);
        writeStlForCote(writer,(getEpaisseur().toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()),chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(),getSmallEpaisseur().toInches(),normalArriere);
        writeStlForFace(writer, 0, getSmallEpaisseur().toInches(), (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()), chalet.getLargeur().toInches()-getEpaisseur().toInches(), normalArriere);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), getEpaisseur().toInches(), (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.getHauteurRallonge().toInches()), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), normalArriere);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), getEpaisseur().toInches(), (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()), getSmallEpaisseur().toInches(), normalArriere);
        writeStlForUpAndDown(writer,0, getSmallEpaisseur().toInches(),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),chalet.getLargeur().toInches()- getEpaisseur().toInches(),getEpaisseur().toInches(),normalHaut);
        writeStlForUpAndDown(writer,getSmallEpaisseur().toInches(), getEpaisseur().toInches(),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),chalet.getLargeur().toInches()- getSmallEpaisseur().toInches(),getSmallEpaisseur().toInches(),normalHaut);

        writer.write("facet normal " + normalBas + "\n");//haut rallonge
        writer.write("outer loop\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + (-chalet.rallonge.getHauteurRallonge().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");//haut rallonge
        writer.write("outer loop\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.getHauteurRallonge().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()-getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (chalet.getLargeur().toInches()-getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");


        writer.write("facet normal " + normalBas + "\n");//haut rallonge
        writer.write("outer loop\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("endsolid Panneau PG\n");
    }


    protected void writeStlForPD(FileWriter writer) throws IOException {
        writer.write("STL data for Panneau PD");
        writer.write("solid Panneau PD\n");
        Imperial oppose= chalet.rallonge.getHauteurRallonge().subtract(chalet.rallonge.calculerYSommetTriangulation()).negate();

        writer.write("facet normal " + normalBas + "\n");//haut retrait
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()- getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()-oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writeStlForCote(writer,(getSmallEpaisseur().toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()),chalet.getLargeur().toInches()- getSmallEpaisseur().toInches(),getSmallEpaisseur().toInches(),normalArriere);
        writeStlForCote(writer,(getEpaisseur().toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()),chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(),getSmallEpaisseur().toInches(),normalArriere);
        writeStlForFace(writer, 0, getSmallEpaisseur().toInches(), (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()), chalet.getLargeur().toInches()-getEpaisseur().toInches(), normalArriere);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), getEpaisseur().toInches(), (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.getHauteurRallonge().toInches()), chalet.getLargeur().toInches()-getSmallEpaisseur().toInches(), normalArriere);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), getEpaisseur().toInches(), (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),(-chalet.rallonge.calculerYSommetTriangulation().toInches()), getSmallEpaisseur().toInches(), normalArriere);
        writeStlForUpAndDown(writer,0, getSmallEpaisseur().toInches(),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),chalet.getLargeur().toInches()- getEpaisseur().toInches(),getEpaisseur().toInches(),normalHaut);
        writeStlForUpAndDown(writer,getSmallEpaisseur().toInches(), getEpaisseur().toInches(),(-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()),chalet.getLargeur().toInches()- getSmallEpaisseur().toInches(),getSmallEpaisseur().toInches(),normalHaut);

        writer.write("facet normal " + normalBas + "\n");//haut rallonge
        writer.write("outer loop\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + (-chalet.rallonge.getHauteurRallonge().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");//haut rallonge
        writer.write("outer loop\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.getHauteurRallonge().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (chalet.getLargeur().toInches()- getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()) + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");

        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()) + " " + (chalet.getLargeur().toInches()-getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (chalet.getLargeur().toInches()-getEpaisseur().toInches()) + "\n");
        writer.write("vertex " + 0 + " " + (-chalet.rallonge.calculerYSommetTriangulation().toInches()-getBigEpaisseur().toInches()- oppose.toInches()) + " " + (getEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");


        writer.write("facet normal " + normalBas + "\n");//haut rallonge
        writer.write("outer loop\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + getSmallEpaisseur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + -chalet.rallonge.getHauteurRallonge().toInches() + " " + (chalet.getLargeur().toInches()-getSmallEpaisseur().toInches()) + "\n");
        writer.write("vertex " + getEpaisseur().toInches() + " " + -chalet.rallonge.calculerYSommetTriangulation().toInches() + " " + (getSmallEpaisseur().toInches()) + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("endsolid Panneau PG\n");
    }
}
