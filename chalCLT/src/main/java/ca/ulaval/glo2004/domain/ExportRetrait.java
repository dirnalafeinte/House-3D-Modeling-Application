package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ExportRetrait extends Export {

    public ExportRetrait(Chalet chalet, String path) {
        super(chalet, path);
    }

    @Override
    public void export() {
        try {
            for (Panneau panneau : Panneau.values()) {
                writeFile(panneau);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(Panneau panneau) throws IOException {
        switch (panneau) {
            case F:
                writeStlForF();
                break;
            case A:
                writeStlForA();
                break;
            case G:
                writeStlForG();
                break;
            case D:
                writeStlForD();
                break;
//            case T:
//                writeStlForT();
//                break;
//            case R:
//                writeStlForR();
//                break;
//            case PG:
//                writeStlForPG();
//                break;
//            case PD:
//                writeStlForPD();
//                break;
        }
    }


    protected void writeStlForF() throws IOException {
        int sequentialNumber = 1;
        Mur mur = chalet.getMurByOrientation(Orientation.FACADE);
        String nom_fichier = "ChalCLT_Retrait_F";

        // rainure 1
        File file_rainure1 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure1 = new FileWriter(file_rainure1)) {
            writeStlRainure1(writer_rainure1, mur);
            sequentialNumber++;
        }

        //rainure 2
        File file_rainure2 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure2 = new FileWriter(file_rainure2);) {
            writeStlRainure2(writer_rainure2, mur);
            sequentialNumber++;
        }

        for (int i = 0; i < mur.getAccessoires().size(); i++) {
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            File file = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
            try (FileWriter writer = new FileWriter(file)) {
                writeStlAccessoire(writer, accessoryPoints);
                sequentialNumber++;
            }
        }
    }

    protected void writeStlAccessoire(FileWriter writer, List<Coordonnee> accessoryPoints) throws IOException {
        writer.write("solid Panneau Accessoire\n");

        writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, normalAvant);
        writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
        writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalBas);
        writeStlForCote(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForCote(writer, accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalDroite);

        writer.write("endsolid Panneau Accessoire\n");
    }

    protected void writeStlRainure1(FileWriter writer, Mur mur) throws IOException {
        writer.write("solid Panneau Rainure1\n");
        double premiereEpaisseurDuMurRainure = mur.getSmallEpaisseur().toInches();
        double distanceXRainure = mur.getBigEpaisseur().toInches();
        double distanceXRainure2 = chalet.getLongueur().toInches() - distanceXRainure;
        writeStlForFace(writer, 0, distanceXRainure, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, normalAvant);
        writeStlForFace(writer, 0, distanceXRainure, 0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForCote(writer, distanceXRainure, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, 0, distanceXRainure, 0, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, 0, distanceXRainure, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        writer.write("endsolid Panneau Rainure1\n");
    }

    protected void writeStlRainure2(FileWriter writer, Mur mur) throws IOException {
        writer.write("solid Panneau Rainure2\n");

        double premiereEpaisseurDuMurRainure = mur.getSmallEpaisseur().toInches();
        double distanceXRainure = mur.getBigEpaisseur().toInches();
        double distanceXRainure2 = chalet.getLongueur().toInches() - distanceXRainure;
        writeStlForFace(writer, distanceXRainure2, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, normalAvant);
        writeStlForFace(writer, distanceXRainure2, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
        writeStlForCote(writer, distanceXRainure2, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForCote(writer, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, distanceXRainure2, chalet.getLongueur().toInches(), 0, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, distanceXRainure2, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);

        writer.write("endsolid Panneau Rainure2\n");
    }

    protected void writeStlForA() throws IOException {
        int sequentialNumber = 1;
        Mur mur = chalet.getMurByOrientation(Orientation.ARRIERE);
        String nom_fichier = "ChalCLT_Retrait_A";

        // rainure 1
        File file_rainure1 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure1 = new FileWriter(file_rainure1)) {
            writeStlRainure1(writer_rainure1, mur);
            sequentialNumber++;
        }

        //rainure 2
        File file_rainure2 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure2 = new FileWriter(file_rainure2);) {
            writeStlRainure2(writer_rainure2, mur);
            sequentialNumber++;
        }

        for (int i = 0; i < mur.getAccessoires().size(); i++) {
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            File file = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
            try (FileWriter writer = new FileWriter(file)) {
                writeStlAccessoire(writer, accessoryPoints);
                sequentialNumber++;
            }
        }
    }

    protected void writeStlForG() throws IOException {
        int sequentialNumber = 1;
        Mur mur = chalet.getMurByOrientation(Orientation.GAUCHE);
        String nom_fichier = "ChalCLT_Retrait_G";

        // rainure 1
        File file_rainure1 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure1 = new FileWriter(file_rainure1)) {
            writeStlRainureL1(writer_rainure1, mur);
            sequentialNumber++;
        }

        //rainure 2
        File file_rainure2 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure2 = new FileWriter(file_rainure2);) {
            writeStlRainureL2(writer_rainure2, mur);
            sequentialNumber++;
        }

        for (int i = 0; i < mur.getAccessoires().size(); i++) {
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            File file = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
            try (FileWriter writer = new FileWriter(file)) {
                writeStlAccessoire(writer, accessoryPoints);
                sequentialNumber++;
            }
        }
    }

    private void writeStlRainureL1(FileWriter writer, Mur mur) throws IOException {
        writer.write("solid Panneau Rainure1\n");
        double premiereEpaisseurDuMurRainure = mur.getSmallEpaisseur().toInches();
        double distanceXRainure= mur.getBigEpaisseur().toInches() * 2;
        double distanceXRainure2= chalet.getLargeur().toInches()-distanceXRainure;
        //rainure 1
        writeStlForFace(writer, 0, distanceXRainure,0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, normalAvant);
        writeStlForFace(writer, 0, distanceXRainure,0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForCote(writer, distanceXRainure, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, 0, distanceXRainure, 0, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, 0, distanceXRainure, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        // rainure 1, deuxieme prisme pour le L
        writeStlForFace(writer, 0, mur.getBigEpaisseur().toInches(),0, chalet.getHauteur().toInches(), 0, normalAvant);
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalGauche);
        writeStlForCote(writer, mur.getBigEpaisseur().toInches(), 0, chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalGauche);
        writeStlForUpAndDown(writer, 0, mur.getBigEpaisseur().toInches(), 0, 0, premiereEpaisseurDuMurRainure, normalHaut);
        writeStlForUpAndDown(writer, 0, mur.getBigEpaisseur().toInches(), chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalHaut);
        writer.write("endsolid Panneau Rainure1\n");
    }

    private void writeStlRainureL2(FileWriter writer, Mur mur) throws IOException {
        writer.write("solid Panneau Rainure2\n");
        double premiereEpaisseurDuMurRainure = mur.getSmallEpaisseur().toInches();
        double distanceXRainure= mur.getBigEpaisseur().toInches() * 2;
        double distanceXRainure2= chalet.getLargeur().toInches()-distanceXRainure;
        //rainure 2
        writeStlForFace(writer, distanceXRainure2, chalet.getLargeur().toInches(),0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, normalAvant);
        writeStlForFace(writer, distanceXRainure2, chalet.getLargeur().toInches(),0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalAvant);
        writeStlForCote(writer, distanceXRainure2, 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForCote(writer, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalGauche);
        writeStlForUpAndDown(writer, distanceXRainure2, chalet.getLargeur().toInches(), 0, premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, distanceXRainure2, chalet.getLargeur().toInches(), chalet.getHauteur().toInches(), premiereEpaisseurDuMurRainure, chalet.getEpaisseurMur().toInches(), normalHaut);
        // rainure 2, deuxieme prisme pour le L
        writeStlForFace(writer, distanceXRainure2, distanceXRainure2 + mur.getBigEpaisseur().toInches(),0, chalet.getHauteur().toInches(), 0, normalAvant);
        writeStlForCote(writer, distanceXRainure2, 0, chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalGauche);
        writeStlForCote(writer, distanceXRainure2 + mur.getBigEpaisseur().toInches(), 0, chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalGauche);
        writeStlForUpAndDown(writer, distanceXRainure2, distanceXRainure2 + mur.getBigEpaisseur().toInches(), 0, 0, premiereEpaisseurDuMurRainure, normalHaut);
        writeStlForUpAndDown(writer, distanceXRainure2, distanceXRainure2 + mur.getBigEpaisseur().toInches(), chalet.getHauteur().toInches(), 0, premiereEpaisseurDuMurRainure, normalHaut);

        writer.write("endsolid Panneau Rainure2\n");
    }

    protected void writeStlForD() throws IOException {
        int sequentialNumber = 1;
        Mur mur = chalet.getMurByOrientation(Orientation.DROITE);
        String nom_fichier = "ChalCLT_Retrait_D";

        // rainure 1
        File file_rainure1 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure1 = new FileWriter(file_rainure1)) {
            writeStlRainureL1(writer_rainure1, mur);
            sequentialNumber++;
        }

        //rainure 2
        File file_rainure2 = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
        try (FileWriter writer_rainure2 = new FileWriter(file_rainure2);) {
            writeStlRainureL2(writer_rainure2, mur);
            sequentialNumber++;
        }

        for (int i = 0; i < mur.getAccessoires().size(); i++) {
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            File file = new File(path, String.format("%s_%d.stl", nom_fichier, sequentialNumber));
            try (FileWriter writer = new FileWriter(file)) {
                writeStlAccessoire(writer, accessoryPoints);
                sequentialNumber++;
            }
        }
    }

//    protected void writeStlForT() throws IOException {
//        writer.write("STL data for Panneau T");
//    }
//
//
//    protected void writeStlForR() throws IOException {
//        writer.write("STL data for Panneau R");
//    }
//
//
//    protected void writeStlForPG() throws IOException {
//        writer.write("STL data for Panneau PG");
//    }
//
//
//    protected void writeStlForPD() throws IOException {
//        writer.write("STL data for Panneau PD");
//    }
}