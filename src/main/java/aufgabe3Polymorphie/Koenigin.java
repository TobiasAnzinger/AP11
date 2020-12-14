package aufgabe3Polymorphie;

public class Koenigin extends Person {


    public Koenigin(int einkommen, String name) {
        super(einkommen, name);
    }

    @Override
    int zuVersteuerndesEinkommen() {
        return 0;
    }

    @Override
    int steuer() {
        return 0;
    }
}
