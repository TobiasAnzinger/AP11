package AufgabeBuchhaltungsprogramm;

public class Buecher extends Produkte{

    private String autor;

    public Buecher(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }
}
