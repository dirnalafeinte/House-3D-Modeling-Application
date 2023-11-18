package ca.ulaval.glo2004.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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