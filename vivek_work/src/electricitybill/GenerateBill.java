package electricitybill;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;

public class GenerateBill extends JFrame implements ActionListener, java.awt.event.ActionListener {
    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

    GenerateBill(String meter) {
        this.meter = meter;
        setSize(500, 650);
        setLocation(550, 30);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JLabel heading = new JLabel("Generate Bill");
        JLabel meterno = new JLabel("Meter No.");

        cmonth = new Choice();
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

        area = new JTextArea(50, 15);
        area.setText(
                "\n\n\t--------Click on the Generate Bill ------- \n\t--------to see the Details of the Bill--------\n\n\t");
        area.setFont(new Font("Senserif", Font.BOLD, 18));

        JScrollPane pane = new JScrollPane(area);

        bill = new JButton("Generate Bill");
        bill.setBackground(Color.BLACK);
        bill.setForeground(Color.WHITE);
        bill.addActionListener(this);

        panel.add(heading);
        panel.add(meterno);
        panel.add(cmonth);
        add(panel, "North");
        add(pane, "Center");
        add(bill, "South");

        setVisible(true);
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String month = cmonth.getSelectedItem();
            area.setText(
                    "\tRelience Power LImited\n ELECTRICITY BILL GENERATED FOR THE MONTH\n\t OF " + month
                            + ",2022\n\n\n");
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            if (rs.next()) {
                area.append("\n    Customer Name:" + rs.getString("name"));
                area.append("\n    Meter No.            :" + rs.getString("meter_no"));
                area.append("\n    Address              :" + rs.getString("address"));
                area.append("\n    City                      :" + rs.getString("city"));
                area.append("\n    State                    :" + rs.getString("state"));
                area.append("\n    E-mail                  :" + rs.getString("email"));
                area.append("\n    Phone                  :" + rs.getString("phone"));
                area.append("\n----------------------------------------------------------------------------");
                area.append("\n");
            }
            rs = c.s.executeQuery("select * from meter_info where meter_no='" + meter + "'");
            if (rs.next()) {
                area.append("\n    Meter Location   :" + rs.getString("meter_location"));
                area.append("\n    Meter Type         :" + rs.getString("meter_type"));
                area.append("\n    Phase Code        :" + rs.getString("phase_code"));
                area.append("\n    Bill Type              :" + rs.getString("bill_type"));
                area.append("\n    Days                     :" + rs.getString("days"));
                area.append("\n----------------------------------------------------------------------------");
                area.append("\n");

            }
            rs = c.s.executeQuery("select * from tax ");
            if (rs.next()) {

                area.append("\n");
                area.append("\n    Cost Per Unit       :" + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent           :" + rs.getString("meter_rant"));
                area.append("\n    Service Charge   :" + rs.getString("service_charge"));
                area.append("\n    Service Tax         :" + rs.getString("service_tax"));
                area.append("\n    Sw. Bharat. Cess       :" + rs.getString("swach_bharat_cess"));
                area.append("\n    Fixed Tax             :" + rs.getString("sixed_tax"));
                area.append("\n----------------------------------------------------------------------------");
                area.append("\n");

            }

            rs = c.s.executeQuery("select * from bill where meter_no='" + meter + "' and month='" + month + "' ");
            if (rs.next()) {

                area.append("\n");
                area.append("\n   Current Month       :" + rs.getString("month"));
                area.append("\n   Unit Consumed     :" + rs.getString("unit_consumed"));
                area.append("\n   Total Charge         :" + rs.getString("total_bill"));

                area.append("\n----------------------------------------------------------------------------");
                area.append("\n    Total Payable       :" + rs.getString("total_bill"));
                area.append("\n");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
