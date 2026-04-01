package presentation.customer;

import dao.ProductDAO;
import model.CartItem;
import model.Product;
import service.CartService;

import java.util.List;
import java.util.Scanner;

public class CartCustomerUI {
    private CartService service = new CartService();
    private ProductDAO productDAO = new ProductDAO();
    public void showCart(int userId) {
        List<CartItem> list = service.getCart(userId);
        if (list.isEmpty()) {
            System.out.println("\n");
            System.out.println("=====================================================================");
            System.out.println("------------------------- Giỏ hàng trống! ---------------------------");
            System.out.println("=====================================================================");
            return;
        }
        System.out.println("\n");
        System.out.println("=================== GIỎ HÀNG =======================");
        System.out.println("====================================================");
        System.out.printf("%-9s %-20s %-10s %-10s %-10s\n",
                "ID","NAME","PRICE","QTY","TOTAL");
        for (CartItem item : list) {
            System.out.println("----------------------------------------------------");
            Product p = item.getProduct();
            int qty = item.getQuantity();
            double total = p.getPrice() * qty;
            System.out.printf("%-9s %-20s %-10.0f %-10d %-10.0f\n",
                    p.getId(),
                    p.getName(),
                    p.getPrice(),
                    qty,
                    total);
        }
        System.out.println("====================================================");
        System.out.println("\n");
    }
    public void addToCart(int userId) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập Product ID: ");
        String productId = sc.nextLine();
        Product p = service.findProductById(productId);
        if (p == null) {
            System.err.println("Sản phẩm không tồn tại!");
            return;
        }
        int quantity;
        try {
            System.out.print("Nhập số lượng: ");
            quantity = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Số lượng không hợp lệ!");
            return;
        }
        if (quantity <= 0) {
            System.err.println("Số lượng phải > 0");
            return;
        }
        if (quantity > p.getStock()) {
            System.err.println("Không đủ hàng trong kho! Tồn kho hiện tại: " + p.getStock());
            return;
        }
        if (service.addToCart(userId, productId, quantity)) {
            System.out.println("Thêm vào giỏ thành công!");
        }
    }
    public void removeFromCart(int userId) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập Product ID: ");
        String productId = sc.nextLine();
        int currentQty = service.getQuantity(userId, productId);
        if (currentQty == 0) {
            System.err.println("Sản phẩm không có trong giỏ!");
            return;
        }
        int removeQty;
        try {
            System.out.print("Nhập số lượng muốn giảm: ");
            removeQty = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.err.println("Số lượng không hợp lệ!");
            return;
        }
        if (removeQty <= 0) {
            System.err.println("Số lượng phải > 0");
            return;
        }
        int newQty = currentQty - removeQty;
        if (newQty <= 0) {
            service.delete(userId, productId);
            System.out.println("Đã xóa sản phẩm khỏi giỏ!");
        } else {
            service.updateQuantity(userId, productId, newQty);
            System.out.println("Đã cập nhật số lượng còn: " + newQty);
        }
    }
}