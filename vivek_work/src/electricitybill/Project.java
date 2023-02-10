package electricitybill;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project extends JFrame implements ActionListener, java.awt.event.ActionListener {
    String atype, meter;

    Project(String atype, String meter) {
        this.atype = atype;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JMenuBar mb = new JMenuBar();
        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);
        setJMenuBar(mb);

        JMenuItem newcustomer = new JMenuItem("New Customer");
        newcustomer.setFont(new Font("monospace", Font.PLAIN, 12));
        newcustomer.setBackground(Color.WHITE);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(image1));
        newcustomer.setMnemonic('N');
        newcustomer.addActionListener(this);
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        master.add(newcustomer);

        JMenuItem customerdetails = new JMenuItem("Customer Details");
        customerdetails.setFont(new Font("monospace", Font.PLAIN, 12));
        newcustomer.setBackground(Color.WHITE);
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerdetails.setIcon(new ImageIcon(image2));
        customerdetails.setMnemonic('M');
        customerdetails.addActionListener(this);
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        master.add(customerdetails);

        JMenuItem depositedetails = new JMenuItem("Deposite Details");
        depositedetails.setFont(new Font("monospace", Font.PLAIN, 12));
        depositedetails.setBackground(Color.WHITE);
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositedetails.setIcon(new ImageIcon(image3));
        depositedetails.setMnemonic('D');
        depositedetails.addActionListener(this);
        depositedetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        master.add(depositedetails);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        calculatebill.setFont(new Font("monospace", Font.PLAIN, 12));
        calculatebill.setBackground(Color.WHITE);
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(image4));
        calculatebill.setMnemonic('B');
        calculatebill.addActionListener(this);
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        master.add(calculatebill);

        // second Menu

        JMenu info = new JMenu("Information");
        info.setForeground(Color.RED);
        setJMenuBar(mb);

        JMenuItem updateinfo = new JMenuItem("Update Info");
        updateinfo.setFont(new Font("monospace", Font.PLAIN, 12));
        updateinfo.setBackground(Color.WHITE);
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateinfo.setIcon(new ImageIcon(image5));
        updateinfo.addActionListener(this);
        updateinfo.setMnemonic('U');
        updateinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        info.add(updateinfo);

        JMenuItem viewinfo = new JMenuItem("View Info");
        viewinfo.setFont(new Font("monospace", Font.PLAIN, 12));
        viewinfo.setBackground(Color.WHITE);
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewinfo.setIcon(new ImageIcon(image6));
        viewinfo.addActionListener(this);
        viewinfo.setMnemonic('V');
        viewinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        info.add(viewinfo);

        // third menu===

        JMenu user = new JMenu("User");
        user.setForeground(Color.BLUE);
        setJMenuBar(mb);

        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("monospace", Font.PLAIN, 12));
        paybill.setBackground(Color.WHITE);
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        paybill.setIcon(new ImageIcon(image7));
        paybill.setMnemonic('B');
        paybill.addActionListener(this);
        paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        user.add(paybill);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("monospace", Font.PLAIN, 12));
        billdetails.setBackground(Color.WHITE);
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billdetails.setIcon(new ImageIcon(image8));
        billdetails.setMnemonic('1');
        billdetails.addActionListener(this);
        billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        user.add(billdetails);

        // forth menu===
        JMenu report = new JMenu("Report");
        report.setForeground(Color.RED);
        setJMenuBar(mb);

        JMenuItem generatebill = new JMenuItem("Generate Bill");
        generatebill.setFont(new Font("monospace", Font.PLAIN, 12));
        generatebill.setBackground(Color.WHITE);
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generatebill.setIcon(new ImageIcon(image9));
        generatebill.setMnemonic('R');
        generatebill.addActionListener(this);
        generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        report.add(generatebill);

        // fifth menu===
        JMenu utality = new JMenu("Utility");
        utality.setForeground(Color.BLUE);
        setJMenuBar(mb);

        JMenuItem notepad = new JMenuItem("Note Pad");
        notepad.setFont(new Font("monospace", Font.PLAIN, 12));
        notepad.setBackground(Color.WHITE);
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(image10));
        notepad.setMnemonic('9');
        notepad.addActionListener(this);
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, ActionEvent.CTRL_MASK));
        utality.add(notepad);

        JMenuItem calc = new JMenuItem("Calculater");
        calc.setFont(new Font("monospace", Font.PLAIN, 12));
        calc.setBackground(Color.WHITE);
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calc.setIcon(new ImageIcon(image11));
        calc.setMnemonic('C');
        calc.addActionListener(this);
        calc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        utality.add(calc);

        JMenuItem mexit = new JMenuItem("Exit");
        mexit.setFont(new Font("monospace", Font.PLAIN, 12));
        mexit.setBackground(Color.WHITE);
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        mexit.setIcon(new ImageIcon(image12));
        mexit.setMnemonic('C');
        mexit.addActionListener(this);
        mexit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        utality.add(mexit);

        if (atype.equals("ADMIN")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);

        }

        mb.add(utality);

        setLayout(new FlowLayout());
        setVisible(true);

    }

    public static void main(String[] args) {
        new Project("", "");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposite Details")) {
            new DepositeDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();

        } else if (msg.equals("View Info")) {
            new ViewInfo(meter);
        } else if (msg.equals("Update Info")) {
            new UpdateInfo(meter);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (msg.equals("Note Pad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Calculater")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter);
        }

    }
}
