import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.host:localhost}")
    private String dbHost;

    @Value("${db.port}")
    private String dbPort;

    @Value("${db.name}")
    private String dbName;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public DBManager dbManager() {
        return new DBManager(dbDriver, dbHost, dbPort, dbName, dbUser, dbPassword);
    }
}