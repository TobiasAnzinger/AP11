package Game2D;

import javax.swing.text.html.parser.Entity;

import static Game2D.GameCore.camera;
import static Game2D.GameCore.screenSize;

public class Camera {


    private Vector2D cameraPosition = new Vector2D(0, 0);

    private Vector2D cameraTargetPosition = new Vector2D(0, 0);

    private Vector2D cameraOffset;

    private Vector2D finalCameraRenderPosition = new Vector2D(0, 0);



    public Camera(Vector2D cameraOffset) {
        this.cameraOffset = cameraOffset;
    }

    public void updateCameraPosition(GameEntity target) {
        cameraTargetPosition = target.position;
        setCameraPosition((Vector2D.findR(cameraTargetPosition, cameraPosition).normalize()).add(cameraOffset));

        moveCamera(Vector2D.findR(target.position, camera.getCameraPosition()).normalize());
//        moveCamera(Vector2D.findR(target.position, camera.getCameraPosition()));

        finalCameraRenderPosition = cameraPosition.add(cameraOffset);

        if (finalCameraRenderPosition.x <= 0) {
            finalCameraRenderPosition.x = 0;
        } else if (finalCameraRenderPosition.x  >= (GameCore.level.width - (GameCore.screenSize.width))) {
            finalCameraRenderPosition.x = GameCore.level.width - GameCore.screenSize.width;
        }

        if (finalCameraRenderPosition.y + cameraOffset.y <= 0) {
            finalCameraRenderPosition.y = 0;
        } else if (finalCameraRenderPosition.y >= (GameCore.level.height - GameCore.screenSize.height)) {
            finalCameraRenderPosition.y = GameCore.level.height - GameCore.screenSize.height;
        }

//        System.out.println("ct: " + cameraTargetPosition);
//        System.out.println("cp: " + cameraPosition);
        System.out.println("fp: " + finalCameraRenderPosition);
    }



    private void moveCamera(Vector2D dir) {
        cameraPosition = new Vector2D((cameraTargetPosition.x + dir.x),
                (cameraTargetPosition.y + dir.y));
    }

    public Vector2D getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(Vector2D cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    public Vector2D getCameraOffset() {
        return cameraOffset;
    }

    public Vector2D getFinalCameraRenderPosition() {
        return finalCameraRenderPosition;
    }


    @Override
    public String toString() {
        return "Camera{" +
                ", cameraPosition=" + cameraPosition +
                ", cameraTargetPosition=" + cameraTargetPosition +
                ", cameraOffset=" + cameraOffset +
                '}';
    }


}
