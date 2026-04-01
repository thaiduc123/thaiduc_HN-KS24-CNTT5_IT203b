package presentation;

import model.User;
import presentation.customer.CartCustomerUI;
import presentation.customer.OrderCustomerUI;
import presentation.customer.ProductCustomerUI;

import java.util.Scanner;

public class CustomerUI {
    private Scanner sc = new Scanner(System.in);
    private ProductCustomerUI productUI = new ProductCustomerUI();
    private CartCustomerUI cartUI = new CartCustomerUI();
    private OrderCustomerUI orderUI = new OrderCustomerUI();
    public void showMenu(User user) {
        int choice;
        do {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("Xin chào: " + user.getUsername());
            System.out.println("====== CUSTOMER MENU ======");
            System.out.println("1. Xem sản phẩm còn hàng");
            System.out.println("2. Xem giỏ hàng");
            System.out.println("3. Thêm vào giỏ hàng");
            System.out.println("4. Giảm số lượng trong giỏ");
            System.out.println("5. Đặt hàng");
            System.out.println("6. Xem lịch sử đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println("===========================");
            switch (choice) {
                case 1:
                    productUI.showAvailableProducts();
                    break;
                case 2:
                    cartUI.showCart(user.getId());
                    break;
                case 3:
                    cartUI.addToCart(user.getId());
                    break;
                case 4:
                    cartUI.removeFromCart(user.getId());
                    break;
                case 5:
                    orderUI.checkout(user.getId());
                    break;
                case 6:
                    orderUI.showOrderHistory(user.getId());
                    break;
            }
        } while (choice != 0);
    }
}