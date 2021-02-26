package wichtelindustries.wichtel;


import wichtelindustries.geschenk.IGeschenk;

import java.util.List;

public interface IWichtel extends Comparable<IWichtel> {


    public String[] LEXICON = {"ARI", "BOR", "CAR", "DUR", "EORL", "FRI", "GEROS", "HATI", "IOTI",
            "JOLA", "KO", "LE", "MU", "NOR", "OPO", "ROMI", "SETI", "TAZ", "UMA", "VAR", "WEL", "XUL", "YIFI", "ZOTO"};

    static List<IWichtel> generiereWichtel(int anzahlWichtel) {
        return null;
    }

    public String toString();

    public boolean arbeiteNoch();

    public void arbeiteWeiter();

    public void arbeite(IGeschenk g);

    //	TODO fix
    @Override
	default int compareTo(IWichtel o) {
        if (o.effizienz() > this.effizienz()) {
            return 1;
        } else if (o.effizienz() < this.effizienz()) {
            return -1;
        }
        return 0;
    }


    // TODO fix
    double effizienz();
}
