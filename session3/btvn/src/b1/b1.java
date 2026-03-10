package b1;

import java.util.ArrayList;
import java.util.List;

record User(String username, String email, String status) {}
public class b1 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("thd", "thd@example.com", "ACTIVE"));
        users.add(new User("thd2", "dht@example.com", "INACTIVE"));
        users.add(new User("thd3", "hdt@example.com", "ACTIVE"));
        users.forEach(user ->
                System.out.println("Username: " + user.username() +
                                ", Email: " + user.email() +
                                ", Status: " + user.status()
                )
        );
    }
}