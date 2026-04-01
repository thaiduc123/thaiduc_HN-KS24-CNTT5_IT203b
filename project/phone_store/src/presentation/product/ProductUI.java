package presentation.product;

import dao.ProductDAO;
import model.Product;
import service.CategoryService;
import service.ProductService;
import java.util.List;
import java.util.Scanner;

public class ProductUI {
    private ProductDAO dao = new ProductDAO();
    private ProductService service = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private Scanner sc = new Scanner(System.in);
    public void showMenu() {
        int choice;
        do {
            System.out.println("==========================");
            System.out.println("====== PRODUCT MENU ======");
            System.out.println("1. Hiển thị");
            System.out.println("2. Thêm");
            System.out.println("3. Sửa");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println("==========================");

            switch (choice) {
                case 1:
                    show(service.getAll());
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    search();
                    break;
            }

        } while (choice != 0);
    }
    private void show(List<Product> list) {
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
    }
//    public boolean add(Product p) {
//        if (p.getPrice() <= 0 || p.getStock() <= 0) {
//            System.out.println("Giá và số lượng phải > 0");
//            return false;
//        }
//
//        p.setId(service.generateId());
//        return dao.insert(p);
//    }
private void add() {
    Product p = new Product();
    System.out.print("ID (PAxxxxx): ");
    while (true) {
        String id = sc.nextLine();
        if (!id.matches("PA\\d{5}")) {
            System.err.println("Sai định dạng! (VD: PA00001).  Vui lòng nhập lại: ");
            continue;
        }
        if (service.findById(id) != null) {
            System.err.println("ID đã tồn tại! Vui lòng nhập lại: ");
            continue;
        }
        p.setId(id);
        break;
    }
    while (true) {
        System.out.print("Category ID (CAxxx): ");
        String catId = sc.nextLine();
        if (!categoryService.existsActiveById(catId)) {
            System.err.println("Category không tồn tại! Vui lòng nhập lại: ");
            continue;
        }
        p.setCategoryId(catId);
        break;
    }
    System.out.print("Name: ");
    p.setName(sc.nextLine());

    System.out.print("Brand: ");
    p.setBrand(sc.nextLine());

    System.out.print("Storage: ");
    p.setStorage(sc.nextLine());

    System.out.print("Color: ");
    p.setColor(sc.nextLine());

    while (true) {
        try {
            System.out.print("Price (>0): ");
            double price = Double.parseDouble(sc.nextLine());
            System.out.print("Stock (>0): ");
            int stock = Integer.parseInt(sc.nextLine());
            if (price <= 0 || stock <= 0) {
                System.out.println("Giá và số lượng phải > 0. Vui lòng nhập lại: ");
                continue;
            }
            p.setPrice(price);
            p.setStock(stock);
            break;
        } catch (Exception e) {
            System.err.println("Giá hoặc số lượng không hợp lệ!");
        }
    }
    System.out.print("Description: ");
    p.setDescription(sc.nextLine());
    if (service.add(p)) {
        System.out.println("Thêm thành công!");
    } else {
        System.out.println("Thêm thất bại!");
    }
}

    private void update() {
        System.out.print("Nhập ID cần sửa: ");
        String id = sc.nextLine();
        Product old = service.findById(id);
        if (old == null) {
            System.err.println("Không tìm thấy sản phẩm! Vui lòng nhập lại: ");
            return;
        }

        System.out.println("Thông tin hiện tại:");
        System.out.println("Name: " + old.getName());
        System.out.println("Brand: " + old.getBrand());
        System.out.println("Price: " + old.getPrice());
        System.out.println("Stock: " + old.getStock());

        System.out.print("Name mới: ");
        old.setName(sc.nextLine());

        System.out.print("Brand mới: ");
        old.setBrand(sc.nextLine());

        System.out.print("Storage mới: ");
        old.setStorage(sc.nextLine());

        System.out.print("Color mới: ");
        old.setColor(sc.nextLine());

        while (true) {
            try {
                System.out.print("Price mới (>0): ");
                double price = Double.parseDouble(sc.nextLine());
                System.out.print("Stock mới (>0): ");
                int stock = Integer.parseInt(sc.nextLine());
                if (price <= 0 || stock <= 0) {
                    System.out.println("Giá và số lượng phải > 0. Vui lòng nhập lại: ");
                    continue;
                }
                old.setPrice(price);
                old.setStock(stock);
                break;
            } catch (Exception e) {
                System.err.println("Giá hoặc số lượng không hợp lệ!");
            }
        }

        System.out.print("Description mới: ");
        old.setDescription(sc.nextLine());

        if (service.update(old)) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }
    }

    private void delete() {
        String id;
        System.out.print("Nhập ID: ");
        while (true) {
            id = sc.nextLine();
            if (!id.matches("PA\\d{5}")) {
                System.err.println("Sai định dạng! (VD: PA00001). Vui lòng nhập lại: ");
                continue;
            }
            if (service.findById(id) == null) {
                System.err.println("Không tìm thấy sản phẩm! Vui lòng nhập lại: ");
                continue;
            }
            break;
        }
        System.out.print("Bạn chắc chắn xóa? (Y/N): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            service.delete(id);
            System.out.println("Đã xóa!");
        } else if (confirm.equalsIgnoreCase("N")){
            System.out.println("Đã huỷ xoá!");
        } else {
            System.out.print("Lựa chọn sai!");
        }
    }
    private void search() {
        System.out.print("Nhập tên: ");
        String key = sc.nextLine();

        show(service.search(key));
    }
}
