package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String CONNECTION_URL = "jdbc:mysql://localhost:3306/oversee?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String DB_USERNAME = "zz";
    private String DB_PASSWORD = "ln1gRC3qA-Aw51tF";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
}
