package aufgabeBankkonten;


import lombok.Getter;
import lombok.Setter;

public class GiroKonto extends Konto {


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


    boolean einzahlen(double amount) {
        if (amount > 0){
            setSaldo(getSaldo() + amount);
            return true;
        }
        return false;
    }

    boolean abheben(double amount) {
        if (amount > 0 && amount < getSaldo() + dispokredit ){
            setSaldo(getSaldo() - amount);
            return true;
        }
        return false;
    }
}
