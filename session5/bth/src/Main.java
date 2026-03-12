import model.*;
import repository.MenuRepository;
import service.MenuService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MenuRepository repo = new MenuRepository();
        MenuService service = new MenuService(repo);

        int choice;

        do {

            System.out.println("\n========= MENU MANAGEMENT =========");
            System.out.println("1. Thêm món ăn");
            System.out.println("2. Sửa món ăn");
            System.out.println("3. Xóa món ăn");
            System.out.println("4. Tìm kiếm món ăn");
            System.out.println("5. Hiển thị danh sách món");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addMenuItem(sc, service);
                    break;

                case 2:
                    updateMenuItem(sc, service);
                    break;

                case 3:
                    deleteMenuItem(sc, service);
                    break;

                case 4:
                    searchMenuItem(sc, service);
                    break;

                case 5:
                    displayMenu(service);
                    break;

                case 0:
                    System.out.println("Thoát chương trình...");
                    break;

                default:
                    System.out.println("Chức năng không hợp lệ");
            }

        } while (choice != 0);
    }

    // ---------------- ADD ----------------

    private static void addMenuItem(Scanner sc, MenuService service) {

        System.out.println("1. Món chính");
        System.out.println("2. Đồ uống");
        System.out.println("3. Tráng miệng");

        System.out.print("Chọn loại món: ");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Tên món: ");
        String name = sc.nextLine();

        System.out.print("Giá: ");
        double price = sc.nextDouble();
        sc.nextLine();

        MenuItem item = null;

        switch (type) {

            case 1:
                item = new Food(id, name, price);
                break;

            case 2:
                System.out.print("Size (S/M/L): ");
                String size = sc.nextLine();
                item = new Drink(id, name, price, size);
                break;

            case 3:
                item = new Dessert(id, name, price);
                break;

            default:
                System.out.println("Loại không hợp lệ");
                return;
        }

        service.addMenuItem(item);

        System.out.println("Thêm món thành công!");
    }

    // ---------------- UPDATE ----------------

    private static void updateMenuItem(Scanner sc, MenuService service) {

        System.out.print("Nhập ID món cần sửa: ");
        String id = sc.nextLine();

        Optional<MenuItem> itemOpt = service.findById(id);

        if (!itemOpt.isPresent()) {
            System.out.println("Không tìm thấy món!");
            return;
        }

        MenuItem item = itemOpt.get();

        System.out.print("Tên mới: ");
        String newName = sc.nextLine();

        System.out.print("Giá mới: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();

        item.setName(newName);
        item.setPrice(newPrice);

        System.out.println("Cập nhật thành công!");
    }

    // ---------------- DELETE ----------------

    private static void deleteMenuItem(Scanner sc, MenuService service) {

        System.out.print("Nhập ID món cần xóa: ");
        String id = sc.nextLine();

        boolean removed = service.deleteById(id);

        if (removed)
            System.out.println("Xóa thành công");
        else
            System.out.println("Không tìm thấy món");
    }

    // ---------------- SEARCH ----------------

    private static void searchMenuItem(Scanner sc, MenuService service) {

        System.out.print("Nhập tên món cần tìm: ");
        String name = sc.nextLine();

        List<MenuItem> result = service.searchByName(name);

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy món");
            return;
        }

        result.forEach(System.out::println);
    }

    // ---------------- DISPLAY ----------------

    private static void displayMenu(MenuService service) {

        List<MenuItem> list = service.getAll();

        if (list.isEmpty()) {
            System.out.println("Menu trống");
            return;
        }

        list.forEach(System.out::println);
    }
}