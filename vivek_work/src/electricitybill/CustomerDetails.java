package electricitybill;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener, java.awt.event.ActionListener {

    Choice meternumber, cmonth;
    JTable table;
    JButton print;

    CustomerDetails() {
        super("Customer Details");

        setSize(1000, 650);
        setLocation(200, 150);

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        add(sp);

        print = new JButton("PRINT");
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print, "South");

        setVisible(true);

    }

    public static void main(String[] args) {
        new CustomerDetails();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == print) {

            try {
                table.print();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
