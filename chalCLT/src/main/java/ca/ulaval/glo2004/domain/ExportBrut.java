package ca.ulaval.glo2004.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ca.ulaval.glo2004.domain.Panneau;

public class ExportBrut extends Export {
    String normalAvant= "0.00000E+000 .00000E+000 -1.00000E+000";
    String normalArriere= "0.00000E+000 .00000E+000 1.00000E+000";
    String normalGauche= "-1.00000E+000 .00000E+000 .00000E+000";
    String normalDroite= "1.00000E+000 .00000E+000 .00000E+000";
    String normalHaut= "0.00000E+000 1.00000E+000 .00000E+000";
    String normalBas= "0.00000E+000 -1.00000E+000 .00000E+000";

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
//        writer.write("Panneau F\n");
//        // face avant
//        writer.write("    facet normal "+vectorAvant+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face avant 2
//        writer.write("    facet normal "+vectorAvant+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        // face arriere
//        writer.write("    facet normal "+vectorArriere+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face arriere 2
//        writer.write("    facet normal "+vectorArriere+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        // face gauche
//        writer.write("    facet normal "+vectorGauche+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face gauche 2
//        writer.write("    facet normal "+vectorGauche+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        // face droite
//        writer.write("    facet normal "+vectorDroite+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face droite 2
//        writer.write("    facet normal "+vectorDroite+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        // face haut
//        writer.write("    facet normal "+vectorHaut+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face haut 2
//        writer.write("    facet normal "+vectorHaut+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        // face bas
//        writer.write("    facet normal "+vectorBas+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face bas 2
//        writer.write("    facet normal "+vectorBas+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face rainure gauche 1 1 (vector arriere gauche)
//        writer.write("    facet normal "+vectorArriere+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face rainure gauche 1 2 (vector arriere gauche)
//        writer.write("    facet normal "+vectorArriere+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+" \n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face rainure gauche 2 1(vector gauche gauche)
//        writer.write("    facet normal "+vectorGauche+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face rainure gauche 2 2 (vector gauche gauche)
//        writer.write("    facet normal "+vectorGauche+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+" \n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face rainure droite 1 1(vector arriere droite)
//        writer.write("    facet normal "+vector+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+" \n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face rainure droite 1 2 (vector arriere droite)
//        writer.write("    facet normal "+vectorArriere+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //face rainure droite 2 1 (vector droite droite)
//        writer.write("    facet normal "+vectorDroite+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+" \n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        //face rainure droite 2 2 (vector droite droite)
//        writer.write("    facet normal "+vectorDroite+"\n");
//        writer.write("        outer loop\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("            vertex "+coords+"\n");
//        writer.write("        endloop\n");
//        writer.write("    endfacet\n");
//        //fin
//        writer.write("endsolid Panneau F\n");
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
