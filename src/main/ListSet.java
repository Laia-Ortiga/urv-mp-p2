package main;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * axsor: Borreu-la si voleu, però m'agrada poder llegir el codi
     */
    public boolean isNotEmpty() {
        return !list.isEmpty();
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

    /**
     * Adds new element into the list sorting it ascending by value.
     *
     * @param number Element to be added
     */
    public boolean add(Integer number) {
        int index = Collections.binarySearch(list, number);

        if (index < 0) {
            list.add(-index - 1, number);
            return true;
        }

        return false;
    }

    /**
     * Adds new elements into the list.
     *
     * @param listSet Elements to be added
     */
    public boolean addAll(ListSet listSet) {
        boolean changed = false;

        if (isEmpty()) {
            list.addAll(listSet.list);
            changed = true;
        } else {
            int compareResult, i = 0, j = 0;

            while (i < size() && j < listSet.size()) {
                compareResult = list.get(i).compareTo(listSet.get(j));

                if (compareResult == 1) {
                    list.add(i, listSet.get(j));
                    changed = true;
                }

                if (compareResult != -1) i++;

                j++;
            }
        }

        return changed;
    }

    /**
     * Sets an element at the specified position of `list` moving one position the rest of the greatest elements.
     *
     * @param index  Position where to place the element
     * @param number Element to place
     */
    private void set(int index, Integer number) {
        int originalSize = size();

        list.add(list.get(originalSize - 1));

        for (int j = originalSize - 1; j > index; j--) list.set(j, list.get(j - 1));

        list.set(index, number);
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
