package aufgabe3Polymorphie;

public class Arbeiter extends Person {

    public Arbeiter(int einkommen, String name) {
        super(einkommen, name);
    }

    @Override
    int zuVersteuerndesEinkommen() {
        if (einkommen > 2400) {
            return einkommen - 2400;
        } else {
            return 0;
        }
    }


}
