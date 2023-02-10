package electricitybill;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MeterInfo extends JFrame implements ActionListener, java.awt.event.ActionListener {
    JButton next;

    Choice meterlocation, metertype, phasecode, billtype;
    String meternumber;

    MeterInfo(String meternumber) {

        this.meternumber = meternumber;
        setSize(700, 500);
        setLocation(400, 200);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 30, 200, 20);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        p.add(heading);

        JLabel lblmeternumber = new JLabel("Meter Number :");
        lblmeternumber.setBounds(100, 80, 100, 20);
        p.add(lblmeternumber);

        JLabel lblmeterno = new JLabel(meternumber);
        lblmeterno.setBounds(240, 80, 100, 20);
        p.add(lblmeterno);

        JLabel lblmeterlo = new JLabel("Meter Location");
        lblmeterlo.setBounds(100, 120, 100, 20);
        p.add(lblmeterlo);

        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240, 120, 100, 20);
        p.add(meterlocation);

        JLabel lblmtype = new JLabel("Meter Type");
        lblmtype.setBounds(100, 160, 100, 20);
        p.add(lblmtype);

        metertype = new Choice();
        metertype.add("Electric Meter");
        metertype.add("Solar Meter");
        metertype.add("Smart Meter");
        metertype.setBounds(240, 160, 100, 20);
        p.add(metertype);

        JLabel lblphase = new JLabel("Phase Code");
        lblphase.setBounds(100, 200, 100, 20);
        p.add(lblphase);

        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.add("088");
        phasecode.add("099");

        phasecode.setBounds(240, 200, 100, 20);
        p.add(phasecode);

        JLabel lblbtype = new JLabel("Bill Type");
        lblbtype.setBounds(100, 240, 100, 20);
        p.add(lblbtype);

        billtype = new Choice();
        billtype.add("Industrial Bill");
        billtype.add("Normal Bill");
        billtype.setBounds(240, 240, 100, 20);
        p.add(billtype);

        JLabel lbldays = new JLabel("Days");
        lbldays.setBounds(100, 280, 100, 20);
        p.add(lbldays);

        JLabel sdays = new JLabel("30 Days");
        sdays.setBounds(240, 280, 100, 20);
        p.add(sdays);

        JLabel lblnote = new JLabel("Note");
        lblnote.setBounds(100, 320, 100, 20);
        p.add(lblnote);

        JLabel snote = new JLabel("By Default Bill is Calculated for 30 Days.");
        snote.setBounds(240, 320, 500, 20);
        p.add(snote);

        next = new JButton(" SUBMIT ");
        next.setBounds(220, 390, 100, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);

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

            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typeb = billtype.getSelectedItem();
            String days = "30";

            String query1 = "insert into meter_info values('" + meter + "','" + location + "','" + type + "','" + code
                    + "','" + typeb + "','" + days + "')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);

                JOptionPane.showMessageDialog(null, "Meter Information Added !!");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new MeterInfo("");

    }
}