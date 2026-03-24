package b1;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã bác sĩ: ");
        String code = sc.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String pass = sc.nextLine();

        boolean isLogin = Doc_b1.login(code, pass);

        if (isLogin) {
            System.out.println("Đăng nhập thành công!");
        } else {
            System.out.println("Sai thông tin!");
        }
    }
}
