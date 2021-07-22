package Game2D;

public class Camera {



    public Vector2D cameraPosition = new Vector2D(0,0);


    public Camera() {
    }

    public void moveCamera(Vector2D dir){
        cameraPosition = new Vector2D(cameraPosition.x + dir.x, cameraPosition.y + dir.y);
    }

    public Vector2D getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(Vector2D cameraPosition) {
        this.cameraPosition = cameraPosition;
    }
}
