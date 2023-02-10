package electricitybill;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Signup extends JFrame implements ActionListener, java.awt.event.ActionListener {
    JButton create, back, reset;
    JTextField username, pass, meter, name;
    Choice acctype;

    Signup() {
        super("Signup Form");

        setBounds(450, 150, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(172, 216, 230)));

        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);

        JLabel heading = new JLabel("Create Account");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);

        acctype = new Choice();
        acctype.add("ADMIN");
        acctype.add("CUSTOMER");
        acctype.setBounds(260, 50, 150, 20);
        panel.add(acctype);
        // acctype.addFocusListener();

        JLabel lblmeter = new JLabel("Meter Number :");
        lblmeter.setBounds(100, 90, 140, 20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);

        meter = new JTextField();
        meter.setBounds(260, 90, 150, 20);

        meter.setVisible(false);
        panel.add(meter);

        JLabel lbluser = new JLabel("User Name :");
        lbluser.setBounds(100, 130, 140, 20);
        lbluser.setForeground(Color.GRAY);
        lbluser.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lbluser);

        username = new JTextField();
        username.setBounds(260, 130, 150, 20);
        panel.add(username);

        JLabel lblname = new JLabel("Name :");
        lblname.setBounds(100, 170, 140, 20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblname);

        name = new JTextField();
        name.setBounds(260, 170, 150, 20);
        panel.add(name);

        meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {

            }

            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no='" + meter.getText() + "'");
                    while (rs.next()) {
                        name.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        JLabel lblpass = new JLabel("Password :");
        lblpass.setBounds(100, 210, 140, 20);
        lblpass.setForeground(Color.GRAY);
        lblpass.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblpass);

        pass = new JTextField();
        pass.setBounds(260, 210, 150, 20);
        panel.add(pass);

        acctype.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = acctype.getSelectedItem();
                if (user.equals("CUSTOMER")) {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(false);
                }

            }
        });

        create = new JButton("CREATE");
        create.setBounds(115, 260, 90, 25);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.addActionListener(this);
        panel.add(create);

        reset = new JButton("RESET");
        reset.setBounds(230, 260, 90, 25);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        panel.add(reset);

        back = new JButton("BACK");
        back.setBounds(345, 260, 90, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupimage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(415, 30, 250, 250);
        panel.add(image);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == create) {
            String atype = acctype.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spass = pass.getText();
            String smeter = meter.getText();

            try {
                Conn c = new Conn();
                String query = null;
                if (atype.equals("ADMIN")) {
                    query = "insert into login values('" + smeter + "', '" + susername + "','" + sname + "','"
                            + spass + "','" + atype + "');";
                } else {
                    query = "update login set username='" + susername + "', pass='" + spass + "', user='" + atype
                            + "' where meter_no='" + smeter + "';";
                }
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Accouunt Created Sucessfully");
                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == reset) {
            username.setText("");
            name.setText("");
            pass.setText("");
            meter.setText("");

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();

        }

    }

    public static void main(String[] args) {
        new Signup();
    }
}
