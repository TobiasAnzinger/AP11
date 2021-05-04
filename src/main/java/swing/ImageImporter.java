package swing;


import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageImporter {

    private static final String FILE_ENDING = ".png";
    private static final String BASE_PATH = "Numbers/";

    public boolean[][] getNumberArray(int i) throws IOException {
        boolean[][] pixels;
        BufferedImage image = findImageByInt(i);

        pixels = new boolean[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                pixels[x][y] = isBlack(image.getRGB(x, y));
            }
        }
        return pixels;
    }

    private BufferedImage findImageByInt(int i) throws IOException {
        System.out.println(BASE_PATH + i + FILE_ENDING);
        File file = new File(BASE_PATH + i + FILE_ENDING);
        return ImageIO.read(file);

    }

    private boolean isBlack(int rgb) {
        Color color = new Color(rgb);
        return color.getRed() < 127;
    }
}
