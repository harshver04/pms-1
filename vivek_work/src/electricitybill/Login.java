
package electricitybill;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

/**
 *
 * @author NAYAL
 */

public class Login extends JFrame implements ActionListener, java.awt.event.ActionListener {
    JButton login, signup, cancle;
    JTextField username, pass;
    Choice loginin;

    Login() {
        super("Login Page");

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel lblusername = new JLabel("User Name");
        lblusername.setBounds(300, 20, 100, 20);
        add(lblusername);

        username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        add(username);

        JLabel lblpass = new JLabel("Password");
        lblpass.setBounds(300, 60, 100, 20);
        add(lblpass);

        pass = new JTextField();
        pass.setBounds(400, 60, 150, 20);
        add(pass);

        JLabel loginas = new JLabel("Login As :");
        loginas.setBounds(300, 100, 100, 20);
        add(loginas);

        loginin = new Choice();
        loginin.add("ADMIN");
        loginin.add("CUSTOMER");
        loginin.setBounds(400, 100, 150, 25);
        add(loginin);

        login = new JButton("Login");
        login.setBounds(330, 160, 100, 25);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener((java.awt.event.ActionListener) this);
        add(login);

        cancle = new JButton("Cancle");
        cancle.setBounds(450, 160, 100, 25);
        cancle.setBackground(Color.BLACK);
        cancle.setForeground(Color.WHITE);
        cancle.addActionListener(this);
        add(cancle);

        signup = new JButton("SignUp");
        signup.setBounds(380, 200, 100, 25);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 250, 250);
        add(image);

        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String susername = username.getText();
            String spass = pass.getText();
            String user = loginin.getSelectedItem();
            try {
                Conn c = new Conn();
                String query = "select * from login where username ='" + susername + "' and pass= '" + spass
                        + "' and user='" + user + "'";
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user, meter);

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    pass.setText("");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        else if (ae.getSource() == signup) {
            setVisible(false);
            new Signup();

        }

        else if (ae.getSource() == cancle) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
