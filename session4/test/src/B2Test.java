import b2.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class B2Test{
    @Test
    void testAge18() {
        UserService service = new UserService();
        int age = 18;
        boolean result = service.checkRegistrationAge(age);
        assertEquals(false, result);
    }
    @Test
    void testAge17() {
        UserService service = new UserService();
        int age = 17;
        boolean result = service.checkRegistrationAge(age);
        assertEquals(false, result);
    }
    @Test
    void testNegative() {
        UserService service = new UserService();
        int age = -1;
        assertThrows(IllegalArgumentException.class, () -> {
            service.checkRegistrationAge(age);
        });
    }
}
