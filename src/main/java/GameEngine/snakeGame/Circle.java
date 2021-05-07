package GameEngine.snakeGame;

public class Circle {

    private Position pos;
    private int radius;

    public Circle(Position pos, int radius) {
        this.pos = pos;
        this.radius = radius;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
