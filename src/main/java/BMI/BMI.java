package BMI;

import swingDisplay.Display;
import swingDisplay.Pixel;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

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

    private void handle(Pixel[][] display, JTextField weight, JTextField height) {
        assert display != null;
        Display.clear(display);
        try {
            int w = Integer.parseInt(weight.getText());
            int h = Integer.parseInt(height.getText());
            float bmi = calculateBMI(w, h);
            DecimalFormat df = new DecimalFormat("#.#");
            this.display = Display.generate(df.format(bmi));
            System.out.println(bmi);
            showResult(bmi);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid numbers");
        }
    }

    private void showResult(float bmi) {
        String value = null;
        if (bmi < 15) value = "Very severely underweight";
        if (bmi >= 15) value = "Severely underweight";
        if (bmi >= 16) value = "Underweight";
        if (bmi >= 18.5) value = "Normal";
        if (bmi >= 25) value = "Overweight";
        if (bmi >= 30) value = "Obese Class I (Moderately obese)";
        if (bmi >= 35) value = "Obese Class II (Severely obese)";
        if (bmi >= 40) {
            value = "Obese Class III (Very severely obese)";
            JOptionPane.showMessageDialog(new JFrame(), value);
            display = Display.clear(display);
            this.display = Display.generate("TOO  FAT");
        } else JOptionPane.showMessageDialog(new JFrame(), value);
        display = Display.clear(display);
    }

    private void exit() throws InterruptedException {
        System.exit(0);
        assert display != null;
        Display.clear(display);
    }

    private float calculateBMI(int weight, int height) {
        float h = (float) height / 100;
        return weight / (h * h);
    }

}
