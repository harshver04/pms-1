package electricitybill;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;

    Conn() {
        try {

            // 1.register Driver class====
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // 2.creating commection==
            // if database is local not need to write ""
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "Vivek@123");

            // 3.create statement==
            s = c.createStatement();

            // 4.execute mysql quarries===

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
