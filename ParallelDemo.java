import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

class ParallelDemo {
    static public void main(String[] args) {
        List<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(1);
        al.add(3);
        al.add(4);
        al.add(5);
        Stream<Integer> str = al.parallelStream();
        if (str.isParallel())
            System.out.println("Parallel Stream");
        int result = str.reduce(0, (a, b) -> a + (b * b), (a, b) -> a + b);
        System.out.println(result);

    }

}
