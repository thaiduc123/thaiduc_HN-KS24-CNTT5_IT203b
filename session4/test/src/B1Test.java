import b1.UserValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class B1Test {
    @Test
    void testValidUsername() {
        String username = "thd123";
        boolean result = UserValidator.isValidUsername(username);
        assertTrue(result);
    }
    @Test
    void testUsernameTooShort() {
        String username = "thd";
        boolean result = UserValidator.isValidUsername(username);
        assertFalse(result);
    }
    @Test
    void testUsernameContainsSpace() {
        String username = "t h d";
        boolean result = UserValidator.isValidUsername(username);
        assertFalse(result);
    }
}