class Multithreading1
{
 public static void main(String[] rk)
{
Thread t1 = new Thread(
() -> { for(int i=1; i<51; i++) { System.out.print(i);
try { Thread.sleep(10); }
catch(Exception ex) { System.out.println(ex); }
}
}
);

Thread t2 = new Thread(
() -> { for(int i=65; i<91; i++) { System.out.print((char)i);
try { Thread.sleep(100); }
catch(Exception ex) { System.out.println(ex); } }
}
);
t1.start(); t2.start();
}
}