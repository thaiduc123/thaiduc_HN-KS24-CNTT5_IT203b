import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Chuan bi ket noi");
        Connection con = DatabaseConnection.openConnection();
        String sql = "SELECT * FROM students";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("NAME: " + rs.getString("full_name"));
                System.out.println("AGE: "+ rs.getInt("age"));
                System.out.println("ADDRESS:" + rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Da ket noi");
    }
}