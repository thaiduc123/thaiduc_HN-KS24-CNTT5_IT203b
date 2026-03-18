import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
class ProductDatabase {
    public static ProductDatabase instance;
    List<Product> products;
    ProductDatabase() {
        products = new ArrayList<>();
    }
    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }
    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getId().equals(product.getId()) || p.getName().equalsIgnoreCase(product.getName())) {
                System.out.println("Sản phẩm đã tồn tại");
                return;
            }
        }
        products.add(product);
        System.out.println("Đã thêm sản phẩm thành công");
    }
    public void updateProduct(String id, String newName, double newPrice) {
    }
    public void deleteProduct(String id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getId().equals(id)) {
                iterator.remove();
                System.out.println("Đã xoá sản phẩm");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm");
    }
    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống");
        } else {
            System.out.println("Danh sách sản phẩm:");
            for (Product p : products) {
                p.displayInfo();
            }
        }
    }
}
