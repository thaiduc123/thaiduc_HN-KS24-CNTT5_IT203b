
import b4.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class B4Test {
    PasswordService service;
    @BeforeEach
    void setUp() {
        service = new PasswordService();
    }
    @Test
    void strong() {
        String result = service.evaluatePasswordStrength("Thd3107@");
        assertEquals("Mạnh", result);
    }
    @Test
    void medium() {
        assertAll(
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("thd123!@")),
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("THD123!@")),
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("Thdhdhd!@")),
                () -> assertEquals("Trung bình", service.evaluatePasswordStrength("Thd12345"))
        );
    }
    @Test
    void weak() {
        assertAll(
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("Ab1!")),
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("password")),
                () -> assertEquals("Yếu", service.evaluatePasswordStrength("ABC12345"))
        );
    }
}