
package electricitybill;

import java.awt.Image;

import javax.swing.*;

public class Splash extends JFrame implements Runnable {
    Thread t;

    Splash() {

        // set image====

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        // set frame ---
        setVisible(true);
        int x = 1;
        for (int i = 1; i < 600; i += 5, x += 1) {
            setSize(i + x, i);
            setLocation(700 - (i + x) / 2, 400 - i / 2);
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        t = new Thread(this);
        t.start();
        setVisible(true);
    }

    public void run() {
        try {
            Thread.sleep(5000);
            setVisible(false);

            // login frame===

            new Login();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
