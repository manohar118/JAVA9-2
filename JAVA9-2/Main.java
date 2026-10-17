import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(255) NOT NULL"
                + ")";
        String insertUserSql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement createTableStatement = connection.prepareStatement(createTableSql)) {
            createTableStatement.executeUpdate();

            try (PreparedStatement insertUserStatement = connection.prepareStatement(insertUserSql)) {
                insertUserStatement.setString(1, "John Doe");
                insertUserStatement.setString(2, "john.doe@example.com");
                insertUserStatement.executeUpdate();

                insertUserStatement.setString(1, "Jane Doe");
                insertUserStatement.setString(2, "jane.doe@example.com");
                insertUserStatement.executeUpdate();
            }

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Database operation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
