package GameEngine;


import GameEngine.snakeGame.Dir;
import GameEngine.snakeGame.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
//    private static final long serialVersionUID = 1L;

    //    public static int width = 1920;
    public static int width = 20;
    //    public static int height = width / 16 * 9;
    public static int height = width;
    public static final int scale = 50;
    public static Dir moveDir;
    private boolean running = false;

    //        private BufferedImage image = new BufferedImage((int) (width * RENDER_RESOLUTION), (int) (height * RENDER_RESOLUTION), BufferedImage.TYPE_INT_RGB);
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private Thread thread;

    private Screen screen;
    private final JFrame frame;

    private int fps = 0;
    private int updates = 0;
    public static Position foodPos;

    public Game() {
//        Dimension size = new Dimension(width * scale, height * scale);
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        screen = new Screen(width, height);
        frame = new JFrame();
        moveDir = Dir.UP;
        setNewFood();
        addKeyListener(new InputHandler());
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, Constants.GAME_Title);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nanoseconds = 1000000000.0 / Constants.GAME_UPDATES;
        double diff = 0;

        int fps = 0;
        long now;


        int updates = 0;

        while (running) {
            now = System.nanoTime();
            diff += (now - lastTime) / nanoseconds;
            lastTime = now;
//            calls the update method every 1/ Constants.GAME_UPDATES
            while (diff >= 1) {
                update(diff);
                updates++;
                diff--;
            }
            render();
            fps++;
//            every 1 second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                this.updates = updates;
                this.fps = fps;
                fps = 0;
                updates = 0;
            }
        }
        stop();
    }


    private void update(double diff) {
        teleportSnakeToOtherSide();
        if (screen.snake.getElements().get(0).getPos().getX() == foodPos.getX() && screen.snake.getElements().get(0).getPos().getY() == foodPos.getY()) {
            screen.snake.grow();
            setNewFood();
        }
//        move ~ every game tick
        if (diff > 0.99) {
            screen.snake.moveDir(moveDir);
        }

    }

    private void setNewFood() {
        Game.foodPos = new Position((int) (Math.random() * width), (int) (Math.random() * height) );
        screen.snake.getElements().forEach(snakePart -> {
            while (snakePart.getPos().getX() == Game.foodPos.getX() && snakePart.getPos().getY() == Game.foodPos.getY()) {
                Game.foodPos = new Position((int) (Math.random() * width * Constants.SNAKEHEAD_SIZE), (int) (Math.random() * height));
            }
        });
    }

    private void teleportSnakeToOtherSide() {

        int pos_x = screen.snake.getElements().get(0).getPos().getX();
        int pos_y = screen.snake.getElements().get(0).getPos().getY();
        if (pos_x > width + Constants.SNAKEHEAD_SIZE) {
            if (Constants.CHEATS_ENABLED) {
                screen.snake.getElements().get(0).setPos(new Position(-Constants.SNAKEHEAD_SIZE / 2, pos_y));
            } else {
                gameOver();
            }
        } else if (pos_x < -Constants.SNAKEHEAD_SIZE) {
            if (Constants.CHEATS_ENABLED) {
                screen.snake.getElements().get(0).setPos(new Position(width + Constants.SNAKEHEAD_SIZE / 2, pos_y));
            } else {
                gameOver();
            }
        }
        if (pos_y > height + Constants.SNAKEHEAD_SIZE) {
            if (Constants.CHEATS_ENABLED) {

                screen.snake.getElements().get(0).setPos(new Position(pos_x, -Constants.SNAKEHEAD_SIZE / 2));
            } else {
                gameOver();
            }
        } else if (pos_y < -Constants.SNAKEHEAD_SIZE) {
            if (Constants.CHEATS_ENABLED) {

                screen.snake.getElements().get(0).setPos(new Position(pos_x, height + Constants.SNAKEHEAD_SIZE / 2));
            } else {
                gameOver();
            }
        }
    }


    public static void gameOver() {
        JOptionPane.showMessageDialog(new JFrame(), "GAME OVER!");
        System.out.println("game Over");
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        screen.render();

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
//        for (int i = 0; i < pixels.length; i++) {
//
//            pixels[i] = screen.pixels[i];
//        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//        g.setColor(Color.BLACK);
//        g.drawRect(0,0,getWidth(),getHeight());
        g.drawString("FPS: " + fps + "      UPDATES: " + updates, 5, 15);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Constants.GAME_Title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        center window on screen
        game.frame.setLocationRelativeTo(null);

        game.frame.setVisible(true);
//        game.addKeyListener(new InputHandler());
        game.start();
    }

}
