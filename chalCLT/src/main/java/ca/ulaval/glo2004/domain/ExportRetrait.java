package ca.ulaval.glo2004.domain;

import ca.ulaval.glo2004.domain.util.Coordonnee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportRetrait extends Export {
    private int sequentialNumber;

    public ExportRetrait(Chalet chalet, String path) {
        super(chalet, path);
        sequentialNumber = 1;
    }

    @Override
    public void export() {
        try {
            for (Panneau panneau : Panneau.values()) {
                writeFile(panneau);
                sequentialNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(Panneau panneau) throws IOException {
        String fileName = getFileName("Retrait", panneau, sequentialNumber);

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
                case T:
                    writeStlForT(writer);
                    break;
                case R:
                    writeStlForR(writer);
                    break;
                case PG:
                    writeStlForPG(writer);
                    break;
                case PD:
                    writeStlForPD(writer);
                    break;
            }
        }
    }

    @Override
    protected void writeStlForF(FileWriter writer) throws IOException {
        Mur mur = chalet.getMurByOrientation(Orientation.FACADE);
        writer.write("solid Panneau F\n");
        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, normalAvant);
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalHaut);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalBas);
            writeStlForCote(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalGauche);
            writeStlForCote(writer, accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalDroite);
        }
        writer.write("endsolid Panneau F\n");

    }

    @Override
    protected void writeStlForA(FileWriter writer) throws IOException {
        Mur mur = chalet.getMurByOrientation(Orientation.ARRIERE);
        writer.write("solid Panneau F\n");
        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, normalAvant);
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalHaut);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalBas);
            writeStlForCote(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalGauche);
            writeStlForCote(writer, accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalDroite);
        }
        writer.write("endsolid Panneau F\n");
    }

    @Override
    protected void writeStlForG(FileWriter writer) throws IOException {
        Mur mur = chalet.getMurByOrientation(Orientation.GAUCHE);
        writer.write("solid Panneau F\n");
        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(chalet.getMurByOrientation(Orientation.FACADE).getCote().toVue());
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, normalAvant);
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalHaut);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalBas);
            writeStlForCote(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalGauche);
            writeStlForCote(writer, accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalDroite);
        }
        writer.write("endsolid Panneau F\n");
    }

    @Override
    protected void writeStlForD(FileWriter writer) throws IOException {
        Mur mur = chalet.getMurByOrientation(Orientation.DROITE);
        writer.write("solid Panneau F\n");
        for(int i=0; i<mur.getAccessoires().size();i++){
            List<Coordonnee> accessoryPoints = mur.getAccessoires().get(i).getSommetsByVue(mur.getCote().toVue());
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, normalAvant);
            writeStlForFace(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalHaut);
            writeStlForUpAndDown(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalBas);
            writeStlForCote(writer, accessoryPoints.get(0).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalGauche);
            writeStlForCote(writer, accessoryPoints.get(1).getX().toInches(), accessoryPoints.get(0).getY().toInches(), accessoryPoints.get(2).getY().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalDroite);
        }
        writer.write("endsolid Panneau F\n");
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