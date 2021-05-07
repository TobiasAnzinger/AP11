package GameEngine;


import GameEngine.snakeGame.Dir;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
//        System.out.println(keycode);
        switch (keycode) {
            case (KeyEvent.VK_SPACE):
//                System.out.println("space pressed");

                break;
            case (KeyEvent.VK_W):
                Game.moveDir = Dir.UP;
                break;
            case (KeyEvent.VK_A):
                Game.moveDir = Dir.LEFT;
                break;
            case (KeyEvent.VK_S):
                Game.moveDir = Dir.DOWN;
                break;
            case (KeyEvent.VK_D):
                Game.moveDir = Dir.RIGHT;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
