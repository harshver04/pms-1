package electricitybill;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositeDetails extends JFrame implements ActionListener, java.awt.event.ActionListener {

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    DepositeDetails() {
        super("Deposite Details");

        setSize(700, 700);
        setLocation(400, 100);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblmeternumber = new JLabel("Search by Meter No.");
        lblmeternumber.setBounds(60, 40, 150, 20);
        add(lblmeternumber);

        meternumber = new Choice();
        meternumber.setBounds(200, 40, 100, 25);
        add(meternumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblmonth = new JLabel("Search by Month");
        lblmonth.setBounds(380, 40, 150, 20);
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(500, 40, 100, 25);
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

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 150, 700, 600);
        add(sp);

        search = new JButton("SEARCH");
        search.setBounds(200, 100, 100, 30);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add(search);

        print = new JButton("PRINT");
        print.setBounds(400, 100, 100, 30);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        setVisible(true);

    }

    public static void main(String[] args) {
        new DepositeDetails();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == search) {

            String query = "select * from bill where meter_no='" + meternumber.getSelectedItem() + "' and month='"
                    + cmonth.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                table.print();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
