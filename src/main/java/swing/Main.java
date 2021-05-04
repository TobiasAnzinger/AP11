package swing;


import javax.sound.sampled.*;

public class Main extends Thread {
    static Clip clip;


    public static void main(String[] args) {

//        new EasyWindow2();
        for (int i = 0; i < 30; i++) {
            Thread t = new T();
            t.start();
        }

    }

}

