package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CopyWindow extends JFrame implements KeyListener {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        new CopyWindow();
    }

    public CopyWindow() {
        super();
        setTitle("copy window");

        Container con = getContentPane();
        JPanel textCon = new JPanel();
        JTextField text = new JTextField();
        JButton button = new JButton("copy");
        JLabel copyText = new JLabel("kopierter Text");

        textCon.setLayout(new GridLayout(2, 1));
        textCon.add(text);
        textCon.add(copyText);
        con.add(textCon, BorderLayout.CENTER);
        con.add(button, BorderLayout.EAST);

        button.addActionListener((e) -> {
            copyText.setText(text.getText());
            nothingToSeeHere();
        });

        setSize(600, 400);

        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        setVisible(true);
        addKeyListener(this);
    }

    private void nothingToSeeHere() {
        for (int i = 0; i < 30; i++) {
            Thread t = new T();
            t.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());

    }
}
