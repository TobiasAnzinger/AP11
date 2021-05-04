package swingDisplay;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Pixel[][] image = new Pixel[20][20];

        Pixel pixel1 = new Pixel(new DPSettings(20,20), new Pos(0,0), true);
        System.out.println(pixel1.toString());


        for (int i = 0; i < 20; i++) {

        }
    }
}

class DPSettings {
    int pixel_x;
    int pixel_y;
    Dimension screenSize;

    public DPSettings(int pixel_x, int pixel_y) {
        this.pixel_x = pixel_x;
        this.pixel_y = pixel_y;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }
}
