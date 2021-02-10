package AufgabeBuchhaltungsprogramm;

public class Kosmetik extends Produkte implements Steuer{

    private String inhaltsstoff;

    public String getInhaltsstoff() {
        return inhaltsstoff;
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
    public double berechneSteuer() {
        return getPreis() * STEUERSATZ * 100;
    }
}
