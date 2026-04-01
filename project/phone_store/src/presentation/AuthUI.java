package presentation;

import model.User;
import service.AuthService;

import java.util.Scanner;

public class AuthUI {
    private AuthService authService = new AuthService();
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n");
            System.out.println("===== MOBILE SHOP =====");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
            }
        } while (choice != 0);
    }

    private void login() {
        System.out.println("==================");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.println("==================");
        User user = authService.login(username, password);

        if (user != null) {
            System.out.println("Đăng nhập thành công!");

            if ("ADMIN".equals(user.getRole())) {
                new AdminUI().showMenu();
            } else {
                new CustomerUI().showMenu(user);
            }

        } else {
            System.err.println("Sai tài khoản hoặc mật khẩu!");
        }
    }

    private void register() {
        User user = new User();
        System.out.println("==================");
        System.out.print("Full name: ");
        user.setFullName(sc.nextLine());
        System.out.println("------------------");
        System.out.print("Username: ");
        user.setUsername(sc.nextLine());
        System.out.println("------------------");
        System.out.print("Password: ");
        user.setPassword(sc.nextLine());
        System.out.println("------------------");
        System.out.print("Email: ");
        user.setEmail(sc.nextLine());
        System.out.println("------------------");
        System.out.print("Phone: ");
        user.setPhone(sc.nextLine());
        System.out.println("------------------");
        System.out.print("Address: ");
        user.setAddress(sc.nextLine());
        System.out.println("------------------");
        if (authService.register(user)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.err.println("Đăng ký thất bại!");
        }
        System.out.println("==================");
    }
}
