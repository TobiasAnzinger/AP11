package swingDisplay;


import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageImporter {

    private static final String FILE_ENDING = ".png";
    private static final String BASE_PATH = "Chars/";

    public List<List<Boolean>> getNumberArray(String value) throws IOException {
        if (value == null || value.length() == 0){
            return null;
        }else if(value.length() > 1){
            return getPixelArrayForMultiChar(value);
        }
        else{
            return getPixelArrayForSingleChar(value.charAt(0));
        }
    }

    private List<List<Boolean>> getPixelArrayForMultiChar(String value) throws IOException {
        List<List<List<Boolean>>> list = new ArrayList<List<List<Boolean>>>();
        for (int j = 0; j < value.length(); j++) {
            list.add(getPixelArrayForSingleChar(value.charAt(j)));
        }
        return combineNumberArrays(list);
    }

    private List<List<Boolean>> combineNumberArrays(List<List<List<Boolean>>> arrays){
        List<List<Boolean>> out = new ArrayList<>();
            for (List<List<Boolean>> array :arrays) {
                out.addAll(array);
        }
        return out;
    }

    private List<List<Boolean>> getPixelArrayForSingleChar(char value) throws IOException {
        BufferedImage image = findImageByChar(value);
        List<List<Boolean>> pixels = new ArrayList<>();
        for (int x = 0; x < image.getWidth(); x++) {
            pixels.add(new ArrayList<>());
            for (int y = 0; y < image.getHeight(); y++) {
                pixels.get(x).add(isBlack(image.getRGB(x, y)));
            }
        }
        return pixels;
    }

    private BufferedImage findImageByChar(char value) throws IOException {
        String fileName = "";
        value = Character.toUpperCase(value);
        if (value == ' ') {
            fileName = "SPACE";
        }else{
            fileName = Character.toString(value);
        }
        File file = new File(BASE_PATH + fileName + FILE_ENDING);
        return ImageIO.read(file);
    }

    private boolean isBlack(int rgb) {
        Color color = new Color(rgb);
        return color.getRed() < 127;
    }
}
