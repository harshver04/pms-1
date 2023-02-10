package electricitybill;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateInfo extends JFrame implements ActionListener, java.awt.event.ActionListener {
    JButton update, cancle;
    JTextField tfaddress, tfcity, tfstate, tfmail, tfphone;
    String meter;
    JLabel name;

    UpdateInfo(String meter) {
        this.meter = meter;
        setBounds(300, 150, 650, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 70, 100, 20);
        add(lblname);
        name = new JLabel("");
        name.setBounds(230, 70, 200, 20);
        add(name);

        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(30, 110, 100, 20);
        add(lblmeterno);
        JLabel meterno = new JLabel("");
        meterno.setBounds(230, 110, 200, 20);
        add(meterno);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30, 150, 100, 20);
        add(lbladdress);
        tfaddress = new JTextField();
        tfaddress.setBounds(230, 150, 200, 20);
        add(tfaddress);

        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(30, 190, 100, 20);
        add(lblcity);
        tfcity = new JTextField();
        tfcity.setBounds(230, 190, 200, 20);
        add(tfcity);

        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(30, 230, 100, 20);
        add(lblstate);
        tfstate = new JTextField();
        tfstate.setBounds(230, 230, 200, 20);
        add(tfstate);

        JLabel lblmail = new JLabel("E-mail");
        lblmail.setBounds(30, 270, 100, 20);
        add(lblmail);
        tfmail = new JTextField();
        tfmail.setBounds(230, 270, 200, 20);
        add(tfmail);

        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(30, 310, 100, 20);
        add(lblphone);
        tfphone = new JTextField();
        tfphone.setBounds(230, 310, 200, 20);
        add(tfphone);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            while (rs.next()) {
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfmail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("phone"));
                meterno.setText(rs.getString("meter_no"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        update = new JButton("UPDATE");
        update.setBounds(180, 360, 100, 25);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        cancle = new JButton("CANCLE");
        cancle.setBounds(320, 360, 100, 25);
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
        new UpdateInfo("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {

            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfmail.getText();
            String phone = tfphone.getText();

            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address='" + address + "',city='" + city + "',state='" + state
                        + "',email='" + email + "',phone='" + phone + "'");
                JOptionPane.showMessageDialog(null, "User Information Updated!!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }

    }
}
