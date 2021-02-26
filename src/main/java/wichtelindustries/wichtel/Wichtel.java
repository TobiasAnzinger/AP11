package wichtelindustries.wichtel;

import java.util.Random;

public abstract class Wichtel implements IWichtel{

	protected int dauer;

	/**
	 * Gibt einen zufaelligen Vornamen zurueck, der bei Wichteln beliebt ist.
	 * 
	 * @return Der Name.
	 */
	public static String koboldname() {
		StringBuilder builder = new StringBuilder(100);
		int length;
		while (builder.length() == 0) {
			length = RAND.nextInt(2) + 2;
			for (int i = 0; i < length; i++) {
				builder.append(LEXICON[RAND.nextInt(LEXICON.length)]);
			}
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		return "Wichtel{}";
	}
}
