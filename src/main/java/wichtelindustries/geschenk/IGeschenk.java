package wichtelindustries.geschenk;


import java.util.List;

public interface IGeschenk {

	static List<IGeschenk> generiereGeschenke(int anzahlGeschenke) {
		return null;
	}

	public String getName();

	public int getSchwierigkeit();

	public String toString();
	
	
	 public String[] GESCHENKE = { "Spannendes Buch", "Robuster Nussknacker", "Schnelles Auto",
				"Stacheliger Kaktus", "Duftende Badesalze", "Leckerer Essensgutschein" };

	 public String[] ESSBARES = { "Schoko-Nikolaus", "Knusprige Kekse", "Aromatischer Tee",
				"Selbstgemachtes Pesto", "Leckere Papaya", "Saftige Kekse" };

	 public String[] KLEIDUNG = { "Kuscheliger Schal", "Rote Unterbuxe", "Thermo-Ski-Unterwaesche",
				"Warme Socken", "Lange Struempfe", "Dicker Pullover", "Zipfelige Muetze", "Bequeme Hose" };

	 public String[] SPIELZEUG = { "Niedliche Puppe", "Interessantes Brettspiel", "Schnelles Auto",
				"Grosser Bagger", "Schnelles Kartenspiel", "Rasende Eisenbahn", "Gemaltes Memory" };
}
