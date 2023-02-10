package electricitybill;

import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.*;

public class ViewInfo extends JFrame implements ActionListener, java.awt.event.ActionListener {
    JButton cancle;

    ViewInfo(String meter) {
        setBounds(350, 100, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);
        JLabel name = new JLabel("");
        name.setBounds(250, 80, 100, 20);
        add(name);

        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(70, 140, 100, 20);
        add(lblmeterno);
        JLabel meterno = new JLabel("");
        meterno.setBounds(250, 140, 100, 20);
        add(meterno);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);
        JLabel address = new JLabel("");
        address.setBounds(250, 200, 100, 20);
        add(address);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);
        JLabel city = new JLabel("");
        city.setBounds(250, 260, 100, 20);
        add(city);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(500, 80, 100, 20);
        add(lblstate);
        JLabel state = new JLabel("");
        state.setBounds(650, 80, 100, 20);
        add(state);

        JLabel lblmail = new JLabel("E-mail");
        lblmail.setBounds(500, 140, 100, 20);
        add(lblmail);
        JLabel mail = new JLabel("");
        mail.setBounds(650, 140, 100, 20);
        add(mail);

        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(500, 200, 100, 20);
        add(lblphone);
        JLabel phone = new JLabel("");
        phone.setBounds(650, 200, 100, 20);
        add(phone);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                mail.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meterno.setText(rs.getString("meter_no"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancle = new JButton("Cancle");
        cancle.setBounds(350, 340, 100, 25);
        cancle.setBackground(Color.BLACK);
        cancle.setForeground(Color.WHITE);
        cancle.addActionListener(this);
        add(cancle);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);

        setVisible(true);

    }

    public static void main(String[] args) {
        new ViewInfo("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);

    }

}
