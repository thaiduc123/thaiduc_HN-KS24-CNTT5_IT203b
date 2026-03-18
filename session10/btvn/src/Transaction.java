import java.util.Scanner;

public class Transaction {
    private String transactionId;
    private String studentName;
    private String message;
    private double amount;

    public Transaction() {
    }

    public Transaction(String transactionId, String studentName, String message, double amount) {
        this.transactionId = transactionId;
        this.studentName = studentName;
        this.message = message;
        this.amount = amount;
    }

    // Getter/Setter
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {
        System.out.print("Nhập mã GD: ");
        this.transactionId = scanner.nextLine();

        do {
            System.out.print("Nhập tên người đóng góp: ");
            this.studentName = scanner.nextLine();
            if (studentName.trim().isEmpty()) {
                System.err.println("Tên không được để trống!");
            }
        } while (studentName.trim().isEmpty());

        System.out.print("Nhập lời nhắn: ");
        this.message = scanner.nextLine();

        do {
            System.out.print("Nhập số tiền: ");
            this.amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.err.println("Số tiền phải > 0!");
            }
        } while (amount <= 0);
    }

    // Hiển thị
    public void displayData() {
        System.out.printf("| %-6s | %-20s | %-20s | %-10.2f |\n",
                transactionId, studentName, message, amount);
    }
}