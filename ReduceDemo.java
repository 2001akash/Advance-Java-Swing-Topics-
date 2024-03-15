import java.util.*;

class ReduceDemo
{
static public void main(String [] rk)
{
ArrayList<Integer> al = new ArrayList<>();
al.add(6); al.add(2); al.add(4);
al.add(30); al.add(7); al.add(5);

Optional <Integer> op = al.stream().filter(a -> a%2 == 1).reduce((x, y) -> x+y);
/* if(op.isPresent())
System.out.println(op.get());
else System.out.println("No Result");
*/
op.ifPresent(x -> System.out.println(x));
}
}