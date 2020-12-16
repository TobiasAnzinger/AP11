package aufgabeBankkonten;


public class TagesgeldKonto extends Konto {

    private int zinsintervall;

    public int getZinsintervall() {
        return zinsintervall;
    }

    public boolean setZinsintervall(int zinsintervall) {
        this.zinsintervall = zinsintervall;
        return true;
    }

    public TagesgeldKonto(Kunde kunde, int kontonummer) {
        super(kunde, kontonummer);
    }

    boolean einzahlen(double amount) {
        if (amount > 0) {
            setSaldo(getSaldo() + amount);
            return true;
        }
        return false;
    }

    boolean abheben(double amount) {
        if (amount > 0 && amount < getSaldo()) {
            setSaldo(getSaldo() - amount);
            return true;
        }
        return false;
    }
}
