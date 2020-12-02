package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSetFuzzy {

    private final List<FuzzyInteger> list;

    public ListSetFuzzy() {
        list = new ArrayList<>();
    }

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    private double remove(int index, double membership) {
        if (1 - membership < list.get(index).getMembership()) {
            if (membership == 1.0) {
                list.remove(index);
                return 1.0;
            }
            else {
                double oldMembership = list.get(index).getMembership();
                list.set(index, new FuzzyInteger(list.get(index).getValue(), 1 - membership));
                return oldMembership;
            }
        }
        return 0.0;
    }

    public double remove(FuzzyInteger x) {
        // search for element with same value
        int index = Collections.binarySearch(list, x);
        if (index >= 0) {
            return remove(index, x.getMembership());
        }
        return 0.0;
    }

    public double removeAll(ListSetFuzzy x) {
        double result = 1.0;
        int i = size() - 1;
        int xIndex = x.size() - 1;

        // We iterate from back to front as removing from an ArrayList shifts elements after the one removed
        // This way it should be a little faster
        while (i >= 0 && xIndex >= 0) {
            if (list.get(i).compareTo(x.list.get(xIndex)) < 0) {
                xIndex--;
            }
            else if (list.get(i).compareTo(x.list.get(xIndex)) > 0) {
                i--;
            }
            else {
                result *= remove(i, x.list.get(xIndex).getMembership());
                i--;
                xIndex--;
            }
        }
        return 1.0 - result;
    }

}
