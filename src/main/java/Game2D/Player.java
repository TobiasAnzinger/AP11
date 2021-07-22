package Game2D;


import java.awt.*;

public class Player extends GameEntity {

    public Player(Vector2D position, String tag, Vector2D size) {
        super.GameEntity(position, tag, size);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }

    @Override
    public void renderEntity() {
        Graphics2D g2d = entity.createGraphics();
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
    }

    @Override
    public void onCollision() {
        super.onCollision();
    }

}
