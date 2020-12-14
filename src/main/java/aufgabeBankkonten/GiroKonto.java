package aufgabeBankkonten;


public class GiroKonto extends Konto{


    public GiroKonto(String kunde, int kontonummer) {
        super(kunde, kontonummer);
    }


    double einzahlen() {
        return 0;
    }

    double abheben() {
        return 0;
    }
}
