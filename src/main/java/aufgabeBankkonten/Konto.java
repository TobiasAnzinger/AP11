package aufgabeBankkonten;

public abstract class Konto {

    private String kunde;
    private int kontonummer;
    private double saldo;
    private double habenszins;

    public Konto(String kunde, int kontonummer) {
        this.kunde = kunde;
        this.kontonummer = kontonummer;
    }


    abstract double einzahlen();

    abstract double abheben();
}
