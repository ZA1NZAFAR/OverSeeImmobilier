package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String CONNECTION_URL = "jdbc:mysql://remotemysql.com:3306/4g7LpRXOWZ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DB_USERNAME = "4g7LpRXOWZ";
    private static String DB_PASSWORD = "2q6pXfV3DP";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }



    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
