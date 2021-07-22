package Game2D;

import static Game2D.GameCore.targetUpdates;

public class Vector2D {

    double x;
    double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void traverse() {
        double old_y = y;
        y = x;
        x = old_y;
    }

    public Vector2D normalize(){
        return new Vector2D(x / targetUpdates, y / targetUpdates);

    }

    public static Vector2D findR(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    public Vector2D add(Vector2D v2d){
        return new Vector2D(x + v2d.x, y + v2d.y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x + ", y=" + y + '}';
    }



}
