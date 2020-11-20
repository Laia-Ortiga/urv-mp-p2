package main;

import java.util.ArrayList;
import java.util.Iterator;

public class ListSet implements Iterable<Integer> {

    private final ArrayList<Integer> list = new ArrayList<>();

    public ListSet() {

    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public Integer get(int index) {
        return list.get(index);
    }

    public void add(Integer number) {
        if (isEmpty()) list.add(number);
        else {
            int compareResult;
            for (int i = 0; i < this.size(); i++) {

                compareResult = list.get(i).compareTo(number);

                if (compareResult == 0) break;

                if (compareResult == -1) {
                    if (i == this.size() - 1) {
                        list.add(number);
                        break;
                    }
                }

                if (compareResult == 1) {
                    int originalSize = this.size();
                    for (int j = originalSize; j > i; j--) {
                        if (j == originalSize) list.add(list.get(j - 1));
                        else list.set(j, list.get(j - 1));
                    }
                    list.set(i, number);
                    break;
                }
            }
        }
    }

    public void addAll(ListSet listSet) {
        list.addAll(listSet.getList());
    }

    @Override
    public Iterator<Integer> iterator() {
        return list.iterator();
//        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "main.ListSet{" +
            "list=" + list +
            '}';
    }
}
