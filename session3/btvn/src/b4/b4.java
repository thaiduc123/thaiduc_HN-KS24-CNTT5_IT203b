package b4;

import java.util.*;
import java.util.stream.Collectors;

record User(String username, String email) {}
public class b4 {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("thd", "thd@gmail.com"),
                new User("hdt", "hdt@yahoo.com"),
                new User("thd", "thd@gmail.com"),
                new User("tdh", "tdh@gmail.com")
        );
        List<User> uniqueUsers = new ArrayList<>(
                users.stream()
                        .collect(Collectors.toMap(
                                User::username,
                                user -> user,
                                (u1, u2) -> u1
                        ))
                        .values()
        );
        uniqueUsers.forEach(System.out::println);
    }
}