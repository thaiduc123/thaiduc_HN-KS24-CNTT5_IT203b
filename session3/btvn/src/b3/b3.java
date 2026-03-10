package b3;

import java.util.Optional;

record User(String username, String email) {}

public class b3 {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();
        Optional<User> user = repo.findUserByUsername("alice");
        user.ifPresent(u -> System.out.println("Welcome " + u.username()));
        if (user.isEmpty()) {
            System.out.println("Guest");
        }
    }
}