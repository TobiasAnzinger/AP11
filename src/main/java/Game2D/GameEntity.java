package Game2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameEntity {

    String tag;
    Vector2D velocity = new Vector2D(0, 0);
    Vector2D position = null;
    Vector2D size;


    boolean grounded = false;

    int width;
    int height;

    BufferedImage entity;

    public void GameEntity(Vector2D position, String tag, Vector2D size) {
        this.position = position;
        this.tag = tag;
        this.size = size;
        this.width = GameCore.scale * (int) size.x;
        this.height = GameCore.scale * (int) size.y;
        this.entity = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    }

    public void updateEntity() {
        renderEntity();
    }

    public void renderEntity() {
        Graphics2D g2d = entity.createGraphics();

//        insert entity rendering here
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
    }

    public void onCollision() {

    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }
}


