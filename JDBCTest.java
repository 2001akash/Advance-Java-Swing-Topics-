import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class JDBCTest {
    public static void main(String[] rk) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/KE019", "root", "Akash@123");

            Statement stmt = con.createStatement();

            stmt.executeUpdate("insert into student values (\"Ravi Kant Sahu\", 535, 7.98)");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
