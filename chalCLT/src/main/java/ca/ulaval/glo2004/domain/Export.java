package ca.ulaval.glo2004.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ca.ulaval.glo2004.domain.Panneau;

public abstract class Export {
    private static final String DEFAULT_PROJECT_NAME = "ChalCLT";
    private String projectName;
    private Chalet chalet;
    protected String path;

    public Export(Chalet chalet, String path) {
        this.chalet = chalet;
        this.path = path;
        this.projectName = DEFAULT_PROJECT_NAME;
    }

    public abstract void export();

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
    protected abstract void writeStlForF(FileWriter writer) throws IOException;

    protected abstract void writeStlForA(FileWriter writer) throws IOException;

    protected abstract void writeStlForG(FileWriter writer) throws IOException;

    protected abstract void writeStlForD(FileWriter writer) throws IOException;

    protected abstract void writeStlForT(FileWriter writer) throws IOException;

    protected abstract void writeStlForR(FileWriter writer) throws IOException;

    protected abstract void writeStlForPG(FileWriter writer) throws IOException;

   protected abstract void writeStlForPD(FileWriter writer) throws IOException;

    protected String getFileName(String panelType, Panneau panneau) {
        return String.format("%s_%s_%s.stl", projectName, panelType, panneau);
    }

    protected String getFileName(String panelType, Panneau panneau, int sequentialNumber) {
        return String.format("%s_%s_%s_%d.stl", projectName, panelType, panneau, sequentialNumber);
    }
}
