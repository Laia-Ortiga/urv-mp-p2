package main;

import java.util.*;

/**
 * Class defining a Crisp set of Integers using a list to implement it.
 */
public class ListSet implements Iterable<Integer> {

    // Structure where elements from ListSet are stored.
    private final List<Integer> list;

    /**
     * Constructs an empty set.
     */
    public ListSet() {
        list = new ArrayList<>();
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Return the number of elements in the set.
     * @return elements in the set
     */
    public int size() {
        return list.size();
    }

    /**
     * Indicates if the set has no elements.
     * @return whether set has no elements
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Removes an element from this set.
     *
     * @param x the element to be removed
     * @return whether the element was there before doing this operation
     */
    public boolean remove(Integer x) {
        int index = Collections.binarySearch(list, x);
        if (index >= 0) {
            list.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Acts as if using the method remove for every single element of the specified set.
     *
     * @param x the list of elements to be removed from this set
     * @return whether the set was modified by this operation
     */
    public boolean removeAll(ListSet x) {
        int prevSize = list.size();
        int i = size() - 1;
        int xIndex = x.size() - 1;

        // We iterate from back to front as removing from an ArrayList shifts elements after the one removed
        // This way it should be a little faster
        while (i >= 0 && xIndex >= 0) {
            if (list.get(i) < x.list.get(xIndex)) {
                xIndex--;
            } else if (list.get(i) > x.list.get(xIndex)) {
                i--;
            } else {
                list.remove(i);
                i--;
                xIndex--;
            }
        }
        return prevSize != size();
    }

    /**
     * Method that returns a boolean indicating whether an element is found or not in a ListSet.
     * It uses a binary search method to find the search element in the set so as to minimize
     * the computational cost of this method, being logarithmic instead of linear.
     * Having the ListSet sorted is one prerequisite.
     *
     * @param integerElement element to be searched in the ListSet
     * @return boolean indicating whether an element is in the ListSet or not
     */
    public boolean contains(Integer integerElement) {
        return Collections.binarySearch(list, integerElement) >= 0;  // We use the already implemented Collections class method binarySearch.
    }

    /**
     * Method that returns a boolean indicating whether a ListSet is a subset or not of a current ListSet.
     *
     * @param subsetCandidate to be checked whether it is a subset or not of the current ListSet
     * @return boolean indicating whether a ListSet is a subset or not
     */
    public boolean containsAll(ListSet subsetCandidate) {
        int i = 0;
        int j = 0;

        // We use a linear search over two lists that are ordered in ascending order from left to right.
        while (i < size() && j < subsetCandidate.size()) {
            int compareResult = list.get(i).compareTo(subsetCandidate.list.get(j));
            i++;

            if (compareResult == 0) {
                j++;
            } else if (compareResult > 0) {
                return false;
            }
        }
        return j == subsetCandidate.size();
    }

    /**
     * RetainAll method for calculating the intersection between a ListSet and
     * the current ListSet.
     * It returns a boolean to indicate whether the set has changed as a result of the call.
     *
     * @param intersectedSet ListSet to be intersected with the current ListSet
     * @return boolean to indicate whether the set has changed as a result of the call, that is, whether any of the
     * elements adjoined in the new set were not already present in the original set
     */
    public boolean retainAll(ListSet intersectedSet) {
        boolean changed = false;
        int i = size() - 1;
        int j = intersectedSet.size() - 1;

        // We iterate from back to front as removing from an ArrayList shifts elements after the one removed
        // This way it should be a little faster
        while (i >= 0 && j >= 0) {
            int comparison = list.get(i).compareTo(intersectedSet.list.get(j));
            if (comparison == 0) {
                i--;
                j--;
            }
            else {
                if (comparison < 0) {
                    j--;
                }
                else {
                    changed = true;
                    list.remove(i);
                    i--;
                }
            }
        }
        // Erases all the elements that are still not in the intersection
        list.subList(0, i + 1).clear();
        return changed;
    }

    /**
     * Adds new element into the list sorting it ascending by value.
     *
     * @param number Element to be added
     * @return Indicates if the set has changed
     */
    public boolean add(Integer number) {
        if (number == null) return false;

        int index = Collections.binarySearch(list, number);

        // Given element gonna be added on their own position in the ListSet.
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
     * @return Indicates if the set has changed
     */
    public boolean addAll(ListSet listSet) {
        boolean changed = false;

        int compareResult, i = 0, j = 0;

        while (i < size() && j < listSet.size()) {
            compareResult = list.get(i).compareTo(listSet.list.get(j));

            // Own element is lower than given element. Search for next given element.
            if (compareResult < 0) {
                i++;
                // Both elements are the same. Search for next elements on both ListSets.
            } else if (compareResult == 0) {
                j++;
                i++;
                // Given element is greater than own element. Add it and search for next given element.
            } else {
                list.add(i, listSet.list.get(j));
                changed = true;
                j++;
            }
        }

        // Add the rest of unchecked given elements.
        changed |= list.addAll(listSet.list.subList(j, listSet.list.size()));

        return changed;
    }

    /**
     * Returns an Iterator over elements from ListSet.
     * @return Iterator over elements from ListSet
     */
    @Override
    public Iterator<Integer> iterator() {
        return list.iterator();
    }

    /**
     * Returns a textual represention of the ListSet.
     * @return Representation of the ListSet
     */
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

    /**
     * Return elements from the ListSet in a List.
     * @return List with ListSet elements
     */
    public List<Integer> toList() {
        return List.copyOf(this.list);
    }
}
