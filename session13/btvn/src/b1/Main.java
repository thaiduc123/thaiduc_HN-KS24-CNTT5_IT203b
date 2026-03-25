package b1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Service service = new Service();
        System.out.print("Nhập medicine id: ");
        int medicineId = sc.nextInt();
        System.out.print("Nhập patient id: ");
        int patientId = sc.nextInt();
        service.med(medicineId, patientId);
    }
}