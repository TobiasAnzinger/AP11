package aufgabe3Polymorphie;

public class Arbeitssuchend extends Person {

    public Arbeitssuchend(int einkommen, String name) {
        super(einkommen, name);
    }

    @Override
    int zuVersteuerndesEinkommen() {
        return einkommen;
    }
}
