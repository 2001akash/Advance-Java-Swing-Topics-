import java.util.*;
import java.util.stream.*;

class StreamDemo1 {
    public static void main(String... rk) {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(5);
        al.add(4);
        al.add(10);
        al.add(3);
        al.add(10);
        al.add(5);

        Stream<Integer> str = al.stream();
        str = str.distinct().sorted(Comparator.reverseOrder()); // Sort in descending order
        str.forEach(x -> System.out.println(x));

        System.out.println(al);
    }
}
