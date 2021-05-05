package BMI;

import swingDisplay.Display;
import swingDisplay.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class BMI extends JFrame {
    Pixel[][] display = Display.generate(" ");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public BMI() throws HeadlessException {
        setTitle("BMI Calculator");
        Container con = getContentPane();
        JPanel panel = new JPanel();
        JTextField weight = new JTextField();
        JTextField height = new JTextField();
        JButton calculateButton = new JButton("calculate");
        calculateButton.setSize(1, 200);
        calculateButton.setPreferredSize(new Dimension(0, 100));
        System.out.println(calculateButton.getSize());
        JLabel enterHeightText = new JLabel("enter height in cm");
        JLabel enterWeightText = new JLabel("enter weight in kg");
        panel.setLayout(new GridLayout(2, 2));
        panel.add(enterWeightText, BorderLayout.CENTER);
        panel.add(weight);
        panel.add(enterHeightText, BorderLayout.CENTER);
        panel.add(height);
        con.add(panel, BorderLayout.CENTER);
        con.add(calculateButton, BorderLayout.SOUTH);

        calculateButton.addActionListener((e) -> {
            handle(this.display, weight, height);
        });

        setSize(screenSize.width / 4, screenSize.height / 4);
        setLocation((screenSize.width - getWidth()) / 2, ((screenSize.height - getHeight()) / 2) + screenSize.height / 6);

        setVisible(true);
    }

    public void windowClosed(WindowEvent e) throws InterruptedException {
        System.exit(0);
        exit();
    }

    private void handle(Pixel[][] display, JTextField weight, JTextField height) {
        assert display != null;
        Display.clear(display);
        try {
            int w = Integer.parseInt(weight.getText());
            int h = Integer.parseInt(height.getText());
            float bmi = calculateBMI(w, h);
            this.display = Display.generate(Float.toString(bmi));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid numbers");
        }
    }

    private void exit() throws InterruptedException {
        System.exit(0);
        assert display != null;
        Display.clear(display);
    }

    private int calculateBMI(int weight, int height) {
        float h = (float) height / 100;
        System.out.println("height: " + h);
        return (int) (weight / (h * h));
    }

}
