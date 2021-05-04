package swing;


import javax.sound.sampled.*;
import java.io.IOException;

public class Main extends Thread {
    static Clip clip;


    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {

//        new EasyWindow2();
//        new EasyWindow();
        for (int i = 0; i < 30; i++) {
            Thread t = new T();
            t.start();
        }

    }

}

