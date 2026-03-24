import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //tao database hospital
        //tao bang doctor (id, name, gender, age, department,
        //b1
        try (Connection conn = DatabaseConnection.openConnection()){
            //b2
            String sql = "insert into doctors values (?,?,?,?,?)";
            //b3
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //b4
            System.out.println("Nhap id: ");
            preparedStatement.setString(1, sc.nextLine());
            System.out.println("Nhap ten bac si: ");
            preparedStatement.setString(2, sc.nextLine());
            System.out.println("Nhap gioi tinh: ");
            preparedStatement.setString(3, sc.nextLine());
            System.out.println("Nhap tuoi:");
            preparedStatement.setInt(4, Integer.parseInt(sc.nextLine()));
            System.out.println("Nhap khoa: ");
            preparedStatement.setString(5, sc.nextLine());
            //b5
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}