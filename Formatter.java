import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Formatter {
    public static void main(String[] args) {
        System.out.println("Enter dob: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.next(); // yyyy-MM-dd

        // to set format to dd-MMM-yyyy
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        // check the characters in Oracle Docs for dateTimeFormatter as small and big characters
        // have different meanings
        LocalDate dt = LocalDate.parse(str, format);
        System.out.println(dt);

        // to print in the required format i.e. dd-MMM-yy or something else
        String result = dt.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        System.out.println(result);

        LocalTime time = LocalTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm:a");
        System.out.println(time.format(f));
        sc.close();
    }
}