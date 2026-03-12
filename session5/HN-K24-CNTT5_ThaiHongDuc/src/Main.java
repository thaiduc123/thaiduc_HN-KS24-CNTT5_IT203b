import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        int choice;
        do{
            System.out.println("""
                    1. Thêm sản phẩm mới
                    2. Hiển thị danh sách sản phẩm
                    3. Cập nhật số lượng theo ID
                    4. Xóa sản phẩm đã hết hàng
                    5. Thoát chương trình
                    """);
            System.out.print("Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
//                    try {
                        System.out.println("Nhập mã sản phẩm: ");
                        String id = sc.nextLine();
                        System.out.println("Nhập tên sản phẩm: ");
                        String name = sc.nextLine();
                        System.out.println("Nhập giá sản phẩm: ");
                        double price = Double.parseDouble(sc.nextLine());
                        System.out.println("Nhập số lượng sản phẩm: ");
                        int quantity = Integer.parseInt(sc.nextLine());
                        System.out.println("Nhập danh mục: ");
                        String category = sc.nextLine();
//                    }
//                    catch (InvalidProductException e){
//                    System.out.println("Lỗi" +e.getMessage());
//                    e.printStackTrace();
//                    }
                    products.add(new Product(id, name, price, quantity, category));
                    break;
                case 2:
                    products.forEach(product -> {
                        System.out.println("ID: " + product.id +
                                "| Tên sp: " + product.name +
                                "| Giá: "+ product.price +
                                "| Số lượng: " + product.quantity +
                                "| Danh mục: " + product.category);
                    });
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while(choice!=5);
    }
}