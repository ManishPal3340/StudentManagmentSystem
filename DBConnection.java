package ProjectDB;

import java.sql.Connection;  
import java.sql.DriverManager;

public class DBConnection {

    public static Connection dbConnect() {

        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/slooze";
            String username = "root";
            String password = "root";

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
