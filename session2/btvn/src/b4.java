import java.util.*;
import java.util.function.*;

class User {
    private String username;

    public User() {
        this.username = "defaultUser";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class b4 {
    public static void main(String[] args) {

        // Tự định nghĩa danh sách users
        List<User> users = Arrays.asList(
                new User("thai"),
                new User("duc"),
                new User("admin")
        );

        // 1. Lambda -> Method Reference (instance method của object bất kỳ)
        Function<User, String> getName = User::getUsername;

        // 2. Lambda -> Method Reference (instance method của object cụ thể)
        Consumer<String> printer = System.out::println;

        // 3. Lambda -> Method Reference (constructor)
        Supplier<User> createUser = User::new;

        // Áp dụng với danh sách users
        users.stream()
                .map(getName)
                .forEach(printer);

        // Tạo user mới bằng constructor reference
        User newUser = createUser.get();
        System.out.println("New user: " + newUser.getUsername());
    }
}