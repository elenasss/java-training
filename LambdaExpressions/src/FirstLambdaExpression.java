import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FirstLambdaExpression {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Hello Lambda";
        System.out.println(supplier.get());

        Consumer<String> consumer = s -> System.out.printf("My name is %s%n", s);
        consumer.accept("Elena");

        List<String> list = new ArrayList<>(List.of("One", "Two", "Three", "Four", "Five"));

        Predicate<String> filter = s -> s.startsWith("T");
        Consumer<String> result = s -> System.out.printf("%s%n", s);
        list.removeIf(filter);
        list.forEach(result);
        System.out.println("==============================================");

        list.removeIf(s -> s.startsWith("F"));
        list.forEach(System.out::println);
    }
}
