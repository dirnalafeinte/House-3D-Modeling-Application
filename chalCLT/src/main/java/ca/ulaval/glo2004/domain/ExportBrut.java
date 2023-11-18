package ca.ulaval.glo2004.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ca.ulaval.glo2004.domain.Panneau;

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
