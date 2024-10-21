import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Demo {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.insertUser(new User("Alex", "Stoyanov", "alex.stoyanov@gmail.com"));
        List<User> users = userService.getAllUsers();

        users.forEach(System.out::println);
    }
}