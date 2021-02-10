package AufgabeBuchhaltungsprogramm;

public interface Steuer {

    static final int STEUERSATZ = 19;

    public default double berechneSteuer(){
        return 0;
    }

}
