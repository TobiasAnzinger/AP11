package AufgabeBuchhaltungsprogramm;

public class Lebensmittel extends Produkte implements SteuerVermindert {

    private int kalorienAngabe;

    public int getKalorienAngabe() {
        return kalorienAngabe;
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
