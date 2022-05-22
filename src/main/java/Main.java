import models.Proprietaire;
import models.Propriete;
import tools.ConnectionManager;
import tools.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        List<Proprietaire> p = DatabaseConnector.getAllProprietaires();
        System.out.println(p);

//        String query = "SELECT * FROM Test";
//        ;
//        try (PreparedStatement ps = new ConnectionManager().getConnection().prepareStatement(query);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                System.out.println(rs.getString("test"));
//            }
//        } catch (SQLException e) {
//            throw new SQLException(e.getMessage());
//        }
    }
}
