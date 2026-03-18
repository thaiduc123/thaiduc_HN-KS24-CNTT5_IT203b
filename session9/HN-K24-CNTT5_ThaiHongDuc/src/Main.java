
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();
        int choice = 0;
        do {
            System.out.println("\nQUẢN LÝ SẢN PHẨM");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();
                    System.out.print("Nhập giá: ");
                    double price = Double.parseDouble(sc.nextLine());
                    System.out.print("Loại sản phẩm (1: Vật lý, 2: Kỹ thuật số): ");
                    int type = Integer.parseInt(sc.nextLine());
                    if (type ==1){
                        System.out.print("Nhập trọng lượng (Kg): ");
                    } else {
                        System.out.print("Nhập dung lượng (MB): ");
                    }
                    double extra = Double.parseDouble(sc.nextLine());
                    Product p = ProductFactory.createProduct(type, id, name, price, extra);
                    db.addProduct(p);
                    break;
                case 2:
                    db.listProducts();
                    break;
                case 3:
//                    System.out.print("Nhập ID sản phẩm cần cập nhật: ");
//                    String updateId = sc.nextLine();
//                    System.out.print("Tên mới: ");
//                    String newName = sc.nextLine();

                    break;
                case 4:
                    System.out.print("Nhập ID sản phẩm cần xoá: ");
                    String deleteId = sc.nextLine();
                    db.deleteProduct(deleteId);
                    break;
                case 5:
                    sc.close();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        } while (choice<=5);
    }
}