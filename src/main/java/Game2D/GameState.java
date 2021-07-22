package Game2D;

import java.awt.image.BufferedImage;


public class GameState {

    int score = 0;

    UI ui;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    static BufferedImage currentLevel = null;

    public GameState() {
        ui = new UI();
    }

    synchronized public void addScore(int value){
        score += value;
    }


}
