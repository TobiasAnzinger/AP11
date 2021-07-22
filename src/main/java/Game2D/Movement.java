package Game2D;


import static Game2D.GameCore.*;
import static Game2D.GameFrame.*;

public class Movement {

//    public static int bottomOfScreen = (int) ((screenSize.height - player.size.y) * scale);
    public static int bottomOfScreen = (int) (level.height - player.size.y);
//    public static int rightWall = (int) ((screenSize.width - player.size.x) * scale);
    public static int rightWall = (int) (level.width - player.size.x);


//    TODO fix jump / gravity. Its not uniformly with different update rates

//    TODO fix the coordinate system. Its still bound to the screen and not the map

    public static void movePlayer() {
        player.velocity.x = 0;
//        System.out.println(player.position.toString() + " delta V " + player.velocity.toString());
        if (player.position.y >= bottomOfScreen) {
            player.grounded = true;
        } else {
            player.grounded = false;
        }

//        add drag
        player.setVelocity(new Vector2D(player.getVelocity().x / horizontalDrag, player.getVelocity().y / verticalDrag));

//        move right
        if (keyPressed_D) {
            player.setVelocity(new Vector2D((movementSpeed), player.getVelocity().y));
//            System.out.println("moving right " + player.velocity.toString());
        }

//        move left
        if (keyPressed_A) {
            player.setVelocity(new Vector2D((-movementSpeed), player.getVelocity().y));
//            System.out.println("move left " + player.velocity.toString());
        }

//        calculate gravity
        if (player.velocity.y < gravity) {
            player.setVelocity(new Vector2D(player.getVelocity().x, player.getVelocity().y + normalize(gravity)));
        }

//        jump
        if (keyPressed_SPACE && player.grounded) {
            player.setVelocity(new Vector2D(player.getVelocity().x, -jumpPower));
        }

//        move if still on screen after movement
        double afterMovingX = player.getPosition().x + normalize(player.getVelocity().x);

        double afterMovingY = player.getPosition().y + player.getVelocity().y;

//        boolean allowMovement = bottomOfScreen >= afterMoving.y && afterMoving.y >= 0 &&
//                0 <= afterMoving.x && afterMoving.x < rightWall;

        boolean allowMovementX = 0 <= afterMovingX && afterMovingX < rightWall;

        boolean allowMovementY = afterMovingY >= 0;

        if (allowMovementX) player.setPosition(new Vector2D(afterMovingX, player.getPosition().y));
        if (allowMovementY) player.setPosition(new Vector2D(player.getPosition().x, afterMovingY));

        if (player.position.y > bottomOfScreen) {
            player.setPosition(new Vector2D(player.getPosition().x, bottomOfScreen));
        }
    }


    private static double normalize(double v) {
        return v / targetUpdates;
    }

}
