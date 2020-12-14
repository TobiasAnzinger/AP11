package aufgabe3Polymorphie;

public class Banker extends Person {

    public Banker(int einkommen, String name) {
        super(einkommen, name);
    }

    @Override
    int zuVersteuerndesEinkommen() {
        return einkommen ;
    }

    @Override
    int steuer() {
        if(einkommen > 1000){
            return super.steuer() + 1000;
        } else return einkommen;
    }
}
