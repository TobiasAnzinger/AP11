package swing;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;
import static swing.Main.clip;

public class EasyWindow extends JFrame {
    int nr;
    Loc loc = new Loc();
    MV mv = new MV();
//    MP mp = new MP();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Clip clip;


    public EasyWindow() throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
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
//        JLabel label = new JLabel("asdasfda", JLabel.CENTER);
//        final JButton red = new JButton("rot");
//        final JButton yellow = new JButton("gelb");
//        final JButton exit = new JButton("Ende");

//        con.add(label, BorderLayout.CENTER);
//        con.add(red, BorderLayout.WEST);
//        con.add(yellow, BorderLayout.EAST);
//        con.add(exit, BorderLayout.SOUTH);

//        exit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    exit();
//                } catch (InterruptedException interruptedException) {
//                    interruptedException.printStackTrace();
//                }
//            }
//        });
//        red.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        yellow.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
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
//            System.exit(0);
//            int x = getLocation().x;
//            int y = getLocation().y;
//            setLocation(x + (int) (Math.random() * 50), y + (int) (Math.random() * 50));
        Main.main(null);
    }

    public void moveWindow(EasyWindow ew, MV mv) throws InterruptedException {
        ew.mv = mv;
        while (true) {
//            System.out.println(ew.loc);
//            System.out.println(ew.mv);
            ew.detectHit(ew);
            ew.updateLoc(ew);
            ew.loc.x += ew.mv.x;
            ew.loc.y += ew.mv.y;
            ew.setLocation(loc.x, loc.y);
            sleep(20);
        }
    }

    private void onHit(EasyWindow ew) {
        int r = ThreadLocalRandom.current().nextInt(0, 255);
        int g = ThreadLocalRandom.current().nextInt(0, 255);
        int b = ThreadLocalRandom.current().nextInt(0, 255);
        this.getContentPane().setBackground(new Color(r, g, b));
        clip.start();
    }

    private void detectHit(EasyWindow ew) {
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

    private void updateLoc(EasyWindow ew) {
        ew.loc.x = getLocation().x;
        ew.loc.y = getLocation().y;
    }

}

class T extends java.lang.Thread {

    public void run() {
        try {
            EasyWindow ew = new EasyWindow();
            ew.moveWindow(ew, new MV());
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
