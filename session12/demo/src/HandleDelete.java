import utils.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class HandleDelete {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id muon xoa:");
        String idDelete = sc.nextLine();
        try (Connection connection= DatabaseConnection.openConnection()){
            CallableStatement callableStatement = connection.prepareCall("{call proc_delete_doctor_by_id(?)}");
            callableStatement.setString(1, idDelete);
            int row = callableStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Xoa thanh cong");
            } else {
                System.out.println("Khong tim thay id de xoa");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
