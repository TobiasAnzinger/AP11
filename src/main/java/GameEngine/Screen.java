package GameEngine;

import GameEngine.snakeGame.Position;
import GameEngine.snakeGame.Snake;

import java.util.Arrays;

public class Screen {

    private final int width;
    private final int height;
    public int[] pixels;
    public int[][] pixels2D;
    public Snake snake = new Snake(new Position(Game.width / 2, Game.height / 2));


    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        pixels2D = new int[width][height];
    }

    public void render() {
        renderFood(Game.foodPos, Constants.SNAKEHEAD_SIZE);

        snake.getElements().forEach(part -> {
            int x = part.getPos().getX();
            int y = part.getPos().getY();
            if (x >= 0 && x <= width + Constants.SNAKEHEAD_SIZE && y >= 0 && y <= height + Constants.SNAKEHEAD_SIZE)
                renderSquare(part.getPos(), part.getRadius(), part.getRadius(), Constants.SNAKE_COLOR);
        });
        pixels = convert(pixels2D);

//        renderCircle(Game.food, 0);

    }

    private void chess() {
        int i = 1;
        for (int x = 0; x < width; x++) {
            i++;
            for (int y = 0; y < height; y++) {
//                pixels2D[x][y] = 0xff00ff;
                if (i % 2 == 0) {
                    pixels2D[x][y] = 100;
                }
                i++;

            }

        }

    }

    private int[] convert(int[][] pixels2D) {
        int[] p = new int[width * height];
        for (int x = 0; x < pixels2D.length; x++) {
            for (int y = 0; y < pixels2D[0].length; y++) {
                p[y * width + x] = pixels2D[x][y];
            }
        }
        return p;
    }

    private void renderCircle(Position pos, int radius) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int dist_x = pos.getX() - x;
                int dist_y = pos.getY() - y;
                if (Math.sqrt((Math.pow(dist_x, 2)) + (Math.pow(dist_y, 2))) <= radius) {
                    pixels[width * y + x] = 0xff00ff;
                }
//                if ((dist_x + dist_y) == radius) {
//                    pixels[width * y + x] = 0xff00ff;
//                }

            }
        }
    }

    private void renderFood(Position pos, int radius) {
        renderSquare(pos,radius,radius, Constants.FOOD_COLOR);

//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                int dist_x = pos.getX() - x;
//                int dist_y = pos.getY() - y;
//                if (Math.sqrt((Math.pow(dist_x, 2)) + (Math.pow(dist_y, 2))) <= radius) {
//                    pixels[width * y + x] = Constants.FOOD_COLOR;
//                }
////                if ((dist_x + dist_y) == radius) {
////                    pixels[width * y + x] = 0xff00ff;
////                }
//
//            }
//        }
    }

    private void renderSquare(Position p, int width, int height, int color) {
        for (int x = p.getX(); x < this.width; x++) {
            for (int y = p.getY(); y < this.height; y++) {
                if (x <= p.getX() + width && y <= p.getY() + height) {
                    pixels2D[x][y] = color;
                }
            }
        }

    }

    public void clear() {
        Arrays.fill(pixels, 0);
        for (int[] row : pixels2D)
            Arrays.fill(row, 0);
    }
}
