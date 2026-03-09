
class User2 implements Authenticatable {

    private String password;

    public User2(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

public class b3 {
    public static void main(String[] args) {

        User2 user = new User2("123456");

        System.out.println("Password: " + user.getPassword());
        System.out.println("Authenticated: " + user.isAuthenticated());

        String encrypted = Authenticatable.encrypt("123456");
        System.out.println("Encrypted password: " + encrypted);
    }
}