package swingDisplay;


import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImageImporter {

    private static final String FILE_ENDING = ".png";
    private static final String BASE_PATH = "Numbers/";

    public List<List<Boolean>> getNumberArray(int number) throws IOException {

        if(number > 9){
            String numberStr = Integer.toString(number);
            List<List<List<Boolean>>> list = new ArrayList<List<List<Boolean>>>();
            for (int j = 0; j < numberStr.length(); j++) {
                list.add(getPixelArrayForSingleNumber(Character.getNumericValue(numberStr.charAt(j))));
            }
            return combineNumberArrays(list);
        }
        else{
            return getPixelArrayForSingleNumber(number);
        }
    }

    private List<List<Boolean>> combineNumberArrays(List<List<List<Boolean>>> arrays){
        List<List<Boolean>> out = new ArrayList<>();
            for (List<List<Boolean>> array :arrays) {
                out.addAll(array);
        }
        return out;
    }

    private List<List<Boolean>> getPixelArrayForSingleNumber(int number) throws IOException {
        BufferedImage image = findImageByInt(number);
        List<List<Boolean>> pixels = new ArrayList<>();
        for (int x = 0; x < image.getWidth(); x++) {
            pixels.add(new ArrayList<>());
            for (int y = 0; y < image.getHeight(); y++) {
                pixels.get(x).add(isBlack(image.getRGB(x, y)));
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
