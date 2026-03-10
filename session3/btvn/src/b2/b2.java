package b2;

import java.util.ArrayList;
import java.util.List;

record User(String username, String email) {}
public class b2 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("thd", "thd@gmail.com"));
        users.add(new User("thd2", "dth@yahoo.com"));
        users.add(new User("thd3", "hdt@gmail.com"));
        users.stream()
                .filter(user -> user.email().endsWith("gmail.com"))
                .map(User::username)
                .forEach(System.out::println);
    }
}