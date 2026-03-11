
import b3.UserProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class B3Test {
    UserProcessor processor;
    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }
    @Test
    void validEmail() {
        String email = "thd@gmail.com";
        String result = processor.processEmail(email);
        assertEquals("thd@gmail.com", result);
    }
    @Test
    void emailMissing() {
        String email = "thdgmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }
    @Test
    void noDomain() {
        String email = "thd@";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }
    @Test
    void lowerCase() {
        String email = "THD@Gmail.com";
        String result = processor.processEmail(email);
        assertEquals("thd@gmail.com", result);
    }
}