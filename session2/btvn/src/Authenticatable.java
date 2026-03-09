@FunctionalInterface
public interface Authenticatable {

    // Abstract method
    String getPassword();

    // Default method: kiểm tra mật khẩu có rỗng không
    default boolean isAuthenticated() {
        return getPassword() != null && !getPassword().isEmpty();
    }

    // Static method: mô phỏng mã hóa mật khẩu
    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword;
    }
}