import tools.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String query = "SELECT * FROM test";
        ;
        try (PreparedStatement ps = new ConnectionManager().getConnection().prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
                       while (rs.next()) {
                           System.out.println(rs.getString("id"));
                       }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
