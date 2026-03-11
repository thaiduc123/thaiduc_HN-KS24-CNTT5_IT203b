package b3;

public class UserProcessor {
    public String processEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Sai định dạng");
        }
        String[] p = email.split("@");
        if (p.length != 2 || p[1].isEmpty()) {
            throw new IllegalArgumentException("Email phải có miền");
        }
        return email.toLowerCase();
    }
}
