package b2;

public class UserService {
    public boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi ko âm");
        }
        return age >= 18;
    }
}