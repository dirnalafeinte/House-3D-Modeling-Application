package ca.ulaval.glo2004.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Export {
    private static final String DEFAULT_PROJECT_NAME = "ChalCLT";
    private final String projectName;
    protected static Chalet chalet;
    protected String path;
    String normalAvant= "0.00000E+000 .00000E+000 -1.00000E+000";
    String normalArriere= "0.00000E+000 .00000E+000 1.00000E+000";
    String normalGauche= "-1.00000E+000 .00000E+000 .00000E+000";
    String normalDroite= "1.00000E+000 .00000E+000 .00000E+000";
    String normalHaut= "0.00000E+000 1.00000E+000 .00000E+000";
    String normalBas= "0.00000E+000 -1.00000E+000 .00000E+000";

    public Export(Chalet chalet, String path) {
        Export.chalet = chalet;
        this.path = path;
        this.projectName = DEFAULT_PROJECT_NAME;
    }

    public abstract void export();

    protected static void writeStlForFace(FileWriter writer, double debutRectangleX, double endRectangleX, double debutRectangleY, double endRectangleY, double z, String normalVector) throws IOException{
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + debutRectangleX + " " + debutRectangleY + " " + z + "\n");
        writer.write("vertex " + endRectangleX + " " + debutRectangleY + " " + z + "\n");
        writer.write("vertex " + endRectangleX + " " + endRectangleY + " " + z + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + debutRectangleX + " " + debutRectangleY + " " + z + "\n");
        writer.write("vertex " + endRectangleX + " " + endRectangleY + " " + z + "\n");
        writer.write("vertex " + debutRectangleX + " " + endRectangleY + " " + z + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
    }

    protected static void writeStlForCote(FileWriter writer,  double x, double debutRectangleY, double endRectangleY,double debutRectangleZ, double endRectangleZ, String normalVector) throws IOException{
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + x + " " + debutRectangleY + " " + debutRectangleZ + "\n");
        writer.write("vertex " + x + " " + debutRectangleY + " " + endRectangleZ + "\n");
        writer.write("vertex " + x + " " + endRectangleY + " " + endRectangleZ + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + x + " " + debutRectangleY + " " + debutRectangleZ + "\n");
        writer.write("vertex " + x + " " + endRectangleY + " " + debutRectangleZ + "\n");
        writer.write("vertex " + x + " " + endRectangleY + " " + endRectangleZ + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
    }
    protected static void writeStlForUpAndDown(FileWriter writer, double debutRectangleX, double endRectangleX,  double y, double debutRectangleZ, double endRectangleZ, String normalVector) throws IOException{
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + debutRectangleX + " " + y + " " + debutRectangleZ + "\n");
        writer.write("vertex " + endRectangleX + " " + y + " " + debutRectangleZ + "\n");
        writer.write("vertex " + endRectangleX + " " + y + " " + endRectangleZ + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
        writer.write("facet normal " + normalVector + "\n");
        writer.write("outer loop\n");
        writer.write("vertex " + debutRectangleX + " " + y + " " + debutRectangleZ + "\n");
        writer.write("vertex " + endRectangleX + " " + y + " " + endRectangleZ + "\n");
        writer.write("vertex " + debutRectangleX + " " + y + " " + endRectangleZ + "\n");
        writer.write("endloop\n");
        writer.write("endfacet\n");
    }

    protected String getFileName(String panelType, Panneau panneau) {
        return String.format("%s_%s_%s.stl", projectName, panelType, panneau);
    }

    protected String getFileName(String panelType, Panneau panneau, int sequentialNumber) {
        return String.format("%s_%s_%s_%d.stl", projectName, panelType, panneau, sequentialNumber);
    }
}
