package presentation.customer;

import model.Product;
import service.ProductService;

import java.util.List;

public class ProductCustomerUI {
    private ProductService service = new ProductService();
    public void showAvailableProducts() {
        List<Product> list = service.getAvailableProducts();
        if (list.isEmpty()) {
            System.out.println("\n");
            System.out.println("=====================================================================");
            System.out.println("----------------- Không có sản phẩm nào còn hàng! -------------------");
            System.out.println("=====================================================================");
            return;
        }
        System.out.println("\n");
        System.out.println("==========================================================================");
        System.out.printf("%-9s %-20s %-10s %-8s %-8s %-10s %-10s\n",
                "ID","NAME","BRAND","STOR","COLOR","PRICE","STOCK");
        for (Product p : list) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("%-9s %-20s %-10s %-8s %-8s %-10.0f %-10d\n",
                    p.getId(), p.getName(), p.getBrand(),
                    p.getStorage(), p.getColor(),
                    p.getPrice(), p.getStock());
        }
        System.out.println("==========================================================================");
        System.out.println("\n");
    }
}