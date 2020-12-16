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

    boolean einzahlen() {
        return false;
    }

    boolean abheben() {
        return false;
    }

}
