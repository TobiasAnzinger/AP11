package Game2D;

import GameEngine.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;


public class GameCore extends Canvas implements Runnable {
//    private static final long serialVersionUID = 1L;


    //    public static int width = 1920;
    public static int levelWidth = 100;
    //    public static int height = width / 16 * 9;
    public static int levelHeight = 100;
    public static final int scale = 8;


    public static double gravity = 9.81 * scale;
    public static double movementSpeed = 70 * scale;
    public static double jumpSpeed = 3 * scale;
    //  1.0 is zero drag rise if you want to add drag
    public static double horizontalDrag = 1.0;
    public static double verticalDrag = 1.0;

    public static int targetFPS = 120;
    public static int targetUpdates = 60;
    public static double fpsNormalisation = 1.1;

    private Thread thread;
    private boolean running = false;
    private final JFrame frame;
    private int fps = 0;
    private int updates = 0;

    public static Player player;
    public static Camera camera;
    public static GameState gameState;
    public static GameCore game;


    public GameCore() {
        super();
        Dimension size = new Dimension(levelWidth * scale, levelHeight * scale);
        setPreferredSize(size);
        frame = new GameFrame();

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

    public static void main(String[] args) {
        game = new GameCore();
        game.frame.setResizable(false);
        game.frame.setTitle(Constants.GAME_Title);
        game.frame.add(game);
        game.frame.requestFocus();
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        center window on screen
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
//        game.frame.addKeyListener(new Game2D.InputHandler());
        game.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.nanoTime();
        final double nanoseconds = 1000000000.0 / targetUpdates;
        double diff = 0;
        int fps = 0;
        long now;
        int updates = 0;

        init();
        while (running) {
            game.frame.requestFocus();
            now = System.nanoTime();
            diff += (now - lastTime) / nanoseconds;
            lastTime = now;

//            calls the update method every 1/ targetupdates
            while (diff >= 1) {
                update(diff);
                updates++;
                diff--;
            }

//            limit fps
            if (System.nanoTime() - timer > (1000000000 / targetFPS)) {
                render();
                fps++;
            }

            if (System.nanoTime() - timer > 1000000000) {
                timer += 1000000000;
                this.updates = updates;
                this.fps = fps;
                fps = 0;
                updates = 0;
            }

            try {
                long delta = (((1000000000) / targetFPS) - (System.nanoTime() - now));
                delta = (long) (delta / fpsNormalisation);
                int millis = (int) delta / 1000000;
                int nanos = (int) delta % 1000000;
                if (delta > 0) {
                    thread.sleep(millis, nanos);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        stop();
    }

    private void update(double diff) {

        player.updateEntity();
        Movement.movePlayer();

        Vector2D cameraMovementV2D = Vector2D.findR(player.getPosition(), camera.getCameraPosition()).normalize();
        System.out.println(cameraMovementV2D);
        camera.moveCamera(cameraMovementV2D);

//        do ~ every game tick
//        if (diff > 0.9x9) {
//        do something every Game tick
//        }

    }

    public static void gameOver() {
        JOptionPane.showMessageDialog(new JFrame(), "GAME OVER!");
        System.out.println("game Over");
    }

    private void render() {
//        TODO render in second thread
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

//        render background
//        g2d.drawImage(GameState.currentLevel, 0, 0, width * scale, height * scale, null);
        g2d.drawImage(GameState.currentLevel, 0, 0, levelWidth * scale, levelHeight * scale, 0, 0, 100, 100, null);
//        g2d.drawImage(GameState.currentLevel, 0, 0, null);

//        render player
        g2d.drawImage(player.entity, (int) player.getPosition().x, (int) player.getPosition().y, null);

//        render UI

//        render camera viewpoint
        g2d.fillOval((int) camera.getCameraPosition().x, (int) camera.cameraPosition.y, 10, 10);

//        render fps counter
        g2d.setColor(Color.BLACK);
        g2d.drawString("FPS: " + fps + "      UPDATES: " + updates, 5, 15);
        g2d.dispose();
        bs.show();
    }

    private void loadAssets() {
        try {
//            GameState.currentLevel = ImageIO.read(new File("src/main/java/Game/assets/yeetus.png"));
            GameState.currentLevel = ImageIO.read(new File("src/main/java/Game2D/assets/yeeeeeeeeeeeee.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        gameState = new GameState();
        loadAssets();
        camera = new Camera();
        player = new Player(new Vector2D(45 * scale, 0), "Player", new Vector2D(4, 8));
    }


//    @Override
//    public void keyTyped(KeyEvent e) {
//        System.out.println("key typed" + e.getKeyCode());
////        switch (e.getKeyCode()){
////            case KeyEvent.VK_A:
////                break;
////
////            case KeyEvent.VK_D:
////                break;
////
////            case KeyEvent.VK_SPACE:
////                break;
////
////            default:
////                break;
////        }
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        System.out.println("key " + e.getKeyCode() + "was pressed");
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_A:
//                keyPressed_A = true;
//                break;
//
//            case KeyEvent.VK_D:
//                keyPressed_D = true;
//                break;
//
//            case KeyEvent.VK_SPACE:
//                keyPressed_SPACE = true;
//                break;
//
//            default:
//                break;
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_A:
//                keyPressed_A = false;
//                break;
//
//            case KeyEvent.VK_D:
//                keyPressed_D = false;
//                break;
//
//            case KeyEvent.VK_SPACE:
//                keyPressed_SPACE = false;
//                break;
//
//            default:
//                break;
//        }
//
//    }
}
