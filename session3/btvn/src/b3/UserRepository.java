package b3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> users = new ArrayList<>();
    public UserRepository() {
        users.add(new User("alice", "alice@gmail.com"));
        users.add(new User("bob", "bob@yahoo.com"));
        users.add(new User("charlie", "charlie@gmail.com"));
    }
    public Optional<User> findUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.username().equalsIgnoreCase(username))
                .findFirst();
    }
}
