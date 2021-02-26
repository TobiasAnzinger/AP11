package wichtelindustries.geschenk;

public enum GeschenkType {

    GESCHENK("Geschenk"),
    ESSBARES("Essbares"),
    KLEIDUNG("Kleidung"),
    SPIELZEUG("Spielzeug");

    private String type;
    private GeschenkType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
