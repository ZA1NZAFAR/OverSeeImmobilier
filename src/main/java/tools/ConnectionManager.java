package tools;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String CONNECTION_URL = "jdbc:mysql://remotemysql.com:3306/4g7LpRXOWZ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DB_USERNAME = "4g7LpRXOWZ";
    private static String DB_PASSWORD = "2q6pXfV3DP";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUrl(CONNECTION_URL);
        dataSource.setUsername( DB_USERNAME);
        dataSource.setPassword( DB_PASSWORD);
        return dataSource;
    }
}
