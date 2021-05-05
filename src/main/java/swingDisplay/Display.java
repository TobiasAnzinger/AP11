package swingDisplay;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Display {

    static ImageImporter im = new ImageImporter();


    public static Pixel[][] clear(Pixel[][] display) {
        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[0].length; j++) {
                display[i][j].setVisible(false);
                display[i][j].dispose();

            }

        }
        return display;
    }

    public static Pixel[][] init() {
//        DPSettings dpSettings = new DPSettings(0, 0);
//        Pixel[][] display = new Pixel[dpSettings.pixel_x][dpSettings.pixel_y];
//
//        for (int i = 0; i < display.length; i++) {
//            for (int j = 0; j < display[0].length; j++) {
//                display[i][j] = new Pixel(dpSettings, new Pos(i, j), false);
//            }
//        }
        return new Pixel[0][0];
    }

    public static Pixel[][] generate(String st) {
        try {
            List<List<Boolean>> image = im.getNumberArray(st);
            DPSettings dpSettings = new DPSettings(image.size(), image.get(0).size());
            Pixel[][] display = new Pixel[dpSettings.pixel_x][dpSettings.pixel_y];

            for (int i = 0; i < display.length; i++) {
                for (int j = 0; j < display[0].length; j++) {
                    display[i][j] = new Pixel(dpSettings, new Pos(i, j), image.get(i).get(j));
                }
            }
            return display;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Pixel[][] generate(String st, Pixel[][] display) {
        try {
            List<List<Boolean>> image = im.getNumberArray(st);
            for (int i = 0; i < display.length; i++) {
                for (int j = 0; j < display[0].length; j++) {
                    display[i][j].setVisible(image.get(i).get(j));
                }
            }
            return display;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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