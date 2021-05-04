package swing;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CopyWindow extends JFrame {

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
    }

    private void nothingToSeeHere() {
        for (int i = 0; i < 30; i++) {
            Thread t = new T();
            t.start();
        }
    }
}
