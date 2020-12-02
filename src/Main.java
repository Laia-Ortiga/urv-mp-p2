import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(5);
        a.add(6);
        System.out.println("The set a contains 6" + a.contains(6));
        System.out.println("The set a contains 9 before insertion" + a.contains(9));
        a.add(9);
        System.out.println("The set a contains 9 after insertion" + a.contains(9));
        a.add(10);
        a.add(30);
        ArrayList<Integer> b = new ArrayList<>();
        System.out.println("The intersection of a and b is" + a.retainAll(b) + "\n");
        b.add(5);
        System.out.println("The set a contains b as a subset: "+ a.containsAll(b) +"\n");
        System.out.println("The intersection of a and b is" + a.retainAll(b) + "\n");
        b.add(6);
        System.out.println("The set a contains b as a subset: "+ a.containsAll(b) +"\n");
        System.out.println("The intersection of a and b is" + a.retainAll(b) + "\n");
        b.add(7);
        b.add(33);
        System.out.println("The set a contains b as a subset: "+ a.containsAll(b) +"\n");
        System.out.println("The intersection of a and b is" + a.retainAll(b) + "\n");


        ArrayList<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(5);
        a.add(6);
        a.add(9);
        a.add(10);
        a.add(30);
        ArrayList<Integer> b = new ArrayList<>();
        b.add(5);
        b.add(6);
        b.add(7);
        b.add(33);
        ListSet set = new ListSet(a);
        System.out.println(set);
        ListSet removalSet = new ListSet(b);
        set.removeAll(removalSet);
        System.out.println(set);
        System.out.println(removalSet);
    }
}
