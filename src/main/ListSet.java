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
     * Adds new element into the list sorting by value.
     *
     * @param number Element to add
     */
    public void add(Integer number) {
        if (isEmpty())
            list.add(number);
        else {
            int compareResult;

            loop:
            for (int i = 0; i < size(); i++) {
                compareResult = list.get(i).compareTo(number);

                switch (compareResult) {
                    case -1:
                        if (i == size() - 1) {
                            list.add(number);
                            break loop;
                        }
                        break;
                    case 0:
                        break loop;
                    case 1:
                        set(i, number);
                        break loop;
                }
            }
        }
    }

    /**
     * Adds new elements into the list.
     *
     * @param listSet Elements to add
     */
    public void addAll(ListSet listSet) {
        if (listSet.isNotEmpty()) {
            if (isEmpty())
                for (Integer number : listSet) list.add(number);
            else {
                int compareResult, i = 0, j = 0;

                while (i < size() && j < listSet.size()) {
                    compareResult = list.get(i).compareTo(listSet.get(j));

                    switch (compareResult) {
                        case 1:
                            set(i, listSet.get(j));
                        case 0:
                            i++; j++;
                            break;
                        case -1:
                            j++;
                            break;
                    }
                }
            }
        }
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
