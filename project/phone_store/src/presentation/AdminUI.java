package presentation;

//import service.CategoryService;

import presentation.category.CategoryUI;
import presentation.product.ProductUI;
import java.util.Scanner;
public class AdminUI {
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n");
            System.out.println("==========================");
            System.out.println("======= ADMIN MENU =======");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println("==========================");
            switch (choice) {
                case 1:
                    new CategoryUI().categoryMenu();
                    break;
                case 2:
                    new ProductUI().showMenu();
                    break;
                default:
                    break;
            }

        } while (choice != 0);
    }
}
