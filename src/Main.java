import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
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
