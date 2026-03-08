public class b5 {
    public static void main(String[] args) {
        User user = new User();
        try {
            user.setAge(-10);
            System.out.println("Tuổi: " + user.getAge());
        } catch (InvalidAgeException e) {
            System.out.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}