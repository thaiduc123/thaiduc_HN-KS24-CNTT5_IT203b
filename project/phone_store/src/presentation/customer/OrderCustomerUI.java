package presentation.customer;
import dao.CartDAO;
import model.CartItem;
import model.Order;
import model.OrderDetail;
import service.OrderService;

import java.util.List;
import java.util.Scanner;
public class OrderCustomerUI {
    private OrderService service = new OrderService();
    private CartDAO cartDAO = new CartDAO();
    private Scanner sc = new Scanner(System.in);
    public void checkout(int userId) {
        List<CartItem> cart = cartDAO.getCartByUser(userId);
        if (cart == null || cart.isEmpty()) {
            System.err.println("Giỏ hàng trống, không thể đặt hàng!");
            return;
        }
        System.out.print("Xác nhận đặt hàng? (Y/N): ");
        String confirm = sc.nextLine();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Đã huỷ!");
            return;
        }
        if (service.checkout(userId)) {
            System.out.println("Đặt hàng thành công!");
        } else {
            System.err.println("Đặt hàng thất bại!");
        }
    }
    public void showOrderHistory(int userId) {
        List<Order> orders = service.getOrdersByUser(userId);
        if (orders.isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng nào!");
            return;
        }
        for (Order o : orders) {
            System.out.println("\n====== ORDER #" + o.getId() + " ======");
            System.out.println("Ngày: " + o.getCreatedAt());
            System.out.println("Trạng thái: " + o.getStatus());
            System.out.println("Tổng tiền: " + o.getTotalAmount());
            List<OrderDetail> details = service.getOrderDetails(o.getId());
            System.out.println("----------------------------------------");
            System.out.printf("%-15s %-10s %-10s\n", "PRODUCT", "QTY", "PRICE");
            for (OrderDetail d : details) {
                System.out.println("----------------------------------------");
                System.out.printf("%-15s %-10d %-10.0f\n",
                        d.getProductName(),
                        d.getQuantity(),
                        d.getPrice());
            }
            System.out.println("========================================");
            System.out.println("\n");
        }
    }
}