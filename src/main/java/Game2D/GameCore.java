package Game2D;

import GameEngine.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static Game2D.Movement.movePlayer;


public class GameCore extends Canvas implements Runnable {
//    private static final long serialVersionUID = 1L;


    public static Dimension screenSize = new Dimension(100, 100);
    public static Vector2D cameraOffset = new Vector2D(-screenSize.width / 2, -screenSize.height / 2);

    public static final int scale = 8;
    public static Dimension windowSize = new Dimension(screenSize.width * scale, screenSize.height * scale);


    public static double gravity = 9.81;
    public static double movementSpeed = 60;
    public static double jumpPower = 3;
    //  1.0 is zero drag rise if you want to add drag
    public static double horizontalDrag = 1.0;
    public static double verticalDrag = 1.0;

    public static int targetFPS = 60;
    public static int targetUpdates = 20;
    public static double fpsNormalisation = 1.1;

    private Thread thread;
    private boolean running = false;
    private final JFrame frame;
    private int fps = 0;
    private int updates = 0;

    public static Player player;
    public static Camera camera;
    public static Level level;
    public static GameState gameState;
    public static GameCore game;

    private Vector2D playerRenderPosition;

    public GameCore() {
        super();
        setPreferredSize(windowSize);
        frame = new GameFrame();

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

    private void init() {
        gameState = new GameState();
        level = new Level("src/main/java/Game2D/assets/yeeeeeeeeeeeee.png");

        camera = new Camera(cameraOffset);
        player = new Player(new Vector2D(0, 0), "Player", new Vector2D(4, 8));
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
        movePlayer();
        camera.updateCameraPosition(player);
        calculatePlayerPositionOnScreen();

//        do ~ every game tick
//        if (diff > 0.99) {
//        do something every Game tick
//        }

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

        g2d.drawImage(level.image, 0, 0,
                windowSize.width,
                windowSize.height,
                (int) camera.getFinalCameraRenderPosition().x,
                (int) camera.getFinalCameraRenderPosition().y,
                (int) camera.getFinalCameraRenderPosition().x + screenSize.width,
                (int) camera.getFinalCameraRenderPosition().y + screenSize.height, null);


//        render player
//        g2d.drawImage(player.entity, (int) player.getPosition().x * scale, (int) player.getPosition().y * scale, null);
        g2d.drawImage(player.entity, (int) playerRenderPosition.x * scale, (int) playerRenderPosition.y * scale, null);

//        render UI

//        render camera viewpoint
//        g2d.fillOval((int) camera.getCameraPosition().x, (int) camera.getCameraPosition().y, 10, 10);

//        render fps counter
        g2d.setColor(Color.BLACK);
        g2d.drawString("FPS: " + fps + "      UPDATES: " + updates, 5, 15);
        g2d.dispose();
        bs.show();
    }

    private void calculatePlayerPositionOnScreen() {
        Vector2D playerOnScreen = player.getPosition();

//      TODO fix teleportation bug which occurs when moving right to the right edge of the level and the screen width
//          is smaller than the level width

//        if (camera.getFinalCameraRenderPosition().x <= - cameraOffset.x) {
//            playerOnScreen.x = player.getPosition().x;
        if (camera.getFinalCameraRenderPosition().x > -cameraOffset.x && camera.getFinalCameraRenderPosition().x < level.width + cameraOffset.x) {
            playerOnScreen.x = -cameraOffset.x;
            level.setMovingX(true);
        } else if (camera.getFinalCameraRenderPosition().x > level.width + screenSize.width) {
//            todo fix
            playerOnScreen.x = player.getPosition().x - level.width + (screenSize.width - player.size.x);
//            playerOnScreen.x = player.getPosition().x - level.width + screenSize.width;
        }

        if (camera.getFinalCameraRenderPosition().y >= level.height - screenSize.height) {
//            playerOnScreen.y = player.getPosition().y;
            System.out.println("b");
        } else if (camera.getFinalCameraRenderPosition().y < screenSize.height) {
            playerOnScreen.y = player.getPosition().y;
            System.out.println();
            System.out.println("a");
        } else {
            System.out.println("c");
            playerOnScreen.y = -cameraOffset.y;
            level.setMovingY(true);
        }


        playerRenderPosition = playerOnScreen;
        System.out.println("pp: " + player.getPosition());
        System.out.println("prp: " + playerRenderPosition);
    }

    public static void gameOver() {
        JOptionPane.showMessageDialog(new JFrame(), "GAME OVER!");
        System.out.println("game Over");
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
