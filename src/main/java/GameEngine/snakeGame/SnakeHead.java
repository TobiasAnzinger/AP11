package GameEngine.snakeGame;

import com.sun.javafx.scene.traversal.Direction;

public class SnakeHead extends SnakePart{

    Direction direction;

    public SnakeHead(Position pos, int radius, Direction direction) {
        super(pos, radius);
    }

}
