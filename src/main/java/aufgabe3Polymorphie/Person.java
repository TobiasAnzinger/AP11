package aufgabe3Polymorphie;

public abstract class Person {

    String name;

    public Person(int einkommen, String name) {
        this.einkommen = einkommen;
        this.name = name;
    }

    int einkommen;

    abstract int zuVersteuerndesEinkommen();

    int steuer() {
        return (int) (zuVersteuerndesEinkommen() * 0.25);
    }

    int restgeld() {
        return einkommen - steuer();
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append(name).append("\n");
        st.append("Einkommen: ").append(einkommen).append("\n");
        st.append("zu versteuerndes Einkommen:").append(zuVersteuerndesEinkommen()).append("\n");
        st.append("zu zahlende Steuer: ").append(steuer()).append("\n");
        st.append("der traurige Rest (): ").append(restgeld()).append("\n\n");
        return st.toString();
    }
}
