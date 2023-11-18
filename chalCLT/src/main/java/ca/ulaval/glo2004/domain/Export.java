package ca.ulaval.glo2004.domain;

public abstract class Export {
    private Chalet chalet;
    private String path;

    public Export(Chalet chalet, String path){
        this.chalet = chalet;
        this.path = path;
    }

}
