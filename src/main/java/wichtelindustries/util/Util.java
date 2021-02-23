package wichtelindustries.util;

public class Util {

	public static String create(Object... str) {
		StringBuilder buf = new StringBuilder(100);
		for (int i = 0; i < str.length; i++) {
			buf.append(str[i].toString());
		}
		return buf.toString();
	}

	public static String createWithSpace(Object... str) {
		StringBuilder buf = new StringBuilder(100);
		for (int i = 0; i < str.length; i++) {
			buf.append(str[i].toString());
			buf.append(" ");
		}
		return buf.toString();
	}

	public static int getZufallszahl(int max){
		try {
			return (int) (Math.random() * (max - 1)) + 1;
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("invalid input");
		}
		return 0;
	}

}
