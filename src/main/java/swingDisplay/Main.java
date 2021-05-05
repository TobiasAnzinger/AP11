package swingDisplay;


import swing.T;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) {
        countdownFrom(11);
    }

    private static void countdownFrom(int start) {
        Pixel[][] display = Display.generate(" ");
        for (int i = start; i >= 0; i--) {
            assert display != null;
            display = Display.generate(Integer.toString(i));
            waitSeconds(1);
            assert display != null;
            Display.clear(display);
        }
        try {
            for (int i = 0; i < 30; i++) {
                Thread t = new T();
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void waitSeconds(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}