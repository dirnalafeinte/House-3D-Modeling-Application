package ca.ulaval.glo2004.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportBrut extends Export {

    public ExportBrut(Chalet chalet, String path) {
        super(chalet, path);
    }

    @Override
    public void export() {
        try {
            for(Panneau panneau : Panneau.values()) {
                String fileName = getFileName("Brut", panneau);

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
                //case PD:
                //    writeStlForPD(writer);
                //    break;
                //case PG:
                //    writeStlForPG(writer);
                //    break;
                //case R:
                //    writeStlForR(writer);
                //    break;
                //case T:
                //    writeStlForT(writer);
                //    break;
            }
        }
    }

    protected void writeStlForF(FileWriter writer) throws IOException {
        //debut
        writer.write("solid Panneau F\n");
        //avant
        writeStlForFace(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), 0, normalAvant);
        //arriere
        writeStlForFace(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
        //gauche
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), 0, 0, chalet.getEpaisseurMur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), chalet.getHauteur().toInches(),0 , chalet.getEpaisseurMur().toInches(), normalBas);
        //fin
        writer.write("endsolid Panneau F\n");
    }


    protected void writeStlForA(FileWriter writer) throws IOException {
        //debut
        writer.write("solid Panneau A\n");
        //avant
        writeStlForFace(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), 0, normalAvant);
        //arriere
        writeStlForFace(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
        //gauche
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), 0, 0, chalet.getEpaisseurMur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), chalet.getHauteur().toInches(),0 , chalet.getEpaisseurMur().toInches(), normalBas);
        //fin
        writer.write("endsolid Panneau A\n");
    }


    protected void writeStlForG(FileWriter writer) throws IOException {
        //debut
        writer.write("solid Panneau G\n");
        //avant
        writeStlForFace(writer, 0, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), 0, normalAvant);
        //arriere
        writeStlForFace(writer, 0, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
        //gauche
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), 0, 0, chalet.getEpaisseurMur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalBas);
        //fin
        writer.write("endsolid Panneau F\n");
    }


    protected void writeStlForD(FileWriter writer) throws IOException {
        //debut
        writer.write("solid Panneau F\n");
        //avant
        writeStlForFace(writer, 0, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), 0, normalAvant);
        //arriere
        writeStlForFace(writer, 0, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getEpaisseurMur().toInches(), normalArriere);
        //gauche
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getLongueur().toInches(), 0, chalet.getHauteur().toInches(),0, chalet.getEpaisseurMur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), 0, 0, chalet.getEpaisseurMur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, 0, chalet.getLongueur().toInches(), chalet.getHauteur().toInches(), 0, chalet.getEpaisseurMur().toInches(), normalBas);
        //fin
        writer.write("endsolid Panneau F\n");
    }

//    protected void writeStlForT(FileWriter writer) throws IOException {
//        writer.write("STL data for Panneau T");
//    }
//
//
//    protected void writeStlForR(FileWriter writer) throws IOException {
//        writer.write("STL data for Panneau R");
//    }
//
//
//    protected void writeStlForPG(FileWriter writer) throws IOException {
//        writer.write("STL data for Panneau PG");
//    }
//
//
//    protected void writeStlForPD(FileWriter writer) throws IOException {
//        writer.write("STL data for Panneau PD");
//    }
}
