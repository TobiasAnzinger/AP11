package Game2D;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {

    public static boolean keyPressed_A = false;
    public static boolean keyPressed_D = false;
    public static boolean keyPressed_SPACE = false;

    public GameFrame() {
        super();
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("key typed" + e.getKeyCode());
//        switch (e.getKeyCode()){
//            case KeyEvent.VK_A:
//                break;
//
//            case KeyEvent.VK_D:
//                break;
//
//            case KeyEvent.VK_SPACE:
//                break;
//
//            default:
//                break;
//        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("key " + e.getKeyCode() + "was pressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                keyPressed_A = true;
                break;

            case KeyEvent.VK_D:
                keyPressed_D = true;
                break;

            case KeyEvent.VK_SPACE:
                keyPressed_SPACE = true;
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("key " + e.getKeyCode() + "was released");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                keyPressed_A = false;
                break;

            case KeyEvent.VK_D:
                keyPressed_D = false;
                break;

            case KeyEvent.VK_SPACE:
                keyPressed_SPACE = false;
                break;

            default:
                break;
        }

    }
}
