package Game2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level {

    int width;
    int height;

    private boolean isMovingY = false;
    private boolean isMovingX = false;

    String name;

    BufferedImage image;


    public Level(String path) {
        this.name = (path.split("\\/")[path.split("\\/").length -1]).split("\\.")[0];
        loadLevel(path);
        width = image.getWidth();
        height = image.getHeight();
        System.out.println("loaded level: " + name);
    }


    public void loadLevel(String path){
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isMovingX() {
        return isMovingX;
    }

    public void setMovingX(boolean movingX) {
        isMovingX = movingX;
    }

    public boolean isMovingY() {
        return isMovingY;
    }

    public void setMovingY(boolean movingY) {
        isMovingY = movingY;
    }
}
