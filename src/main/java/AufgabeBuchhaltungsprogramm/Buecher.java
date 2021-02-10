package AufgabeBuchhaltungsprogramm;

public class Buecher extends Produkte implements SteuerVermindert {

    private String autor;

    public Buecher(String autor) {
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String getBeschreibung() {
        return super.getBeschreibung();
    }

    @Override
    public double getPreis() {
        return super.getPreis();
    }

    @Override
    public double berechneSteuerVermindert() {
        return getPreis() * STEUERSATZ * 100;
    }
}
