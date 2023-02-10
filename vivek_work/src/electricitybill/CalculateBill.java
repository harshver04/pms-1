package electricitybill;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;

public class CalculateBill extends JFrame implements ActionListener, java.awt.event.ActionListener {
    JTextField tfname, tfaddress, tfstate, tfunit, tfphone, tfemail;
    JButton next, cancle;
    JLabel lblname, labeladdress;
    Choice meternumber, cmonth;

    CalculateBill() {
        setSize(700, 500);
        setLocation(400, 150);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        JLabel heading = new JLabel("Calculate E-Bill");
        heading.setBounds(180, 30, 200, 20);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        p.add(heading);

        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100, 80, 100, 20);
        p.add(lblmeternumber);

        meternumber = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        meternumber.setBounds(240, 80, 200, 20);
        p.add(meternumber);

        JLabel lblmeterno = new JLabel("Name");
        lblmeterno.setBounds(100, 120, 100, 20);
        p.add(lblmeterno);

        lblname = new JLabel("");
        lblname.setBounds(240, 120, 100, 20);
        p.add(lblname);

        JLabel lbladdress = new JLabel("Address :");
        lbladdress.setBounds(100, 160, 100, 20);
        p.add(lbladdress);

        labeladdress = new JLabel();
        labeladdress.setBounds(240, 160, 200, 20);
        p.add(labeladdress);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s
                    .executeQuery("select * from customer where meter_no='" + meternumber.getSelectedItem() + "'");
            while (rs.next()) {
                lblname.setText(rs.getString("name"));
                labeladdress.setText(rs.getString("address"));
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        meternumber.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s
                            .executeQuery(
                                    "select * from customer where meter_no='" + meternumber.getSelectedItem() + "'");
                    while (rs.next()) {
                        lblname.setText(rs.getString("name"));
                        labeladdress.setText(rs.getString("address"));
                        ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
        JLabel lblcity = new JLabel("Unit Consume");
        lblcity.setBounds(100, 200, 100, 20);
        p.add(lblcity);

        tfunit = new JTextField();
        tfunit.setBounds(240, 200, 200, 20);
        p.add(tfunit);

        JLabel lblstate = new JLabel("Month");
        lblstate.setBounds(100, 240, 100, 20);
        p.add(lblstate);

        cmonth = new Choice();
        cmonth.setBounds(240, 240, 200, 20);
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
        p.add(cmonth);

        next = new JButton("SUBMIT");
        next.setBounds(200, 350, 100, 20);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

        cancle = new JButton("CANCLE");
        cancle.setBounds(350, 350, 100, 20);
        cancle.setBackground(Color.BLACK);
        cancle.setForeground(Color.WHITE);
        cancle.addActionListener(this);
        p.add(cancle);

        setLayout(new BorderLayout());
        add(p, "Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        getContentPane().setBackground(Color.WHITE);
        ;

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String meter = meternumber.getSelectedItem();
            String unit = tfunit.getText();
            String month = cmonth.getSelectedItem();

            int totalbill = 0;
            int unit_consumed = Integer.parseInt(unit);
            String query = "select * from tax";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while (rs.next()) {
                    totalbill += unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));

                    totalbill += Integer.parseInt(rs.getString("meter_rent"));
                    totalbill += Integer.parseInt(rs.getString("service_charge"));
                    totalbill += Integer.parseInt(rs.getString("service_tax"));
                    totalbill += Integer.parseInt(rs.getString("swach_bharat_cess"));
                    totalbill += Integer.parseInt(rs.getString("sixed_tax"));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            String query2 = "insert into bill values('" + meter + "','" + month + "','" + unit + "','" + totalbill
                    + "','Not Paid')";
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Bill Updated Sucessfully!!");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new CalculateBill();

    }

}
