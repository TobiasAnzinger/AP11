package swing;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Thread.sleep;

public class BouncyWindow extends JFrame {
    int nr;
    Loc loc = new Loc();
    MV mv = new MV();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Clip clip;

    public BouncyWindow() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
        super();
        init();

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                try {
                    exit();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        final Container con = getContentPane();
        con.setLayout(new BorderLayout());


        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sound.wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControll = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControll.setValue(-30.00f);
    }

    private void init() {
        nr++;
        setTitle("" + nr);
        setSize(400, 150);
        loc.x = (screenSize.width - getWidth()) / 2;
        loc.y = (screenSize.height - getHeight()) / 2;
        setLocation(loc.x, loc.y);
        loc.x = ThreadLocalRandom.current().nextInt(0, screenSize.width - getWidth());
        loc.y = ThreadLocalRandom.current().nextInt(0, screenSize.height - getHeight());
        setVisible(true);
    }

    private void exit() throws InterruptedException {
        try {
            Main.main(null);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
//            System.exit(0);
    }

    public void moveWindow(BouncyWindow ew, MV mv) throws InterruptedException {
        ew.mv = mv;
        while (true) {
            ew.detectHit(ew);
            ew.updateLoc(ew);
            ew.loc.x += ew.mv.x;
            ew.loc.y += ew.mv.y;
            ew.setLocation(loc.x, loc.y);
            sleep(20);
        }
    }

    private void onHit(BouncyWindow ew) {
        int r = ThreadLocalRandom.current().nextInt(0, 255);
        int g = ThreadLocalRandom.current().nextInt(0, 255);
        int b = ThreadLocalRandom.current().nextInt(0, 255);
        this.getContentPane().setBackground(new Color(r, g, b));
        clip.start();
    }

    private void detectHit(BouncyWindow ew) {
        if (ew.loc.x + ew.mv.x <= 0 || ew.loc.x + ew.mv.x >= screenSize.width - getWidth()) {
            ew.mv.x *= -1;
            onHit(ew);
        }

        if (ew.loc.y + ew.mv.y <= 0 || ew.loc.y + ew.mv.y >= screenSize.height - getHeight()) {
            ew.mv.y *= -1;
            onHit(ew);
        }
    }

    private void updateLoc() {
        loc.x = getLocation().x;
        loc.y = getLocation().y;
    }

    private void updateLoc(BouncyWindow ew) {
        ew.loc.x = getLocation().x;
        ew.loc.y = getLocation().y;
    }

}

class MV {
    int x;
    int y;
    int speed = 50;

    public MV() {
        x = generate() * ThreadLocalRandom.current().nextInt(1, speed);
        y = generate() * ThreadLocalRandom.current().nextInt(1, speed);
    }

    int generate() {
        int i = ThreadLocalRandom.current().nextInt(0, 2);
        if (i == 0) {
            i = -1;
        }
        return i;
    }

    @Override
    public String toString() {
        return "MV{" +
                "x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                '}';
    }
}

class Loc {
    int x;
    int y;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Loc() {
        x = ThreadLocalRandom.current().nextInt(1, screenSize.width);
        y = ThreadLocalRandom.current().nextInt(1, screenSize.height);
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

class MP {

    int x;
    int y;
    PointerInfo a = MouseInfo.getPointerInfo();

    public void update() {
        Point b = a.getLocation();
        x = (int) b.getX();
        y = (int) b.getY();
    }
}
