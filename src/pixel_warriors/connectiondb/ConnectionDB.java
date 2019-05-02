package pixel_warriors.connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private Connection connection;

    private static String dbName, userName, password;

    private volatile static ConnectionDB instance;

    private ConnectionDB(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        createConnection();
    }

    public static ConnectionDB getInstance() {
        if (instance == null) {
            synchronized (ConnectionDB.class) {
                if (instance == null) {
                    instance = new ConnectionDB("pixelwarriors", "lab", "lab");
                }
            }
        }
        return instance;
    }

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
