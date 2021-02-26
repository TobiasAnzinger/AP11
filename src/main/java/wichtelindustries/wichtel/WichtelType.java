package wichtelindustries.wichtel;

public enum WichtelType {

    BLAUER_WICHTEL("blauerWichtel"),
    ROTER_WICHTEL("roterWichtel"),
    GELBER_WICHTEL("gelberWichtel");

    private String type;
    private WichtelType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
