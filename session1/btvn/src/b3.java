public class b3 {
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setAge(-5);
            System.out.println("Tuổi: " + user.getAge());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAgeException e) {
            throw new RuntimeException(e);
        }
    }
}