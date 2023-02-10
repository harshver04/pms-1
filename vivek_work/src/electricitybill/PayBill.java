package electricitybill;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class PayBill extends JFrame implements ActionListener, java.awt.event.ActionListener {

    Choice cmonth;
    String meter;
    JButton pay, back;

    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(350, 150, 900, 600);

        JLabel heading = new JLabel("Electricity Bill");
        heading.setBounds(120, 5, 400, 24);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);

        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(35, 80, 200, 20);
        add(lblmeterno);

        JLabel lblmeter = new JLabel("");
        lblmeter.setBounds(300, 80, 200, 20);
        add(lblmeter);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 200, 20);
        add(lblname);

        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        add(labelname);

        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 200, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 25);
        add(cmonth);
        cmonth.add("Jan");
        cmonth.add("Feb");
        cmonth.add("Mar");
        cmonth.add("Apr");
        cmonth.add("May");
        cmonth.add("Jun");
        cmonth.add("Jul");
        cmonth.add("Aug");
        cmonth.add("Sep");
        cmonth.add("Oct");
        cmonth.add("Nov");
        cmonth.add("Dec");
        add(cmonth);

        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 200, 20);
        add(lblunits);

        JLabel labelunit = new JLabel("");
        labelunit.setBounds(300, 260, 200, 20);
        add(labelunit);

        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 200, 20);
        add(lbltotalbill);

        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        add(labeltotalbill);

        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 200, 20);
        add(lblstatus);

        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(300, 380, 200, 20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);

        try {

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "' ");
            while (rs.next()) {
                lblmeter.setText(meter);
                labelname.setText(rs.getString("name"));
            }

            rs = c.s.executeQuery("select * from bill where meter_no='" + meter + "'AND month='Jan' ");

            while (rs.next()) {
                labelunit.setText(rs.getString("unit_consumed"));
                labeltotalbill.setText(rs.getString("total_bill"));
                labelstatus.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmonth.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {

                    Conn c = new Conn();
                    ResultSet rs;

                    rs = c.s.executeQuery("select * from bill where meter_no='" + meter + "'AND month='"
                            + cmonth.getSelectedItem() + "' ");

                    while (rs.next()) {
                        labelunit.setText(rs.getString("unit_consumed"));
                        labeltotalbill.setText(rs.getString("total_bill"));
                        labelstatus.setText(rs.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        pay = new JButton("PAY");
        pay.setBounds(100, 460, 100, 25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("BACK");
        back.setBounds(230, 460, 100, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == "PAY") {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status='Paid' where meter_no='" + meter + "' AND month='"
                        + cmonth.getSelectedItem() + "'");

            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);

        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
