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
        writeStlForFace(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches(), normalAvant);
        //arriere
        writeStlForFace(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getLongueur().toInches(), normalArriere);
        //gauche
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(),chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getLongueur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(),chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getLongueur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getLongueur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), chalet.getHauteur().toInches(),chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches() , chalet.getLongueur().toInches(), normalBas);
        //fin
        writer.write("endsolid Panneau A\n");
    }


    protected void writeStlForG(FileWriter writer) throws IOException {
        //debut
        writer.write("solid Panneau G\n");
        //avant
        writeStlForFace(writer, 0, chalet.getEpaisseurMur().toInches(), 0, chalet.getHauteur().toInches(), 0, normalAvant);
        //arriere
        writeStlForFace(writer, 0, chalet.getEpaisseurMur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getLongueur().toInches(), normalArriere);
        //gauche
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(),0, chalet.getLongueur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getEpaisseurMur().toInches(), 0, chalet.getHauteur().toInches(),0, chalet.getLongueur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, 0, chalet.getEpaisseurMur().toInches(), 0, 0, chalet.getLongueur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, 0, chalet.getEpaisseurMur().toInches(), chalet.getHauteur().toInches(), 0, chalet.getLongueur().toInches(), normalHaut);
        //fin
        writer.write("endsolid Panneau G\n");
    }


    protected void writeStlForD(FileWriter writer) throws IOException {
        //debut
        writer.write("solid Panneau D\n");
        //avant
        writeStlForFace(writer, chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), 0, normalAvant);
        //arriere
        writeStlForFace(writer, chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(), chalet.getLongueur().toInches(), normalArriere);
        //gauche
        writeStlForCote(writer, chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), 0, chalet.getHauteur().toInches(),0, chalet.getLongueur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(),0, chalet.getLongueur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getLargeur().toInches(), 0, 0, chalet.getLongueur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, chalet.getLargeur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getLargeur().toInches(), chalet.getHauteur().toInches(), 0, chalet.getLongueur().toInches(), normalHaut);
        //fin
        writer.write("endsolid Panneau D\n");
    }

    protected void writeStlForT(FileWriter writer) throws IOException {
        writer.write("solid Panneau T\n");
        writeStlForCote(writer, getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.toit.getHauteurToit().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), normalGauche);
        writeStlForCote(writer, chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.toit.getHauteurToit().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), normalDroite);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.toit.getHauteurToit().toInches()+chalet.getHauteur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), normalAvant);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.toit.getHauteurToit().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(),normalArriere);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.toit.getHauteurToit().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), normalBas);
        writer.write("endsolid Panneau T\n");

    }

    protected void writeStlForR(FileWriter writer) throws IOException {
        writer.write("solid Panneau R\n");
        writeStlForCote(writer, getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.rallonge.getHauteurRallonge().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.rallonge.getLongueurRallonge().toInches()+getSmallEpaisseur().toInches(), normalGauche);
        writeStlForCote(writer, chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.rallonge.getHauteurRallonge().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.rallonge.getLongueurRallonge().toInches()+getSmallEpaisseur().toInches(), normalDroite);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.rallonge.getHauteurRallonge().toInches()+chalet.getHauteur().toInches(), chalet.rallonge.getLongueurRallonge().toInches()+getSmallEpaisseur().toInches(), normalAvant);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.rallonge.getHauteurRallonge().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(),normalArriere);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.rallonge.getHauteurRallonge().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.rallonge.getLongueurRallonge().toInches()+getSmallEpaisseur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.toit.getLongueurToit().toInches()+getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.rallonge.getLongueurRallonge().toInches()+getSmallEpaisseur().toInches(), normalBas);
        writer.write("endsolid Panneau R\n");
    }


    protected void writeStlForPG(FileWriter writer) throws IOException {
        writer.write("solid Panneau PG\n");
        writeStlForCote(writer, getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.pignonDroit.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalGauche);
        writeStlForCote(writer, chalet.pignonDroit.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), chalet.pignonDroit.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalDroite);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), chalet.pignonDroit.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalAvant);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), chalet.pignonDroit.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(),normalArriere);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.pignonDroit.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonDroit.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalBas);
        writer.write("endsolid Panneau PG\n");
    }


    protected void writeStlForPD(FileWriter writer) throws IOException {
        writer.write("solid Panneau PD\n");
        writeStlForCote(writer, getSmallEpaisseur().toInches(), chalet.getHauteur().toInches(), chalet.pignonGauche.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalGauche);
        writeStlForCote(writer, chalet.pignonGauche.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), chalet.pignonGauche.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalDroite);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), chalet.pignonGauche.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalAvant);
        writeStlForFace(writer, getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), chalet.pignonGauche.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(),normalArriere);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.pignonGauche.getHauteurPignon().toInches()+chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalHaut);
        writeStlForUpAndDown(writer, getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+chalet.getHauteur().toInches(), chalet.getHauteur().toInches(), getSmallEpaisseur().toInches(), chalet.pignonGauche.getLongueurPignon().toInches()+getSmallEpaisseur().toInches(), normalBas);
        writer.write("endsolid Panneau PD\n");
    }
}
