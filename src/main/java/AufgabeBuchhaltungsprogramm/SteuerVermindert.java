package AufgabeBuchhaltungsprogramm;

public interface SteuerVermindert {

    static final int STEUERSATZ = 19;

    public default double berechneSteuerVermindert(){
        return 0;
    }
}
