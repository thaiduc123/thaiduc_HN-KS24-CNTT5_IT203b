package b2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Service service = new Service();
        System.out.print("Nhập patient_id: ");
        int patientId = sc.nextInt();
        System.out.print("Nhập invoice_id: ");
        int invoiceId = sc.nextInt();
        System.out.print("Nhập số tiền: ");
        double amount = sc.nextDouble();
        service.thanhToan(patientId, invoiceId, amount);
    }
}