package ca.ulaval.glo2004.domain;

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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
