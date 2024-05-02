import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Date {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        LocalDate birthDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();

        // Calculate age
        Period age = Period.between(birthDate, currentDate);

        System.out.println("You were born on " + birthDate.getDayOfWeek());
        System.out.println("Your age is " + age.getYears() + " years " +
                age.getMonths() + " months");

        // Get number of days in the birth month
        int daysInMonth = birthDate.getMonth().length(birthDate.isLeapYear());

        System.out.println("\nFull Month Details:");
        int dayOfWeekValue = birthDate.getDayOfWeek().getValue();
        System.out.println("Sun \tMon \tTue \tWed \tThu \tFri \tSat");

        // Print calendar for the birth month
        for (int i = 0; i < dayOfWeekValue; i++) {
            System.out.print("\t");
        }
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate currentDay = LocalDate.of(birthDate.getYear(), birthDate.getMonth(), day);
            System.out.printf("%3d \t", day);
            if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                System.out.println();
                scanner.close();
            }
        }
    }
}
