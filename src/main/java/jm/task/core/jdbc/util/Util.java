package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("users?").          //db name
                    append("serverTimezone=UTC&").
                    append("user=root&").          //login
                    append("password=METRO121213");       //password
            System.out.println("URL: " + url + "\n");

            //Connection connection = DriverManager.getConnection(url.toString());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?serverTimezone=UTC", "root", "METRO121213");
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}