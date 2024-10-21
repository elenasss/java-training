import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    @Autowired
    private DBManager dbManager;

    public void insertIntoDB(User user) {
        Connection connection = dbManager.getConnection();
        String insertQuery = "INSERT INTO users (id, first_name, last_name, email) VALUES(?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Connection connection = dbManager.getConnection();
        List<User> users = new ArrayList<>();
        String selectQuery = "SELECT id, first_name, last_name, email FROM users";

        try(PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                User user = getUserFromResultSet(result);
                users.add(user);
            }
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");

        return new User(id, firstName, lastName, email);
    }
}