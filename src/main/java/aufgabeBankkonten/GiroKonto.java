package aufgabeBankkonten;


import lombok.Getter;
import lombok.Setter;

public class GiroKonto extends Konto{




    @Getter
    private double dispokredit;

    public boolean setDispokredit(double dispokredit) {
        this.dispokredit = dispokredit;
        return true;
    }

    @Getter
    private double zinssatzDispokredit;

    public boolean setZinssatzDispokredit(double zinssatzDispokredit) {
        this.zinssatzDispokredit = zinssatzDispokredit;
        return true;
    }



    public GiroKonto(Kunde kunde, int kontonummer) {
        super(kunde, kontonummer);
    }


    boolean einzahlen() {
        return false;
    }

    boolean abheben() {
        return false;
    }
}
