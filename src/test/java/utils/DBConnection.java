package utils;

import com.epam.library.constants.LibraryConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DBConnection {

    private DBConnection(){}

    public static Connection getConnection() {
        Connection connection = null;
        try {
            ResourceBundle rb = ResourceBundle.getBundle(LibraryConstants.DataBase.Property.CONNECT);
            String url = rb.getString(LibraryConstants.DataBase.Property.URL);
            String login = rb.getString(LibraryConstants.DataBase.Property.LOGIN);
            String password = rb.getString(LibraryConstants.DataBase.Property.PASSWORD);
            String driver = rb.getString(LibraryConstants.DataBase.Property.DRIVER);
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
