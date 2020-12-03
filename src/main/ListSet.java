package main;

import java.util.*;

public class ListSet implements Iterable<Integer> {

    private final List<Integer> list;

    public ListSet() {
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

    public boolean remove(Integer x) {
        int index = Collections.binarySearch(list, x);
        if (index >= 0) {
            list.remove(index);
            return true;
        }
        return false;
    }

    public boolean removeAll(ListSet x) {
        int prevSize = list.size();
        int i = size() - 1;
        int xIndex = x.size() - 1;

        // We iterate from back to front as removing from an ArrayList shifts elements after the one removed
        // This way it should be a little faster
        while (i >= 0 && xIndex >= 0) {
            if (list.get(i) < x.list.get(xIndex)) {
                xIndex--;
            }
            else if (list.get(i) > x.list.get(xIndex)) {
                i--;
            }
            else {
                list.remove(i);
                i--;
                xIndex--;
            }
        }
        return prevSize != size();
    }

    @Override
    public Iterator<Integer> iterator() {
        return list.iterator();
    }

    /**
     * Contains method for an element in an ordered (in ascending order) Integer ListSet.
     * It returns a boolean indicating whether an element is found or not in a ListSet.
     * It uses a binary search method to find the search element in the set so as to minimize
     * the computational cost of this method, being logarithmic instead of linear.
     * Having the ListSet sorted is one prerequisite.
     *
     * @param integerElement element to be searched in the ListSet
     * @return boolean indicating whether an element is in the ListSet or not
     */
    public boolean contains(Integer integerElement) {
        // We used the already implemented Collections class method binarySearch.
        return Collections.binarySearch(list, integerElement) >= 0;
    }

    /**
     * Contains method for a ListSet subset in an ordered (in ascending order) Integer ListSet.
     * It returns a boolean indicating whether a ListSet is a subset or not of a current ListSet.
     * It uses the method contains implemented using a binary search, so having the ListSet
     * sorted is one prerequisite.
     *
     * @param subsetCandidate to be checked whether it is a subset or not of the current ListSet
     * @return boolean indicating whether a ListSet is a subset or not
     */
    public boolean containsAll(ListSet subsetCandidate) {
        boolean result = true;
        int counter = 0;
        int lengthSubsetCandidate = subsetCandidate.list.size();
        while (result && counter < lengthSubsetCandidate) {
            if (contains(subsetCandidate.list.get(counter))) {
                result = false;
            }
            counter++;
        }
        return result;
    }

    /**
     * RetainAll method for calculating the intersection between an Integer ListSet and
     * the current ListSet.
     * It returns a boolean to indicate whether the set has changed as a result of the call.
     * It uses the method contains implemented using a binary search, so having both ListSet
     * sorted are prerequisites.
     *
     * @param intersectedSet ListSet to be intersected with the current ListSet
     * @return boolean to indicate whether the set has changed as a result of the call, that is, whether any of the elements adjoined
     * in the new set were not already present in the original set
     */
    public boolean retainAll(ListSet intersectedSet) {
        boolean changed = false;

        int i = 0;
        while (i < list.size()) {                             // Equivalent to calculating A\(A\B) and leaving the result in list
            Integer value = list.get(i);
            if (!intersectedSet.contains(value)) {
                remove(value);
                changed = true;

            }
            else {
                i++;
            }
        }
        return changed;
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
                compareResult = list.get(i).compareTo(listSet.list.get(j));

                if (compareResult >= 1) {
                    list.add(i, listSet.list.get(j));
                    changed = true;
                }

                if (compareResult >= 0) i++;

                j++;
            }
        }

        return changed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (var x : list) {
            if (x != list.get(0)) {
                sb.append(", ");
            }
            sb.append(x);
        }
        sb.append("}");
        return sb.toString();
    }
}
