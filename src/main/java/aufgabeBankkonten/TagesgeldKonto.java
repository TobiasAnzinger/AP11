package aufgabeBankkonten;


public class TagesgeldKonto extends Konto {


    public TagesgeldKonto(String kunde, int kontonummer) {
        super(kunde, kontonummer);
    }

    double einzahlen() {
        return 0;
    }

    double abheben() {
        return 0;
    }

}
