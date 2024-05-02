import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
class JDBCTest {
    public static void main(String[] rk) {
    {
        Scanner sc=new Scanner(System.in);
        
    }


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/KE019", "root",
                    "Akash@123");

            

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
