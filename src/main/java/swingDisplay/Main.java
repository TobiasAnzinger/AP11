package swingDisplay;


import java.util.concurrent.TimeUnit;

public class Main {



    public static void main(String[] args) {
        countdownFrom(11);
    }

    private static void countdownFrom(int start) {
        Pixel[][] display = Display.generate(" ");
        for (int i = start; i >= 0; i--) {
            display = Display.generate(Integer.toString(i), display);
            waitSeconds(1);
            assert display != null;
            Display.clear(display);

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