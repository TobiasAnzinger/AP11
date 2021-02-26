package wichtelindustries.wichtel;

import wichtelindustries.geschenk.Essbares;
import wichtelindustries.geschenk.IGeschenk;

public class BlauerWichtel extends Wichtel{

    @Override
    public void arbeite(IGeschenk geschenk) {
        if(geschenk instanceof Essbares) {
            super.dauer = Math.round(geschenk.getSchwierigkeit()/2);
        } else {
            super.dauer = geschenk.getSchwierigkeit() + 3;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", WichtelType.BLAUER_WICHTEL.toString(), super.toString());
    }

    @Override
    public boolean arbeiteNoch() {
        return false;
    }

    @Override
    public void arbeiteWeiter() {

    }
}