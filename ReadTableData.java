// wap which reads the table name from the user as command line argument and print all the data of that table using the ResultSetMetaData in jdbc
import java.sql.*;

public class ReadTableData {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the table name as a command line argument.");
            return;
        }

        String tableName = args[0];

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "root", "Akash@123")) {
            String query = "SELECT * FROM " + tableName;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                ResultSetMetaData metaData = resultSet.getMetaData();

                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnLabel(i) + "\t");
                }
                System.out.println();

                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}