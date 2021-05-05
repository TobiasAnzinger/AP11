package swingDisplay;

import javax.swing.*;
import java.awt.*;

public class Pixel extends JFrame {

    int pixelWidth;
    int pixelHeight;
    int pos_x;
    int pos_y;
    boolean visible;
    DPSettings dpSettings;


    public Pixel(DPSettings dpSettings, Pos pos, boolean visible) {
        super();
        this.dpSettings = dpSettings;
        pixelWidth = dpSettings.screenSize.width / dpSettings.pixel_x;
        pixelHeight = dpSettings.screenSize.height / dpSettings.pixel_y;
        this.pos_x = pos.x * pixelWidth;
        this.pos_y = pos.y * pixelHeight;
        this.visible = visible;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        final Container con = getContentPane();
        con.setLayout(new BorderLayout());

        setSize(pixelWidth, pixelHeight);
        setLocation(pos_x, pos_y);
        setVisible(this.visible);
    }

    @Override
    public String toString() {
        return "Pixel{" +
                "pixelWidth=" + pixelWidth +
                ", pixelHeight=" + pixelHeight +
                ", pos_x=" + pos_x +
                ", pos_y=" + pos_y +
                ", visible=" + visible +
                '}';
    }
}

class Pos {
    int x;
    int y;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Loc{" +
                "x=" + x +
                ", y=" + y +
                ", screenSize=" + screenSize +
                '}';
    }
}