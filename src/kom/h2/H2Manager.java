package kom.h2;

import java.sql.*;
import java.util.Objects;

public class H2Manager {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private static Connection getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void createPipelineTable() {
        try(Connection connection = getDBConnection()) {
            Statement statement = Objects.requireNonNull(connection).createStatement();
            statement.execute("CREATE TABLE PIPELINE(idX int, idY int, length int)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertPipeLine(int idX, int idY, int length) {
        String insertQuery = "INSERT INTO PIPELINE" + "(idX, idY, length) VALUES" + "(?,?,?)";
        try(Connection connection = getDBConnection();
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(insertQuery)) {
            statement.setInt(1, idX);
            statement.setInt(2, idY);
            statement.setInt(3, length);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
