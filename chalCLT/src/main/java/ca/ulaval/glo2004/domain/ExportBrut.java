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
        writeStlForCote(writer, 0, 0, chalet.getHauteur().toInches(),chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getEpaisseurMur().toInches(), normalGauche);
        //droite
        writeStlForCote(writer, chalet.getLargeur().toInches(), 0, chalet.getHauteur().toInches(),chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getEpaisseurMur().toInches(), normalDroite);
        //haut
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), 0, chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches(), chalet.getEpaisseurMur().toInches(), normalHaut);
        //bas
        writeStlForUpAndDown(writer, 0, chalet.getLargeur().toInches(), chalet.getHauteur().toInches(),chalet.getLongueur().toInches()-chalet.getEpaisseurMur().toInches() , chalet.getEpaisseurMur().toInches(), normalBas);
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
        //avant
        //writeStlForFace(writer,  0, chalet.Toit.sommetByVue("FACADE").value(1)[0].toInches(),chalet.toit.sommetByVue("FACADE").value(2)[0].toInches(),chalet.toit.sommetByVue("FACADE").value(1)[1].toInches()+chalet.getHauteur().toInches(),chalet.toit.sommetByVue("FACADE").value(3)[1].toInches()+ chalet.getHauteur().toInches(),normalAvant);
        //arriere
     //   writeStlForFace(writer,  0, chalet.toit.sommetByVue("ARRIERE").value(1)[0].toInches(),chalet.toit.sommetByVue("ARRIERE").value(2)[0].toInches(),chalet.toit.sommetByVue("ARRIERE").value(1)[1].toInches())+chalet.getHauteur().toInches(),-(chalet.toit.sommetByVue("ARRIERE").value(3)[1].toInches())+ chalet.getHauteur().toInches(),normalArriere);

        //gauche
        writer.write("facet normal " + normalDroite + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + 0 + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches-chalet.getSmallEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("vertex " + 0 + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + 0 + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches + " " + chalet.getLargeur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + 0 + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + 0 + " " + chalet.Toit.getHauteurToit().toInches()+chalet.getHauteur().toInches() + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + 0 + " " + chalet.Toit.getHauteurToitSecondaire().toInches()+chalet.getHauteur().toInches() + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        //rainure
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("endfacet\n");
        //droite
        writer.write("facet normal " + normalDroite + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches-chalet.getSmallEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.Toit.getHauteurToit.toInches()+chalet.getHauteur().toInches + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + 0 + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.Toit.getHauteurToit().toInches()+chalet.getHauteur().toInches() + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.getSmallEpaisseur().toInches() + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.Toit.getHauteurToitSecondaire().toInches()+chalet.getHauteur().toInches() + " " + chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        //rainure
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("endfacet\n");
        //haut
        writer.write("facet normal " + normalHaut + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToitSecondaire().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToitSecondaire().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToitSecondaire().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.Toit.getSmallEpaisseur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getSmallEpaisseur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToitSecondaire().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.Toit.getSmallEpaisseur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToitSecondaire().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToitSecondaire().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        //bas
        writer.write("facet normal " + normalBas + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getLongueurToit().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + 0 + "\n");
        writer.write("vertex " + 0 + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches() + " " + chalet.getHauteur().toInches() + " " + chalet.Toit.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        //rainure
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("outer loop\n");
        writer.write("vertex " + chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()+chalet.Toit.getHauteurToit().toInches-chalet.getEpaisseur().toInches() + " " + chalet.Toit.getLongueurToit().toInches()-chalet.getSmallEpaisseur().toInches() + "\n");
        writer.write("vertex " + chalet.getLongueur().toInches()-chalet.Toit.getSmallEpaisseur().toInches() + " " + chalet.getHauteur().toInches()-chalet.Toit.getBigEpaisseur().toInches() + " " + chalet.getEpaisseur().toInches() + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("endsolid Panneau T\n");
    }
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
