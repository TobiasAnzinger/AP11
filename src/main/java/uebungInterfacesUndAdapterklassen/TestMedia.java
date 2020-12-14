package uebungInterfacesUndAdapterklassen;

import java.util.ArrayList;

public class TestMedia {

    public static void main(String[] args) {

        Media m1 = new Picture(1);
        Media m2 = new Video(2);
        Media m3 = new Audio(3);

        ArrayList<Media> mediaPlayer = new ArrayList<>();
        mediaPlayer.add(m1);
        mediaPlayer.add(m2);
        mediaPlayer.add(m3);

        mediaPlayer.forEach(m -> {
            System.out.println(m.toString());
            m.display();
            m.play();
            m.stop();
        });

    }
}