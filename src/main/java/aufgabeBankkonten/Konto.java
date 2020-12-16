package aufgabeBankkonten;

import lombok.Getter;
import lombok.Setter;

public abstract class Konto {

    @Setter
    @Getter
    private Kunde kunde;

    @Setter
    @Getter
    private int kontonummer;

    @Setter
    @Getter
    private double saldo;

    @Getter
    private double zinssatzHaben;

    public boolean setZinssatzHaben(double zinssatzHaben) {
        this.zinssatzHaben = zinssatzHaben;
        return true;
    }

    public Konto(Kunde kunde, int kontonummer) {
        this.kunde = kunde;
        this.kontonummer = kontonummer;
    }


    abstract boolean einzahlen(double amount);

    abstract boolean abheben(double amount);


}
