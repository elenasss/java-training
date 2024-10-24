import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class LambdaExpressions {
    public static void main(String[] args) {

        String name = "Elena";
        Function<String, String> upperCase = String::toUpperCase;
        System.out.println(upperCase.apply(name));

        Function<String, String> lastName = s -> s.concat(" Borisova");
        Function<String, String> upperCaseLastName = upperCase.andThen(lastName);
        System.out.println(upperCaseLastName.apply(name));

        upperCaseLastName = upperCase.compose(lastName);
        System.out.println(upperCaseLastName.apply(name));

        Function<String, String[]> func1 = upperCase
                .andThen(s -> s.concat(" Borisova"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(func1.apply(name)));

        Function<String, String> func2 = upperCase
                .andThen(s -> s.concat(" Borisova"))
                .andThen(s -> s.split(" "))
                .andThen(s -> s[1].toUpperCase() + ", " + s[0]);
        System.out.println(func2.apply(name));

        String[] names = {"Ann", "Bob", "Carol"};
        Consumer<String> firstLetter = s -> System.out.print(s.charAt(0));
        Consumer<String> printName = System.out::println;
        Arrays.asList(names)
                  .forEach(firstLetter
                  .andThen(s -> System.out.print(" - "))
                  .andThen(printName));

        record Person(String firstName, String lastName) {}

        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Mickey", "Mouse")
        ));


        list.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        list.forEach(System.out::println);
        System.out.println("------------------------------------");

        list.sort(Comparator.comparing(Person::lastName));
        list.forEach(System.out::println);
        System.out.println("------------------------------------");

        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName));
        list.forEach(System.out::println);
        System.out.println("------------------------------------");

        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName).reversed());
        list.forEach(System.out::println);
    }
}