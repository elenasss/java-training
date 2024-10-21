import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private static int uniqueId = 1;
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public User(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = uniqueId++;
    }

    public User(int id, String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}