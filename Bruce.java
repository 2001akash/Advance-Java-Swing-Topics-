/*
Bruce Lee is working in an IT company which is providing the Holidays on all Sundays and
2nd and 4th Saturday.
Bruce Lee wanted to know the Holidays in the Month in which his Birthday Falls so that
he can plan a long weekend Holidays.
Help Bruce Lee to find out the dates and Days of Holiday of that Month.
Read the DoB from Command Line Arguments.

Input Format:	2002-10-05
OutFormat: 2002-10-03	Sunday
		   2002-10-09	Saturday
		   2002-10-10	Sunday
		   2002-10-17	Sunday
		   2002-10-23	Saturday
		   2002-10-24	Sunday
		   2002-10-31	Sunday
*/

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class Bruce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your birthday (yyyy-MM-dd): ");
        String birthday = scanner.nextLine();

        // Parsing birthday
        LocalDate birthDate = LocalDate.parse(birthday);

        // Get the month and year from the birth date
        int year = birthDate.getYear();
        int month = birthDate.getMonthValue();

        // Get the first and last day of the birth month
        LocalDate firstDay = LocalDate.of(year, month, 1);
        LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());

        // Variable to count 2nd and 4th Saturdays
        int saturdayCount = 0;

        // Loop through the days of the month
        while (!firstDay.isAfter(lastDay)) {
            DayOfWeek dayOfWeek = firstDay.getDayOfWeek();

            // Check if the day is Sunday or 2nd/4th Saturday
            if (dayOfWeek == DayOfWeek.SUNDAY
                    || (dayOfWeek == DayOfWeek.SATURDAY && (saturdayCount == 1 || saturdayCount == 3))) {
                // Print the date and day of the week
                System.out.println(firstDay.format(DateTimeFormatter.ISO_DATE) + "\t" + dayOfWeek);
            }

            // Increment the count if it's Saturday
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                saturdayCount++;
            }

            // Move to the next day
            firstDay = firstDay.plusDays(1);
            scanner.close();
        }
    }
}
/*
 * class bruce {
 * public static void main(String[] args) {
 * System.out.println("Enter your Birthday: ");
 * // 2002-10-05
 * Scanner scanner = new Scanner(System.in);
 * 
 * String birthday = scanner.nextLine();
 * String[] parts = birthday.split("-");
 * int year = Integer.parseInt(parts[0]);
 * int month = Integer.parseInt(parts[1]);
 * 
 * LocalDate firstDay = LocalDate.of(year, month,
 * 1).with(TemporalAdjusters.firstDayOfMonth());
 * LocalDate lastDay = LocalDate.of(year, month,
 * 1).with(TemporalAdjusters.lastDayOfMonth());
 * 
 * LocalDate currentDate = firstDay;
 * // System.out.println(currentDate.plusDays(1).getDayOfMonth());
 * // (currentDate.with(TemporalAdjusters.dayOfWeekInMonth())
 * int var =0;
 * while (!currentDate.isAfter(lastDay)){
 * DayOfWeek day = currentDate.getDayOfWeek();
 * 
 * 
 * if (day == DayOfWeek.SUNDAY) {
 * System.out.println(currentDate + "\t" + day);
 * }
 * else if (day ==DayOfWeek.SATURDAY) {
 * var+=1;
 * if (var == 2) {
 * System.out.println(currentDate + "\t" + day);
 * }
 * else if (var ==4) {
 * System.out.println(currentDate + "\t" + day);
 * }
 * }
 * currentDate = currentDate.plusDays(1);
 * }
 * 
 * }
 * }
 */