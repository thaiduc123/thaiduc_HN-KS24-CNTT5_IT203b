package presentation.category;

import model.Category;
import service.CategoryService;

import java.util.Scanner;

public class CategoryUI {
    private Scanner sc = new Scanner(System.in);
    private CategoryService categoryService = new CategoryService();
    public void categoryMenu() {
        int choice;
        do {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("====== CATEGORY MENU ======");
            System.out.println("1. Hiển thị");
            System.out.println("2. Thêm");
            System.out.println("3. Sửa");
            System.out.println("4. Xóa");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            System.out.println("===========================");
            switch (choice) {
                case 1:
                    showAll();
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
            }

        } while (choice != 0);
    }

    private void showAll() {
        var list = categoryService.getAll();
        System.out.println("\n");
        System.out.println("================================");
        System.out.printf("%-15s %-20s\n", "ID", "NAME");
        for (var c : list) {
            System.out.println("--------------------------------");
            System.out.printf("%-15s %-20s\n", c.getId(), c.getName());
        }
        System.out.println("================================");
        System.out.println("\n");
    }
    private void add() {
        String id;
        System.out.print("ID (CAxxx): ");
        while (true) {
            id = sc.nextLine();
            if (!id.matches("CA\\d{3}")) {
                System.err.println("Sai định dạng! (VD: CA001). Vui lòng nhập lại: ");
                continue;
            }
            if (categoryService.existsActiveById(id)) {
                System.err.println("ID đã tồn tại! Vui lòng nhập lại: ");
                continue;
            }
            break;
        }
        String name;
        while (true) {
            System.out.print("Name: ");
            name = sc.nextLine();
            boolean exists = false;
            for (Category c : categoryService.getAll()) {
                if (c.getName().equalsIgnoreCase(name)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                System.err.println("Tên đã tồn tại!");
            } else {
                break;
            }
        }

        Category c = new Category(id, name);
        if (categoryService.add(c)) {
            System.out.println("Thêm thành công!");
        } else {
            System.out.println("Thêm thất bại!");
        }
    }
    private void update() {
        String id;
        System.out.print("Nhập ID cần sửa: ");
        while (true) {
            id = sc.nextLine();
            if (!id.matches("CA\\d{3}")) {
                System.err.println("ID sai định dạng! Vui lòng nhập lại: ");
                continue;
            }
            if (!categoryService.existsActiveById(id)) {
                System.err.println("ID không tồn tại! Vui lòng nhập lại: ");
                continue;
            }
            break;
        }

        System.out.print("Tên mới: ");
        String name;
        while (true) {
            name = sc.nextLine();
            boolean exists = false;
            for (Category c : categoryService.getAll()) {
                if (c.getName().equalsIgnoreCase(name)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                System.err.println("Tên đã tồn tại!");
            } else {
                break;
            }
        }
        if (categoryService.update(new Category(id, name))) {
            System.out.println("Sửa thành công!");
        } else {
            System.out.println("Sửa thất bại!");
        }
    }
    private void delete() {
        String id;
        System.out.print("Nhập ID cần xóa: ");
        while (true) {
            id = sc.nextLine();
            if (!id.matches("CA\\d{3}")) {
                System.err.println("ID sai định dạng! Vui lòng nhập lại: ");
                continue;
            }
            if (!categoryService.existsActiveById(id)) {
                System.err.println("ID không tồn tại!  Vui lòng nhập lại: ");
                continue;
            }
            break;
        }
        if (categoryService.delete(id)) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa thất bại!");
        }
    }
}