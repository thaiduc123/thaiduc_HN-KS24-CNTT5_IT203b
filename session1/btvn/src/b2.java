import java.util.Scanner;

public class b2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập tổng số người: ");
            int totalUsers = sc.nextInt();
            System.out.print("Nhập số nhóm: ");
            int groups = sc.nextInt();
            int usersPerGroup = totalUsers / groups;
            System.out.println("Mỗi nhóm có: " + usersPerGroup);
        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0!");

        }sc.close();
    }
}