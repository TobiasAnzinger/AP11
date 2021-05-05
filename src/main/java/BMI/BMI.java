package BMI;

import swingDisplay.Display;

import javax.swing.*;
import java.awt.*;

public class BMI extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public BMI() throws HeadlessException {

        setTitle("BMI Rechner");

        Container con = getContentPane();
        JPanel panel = new JPanel();
        JTextField weight = new JTextField();
        JTextField height = new JTextField();
        JButton calculateButton = new JButton("berechen");
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

//        panel.add(copyText);
        con.add(panel, BorderLayout.CENTER);
        con.add(calculateButton, BorderLayout.SOUTH);

        calculateButton.addActionListener((e) -> {
            int w = Integer.parseInt(weight.getText());
            int h = Integer.parseInt(height.getText());
            float bmi = calculateBMI(w, h);
            Display.generate(Float.toString(bmi));
        });

        setSize(800, 400);
        setLocation((screenSize.width - getWidth()) / 2, ((screenSize.height - getHeight()) / 2) + screenSize.height / 6);

        setVisible(true);
    }

    private int calculateBMI(int weight, int height) {
        float h = (float) height / 100;
        return (int) (weight / (h * h));
    }


}
