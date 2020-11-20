package main;

public class Main {

    public static void main(String[] args) {

        ListSet listSet = new ListSet();
        // Proves add
        System.out.println(listSet);
        listSet.add(1);
        listSet.add(3);
        System.out.println(listSet);

        // proves addAll
        System.out.println(listSet);
        ListSet listSetAddAll = new ListSet();
        listSetAddAll.add(2);
        listSetAddAll.add(3);
        listSet.addAll(listSetAddAll);
        System.out.println(listSet);
    }

}
