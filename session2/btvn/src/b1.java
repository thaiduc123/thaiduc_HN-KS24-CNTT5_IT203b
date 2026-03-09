import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

//class User {
//    private String username;
//    private String role;
//
//    public User(String username, String role) {
//        this.username = username;
//        this.role = role;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void displayInfo() {
//        System.out.println("Username: " + username + ", Role: " + role);
//    }
//}

public class b1 {
    public static void main(String[] args) {

        // 1. Predicate: kiểm tra User có phải Admin không
        Predicate<User> isAdmin = user -> user.getRole().equalsIgnoreCase("ADMIN");

        // 2. Function: chuyển User -> String (username)
        Function<User, String> getUsername = user -> user.getUsername();

        // 3. Consumer: in thông tin User ra console
        Consumer<User> printUser = user -> user.displayInfo();

        // 4. Supplier: tạo User mặc định
        Supplier<User> defaultUser = () -> new User("guest", "USER");

        // Test
        User u1 = new User("thai", "ADMIN");
        User u2 = defaultUser.get();

        System.out.println("Is thai admin? " + isAdmin.test(u1));

        String username = getUsername.apply(u1);
        System.out.println("Username: " + username);

        printUser.accept(u1);

        System.out.println("Default user:");
        printUser.accept(u2);
    }
}