package GameEngine.snakeGame;

import GameEngine.Constants;
//import com.sun.javafx.scene.traversal.Direction;

import java.util.ArrayList;

public class Snake {

    private boolean didGrow = false;

    public Snake(Position p) {
        addPart(new SnakeHead(p, Constants.SNAKEHEAD_SIZE, Dir.UP));
    }

    public ArrayList<SnakePart> getElements() {
        return elements;
    }


    private ArrayList<SnakePart> elements = new ArrayList<SnakePart>();

    private void addPart(SnakePart snakePart) {
        elements.add(snakePart);
    }

    public void grow() {
        didGrow = true;
    }

    public void moveDir(Dir dir) {
        if (didGrow) {
            addPart(new SnakePart(elements.get(elements.size() - 1).getPos(), Constants.SNAKEHEAD_SIZE));
            for (int e = elements.size() - 1; 1 <= e; e--) {
                elements.get(e).setPos(elements.get(e - 1).getPos());
            }
            didGrow = false;
        } else if (elements.size() > 1) {
            for (int e = elements.size() - 1; 1 <= e; e--) {
                elements.get(e).setPos(elements.get(e - 1).getPos());
            }
        }

        switch (dir) {
            case UP:
                moveUP();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLEFT();
                break;
            case RIGHT:
                moveRIGHT();
                break;
        }
        elements.forEach(snakePart -> {
            int headPosX = elements.get(0).getPos().getX();
            int headPosY = elements.get(0).getPos().getY();
            if(elements.get(0) != snakePart){
                if(snakePart.getPos().getX() == headPosX && snakePart.getPos().getY() == headPosY){
                    GameEngine.Snake.gameOver();
                }
            }
        });

    }


    private void moveUP() {
        elements.get(0).setPos(new Position(elements.get(0).getPos().getX(), elements.get(0).getPos().getY() - Constants.MOVEMENTSPEED));
    }

    private void moveDown() {
        elements.get(0).setPos(new Position(elements.get(0).getPos().getX(), elements.get(0).getPos().getY() + Constants.MOVEMENTSPEED));
    }

    private void moveLEFT() {
        elements.get(0).setPos(new Position(elements.get(0).getPos().getX() - Constants.MOVEMENTSPEED, elements.get(0).getPos().getY()));
    }

    private void moveRIGHT() {
        elements.get(0).setPos(new Position(elements.get(0).getPos().getX() + Constants.MOVEMENTSPEED, elements.get(0).getPos().getY()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Snake:");
        elements.forEach(snakePart -> sb.append(snakePart.getPos().toString()));
        return sb.toString();
    }

}
