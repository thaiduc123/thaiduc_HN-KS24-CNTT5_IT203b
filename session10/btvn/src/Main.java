
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TransactionBusiness service = TransactionBusiness.getInstance();

        while (true) {
            System.out.println("\n===== QUẢN LÝ QUỸ =====");
            System.out.println("1. Hiển thị");
            System.out.println("2. Thêm");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm theo tên");
            System.out.println("6. Lọc >250k");
            System.out.println("7. Sắp xếp");
            System.out.println("8. Thống kê");
            System.out.println("9. Thoát");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    service.displayAll();
                    break;

                case 2:
                    do {
                        Transaction t = new Transaction();
                        t.inputData(scanner);

                        if (service.add(t)) {
                            System.out.println("Thêm thành công!");
                        }

                        System.out.print("Tiếp tục? (y/n): ");
                    } while (scanner.nextLine().equalsIgnoreCase("y"));
                    break;

                case 3:
                    System.out.print("Nhập ID: ");
                    service.update(scanner.nextLine(), scanner);
                    break;

                case 4:
                    System.out.print("Nhập ID: ");
                    service.delete(scanner.nextLine());
                    break;

                case 5:
                    System.out.print("Nhập tên: ");
                    List<Transaction> result = service.searchByName(scanner.nextLine());
                    result.forEach(Transaction::displayData);
                    break;

                case 6:
                    service.vipList().forEach(Transaction::displayData);
                    break;

                case 7:
                    service.sortDesc();
                    break;

                case 8:
                    service.statistics();
                    break;

                case 9:
                    System.exit(0);
            }
        }
    }
}