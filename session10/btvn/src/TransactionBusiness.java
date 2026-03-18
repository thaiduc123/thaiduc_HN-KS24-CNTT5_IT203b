import java.util.*;
import java.util.stream.Collectors;

public class TransactionBusiness {

    private static TransactionBusiness instance;

    private List<Transaction> list;

    private TransactionBusiness() {
        list = new ArrayList<>();
    }

    // Singleton
    public static TransactionBusiness getInstance() {
        if (instance == null) {
            instance = new TransactionBusiness();
        }
        return instance;
    }

    // Hiển thị
    public void displayAll() {
        if (list.isEmpty()) {
            System.out.println("Quỹ chưa có giao dịch!");
            return;
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-20s | %-20s | %-10s |\n", "ID", "Tên", "Lời nhắn", "Số tiền");
        System.out.println("--------------------------------------------------------------------------");

        list.forEach(Transaction::displayData);
    }

    // Thêm
    public boolean add(Transaction t) {
        boolean exists = list.stream()
                .anyMatch(tr -> tr.getTransactionId().equals(t.getTransactionId()));

        if (exists) {
            System.err.println("Trùng mã giao dịch!");
            return false;
        }

        list.add(t);
        return true;
    }

    // Tìm theo ID
    public Optional<Transaction> findById(String id) {
        return list.stream()
                .filter(t -> t.getTransactionId().equals(id))
                .findFirst();
    }

    // Cập nhật
    public void update(String id, Scanner scanner) {
        Optional<Transaction> optional = findById(id);

        if (!optional.isPresent()) {
            System.err.println("Không tìm thấy!");
            return;
        }

        Transaction t = optional.get();

        System.out.println("1. Sửa tên");
        System.out.println("2. Sửa lời nhắn");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Nhập tên mới: ");
                t.setStudentName(scanner.nextLine());
                break;
            case 2:
                System.out.print("Nhập lời nhắn mới: ");
                t.setMessage(scanner.nextLine());
                break;
        }
    }

    // Xóa
    public void delete(String id) {
        boolean removed = list.removeIf(t -> t.getTransactionId().equals(id));
        if (!removed) {
            System.err.println("Không tìm thấy mã GD!");
        }
    }

    // Tìm theo tên
    public List<Transaction> searchByName(String name) {
        List<Transaction> result = list.stream()
                .filter(t -> t.getStudentName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.err.println("Không tìm thấy!");
        }

        return result;
    }

    // Sắp xếp
    public void sortDesc() {
        list = list.stream()
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .collect(Collectors.toList());
    }

    // Lọc >250k
    public List<Transaction> vipList() {
        return list.stream()
                .filter(t -> t.getAmount() > 250000)
                .collect(Collectors.toList());
    }

    // Thống kê
    public void statistics() {
        double total = list.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        Optional<Transaction> max = list.stream()
                .max(Comparator.comparingDouble(Transaction::getAmount));

        System.out.println("Tổng quỹ: " + total);

        max.ifPresent(t ->
                System.out.println("Top đóng góp: " + t.getStudentName() + " - " + t.getAmount()));
    }
}
