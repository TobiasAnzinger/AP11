package swingDisplay;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        ImageImporter im = new ImageImporter();


        try {
            boolean[][] image = im.getNumberArray(-1);


        DPSettings dpSettings = new DPSettings(image.length, image[0].length);

        Pixel[][] display = new Pixel[dpSettings.pixel_x][dpSettings.pixel_y];

//        System.out.println(pixel1.toString());

//        display[0][0] = new Pixel(new DPSettings(20,20), new Pos(0,0), true);

//        int k = 0;
//        for (int i = 0; i < display.length; i++) {
//            for (int j = 0; j < display[0].length; j++) {
//                display[i][j] = new Pixel(dpSettings, new Pos(i,j), k % 2 == 0);
//                k++;
//            }
//            k++;
//
//        }

        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[0].length; j++) {
                display[i][j] = new Pixel(dpSettings, new Pos(i,j), image[i][j]);
            }

        }

        } catch (IOException e) {
            e.printStackTrace();
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
