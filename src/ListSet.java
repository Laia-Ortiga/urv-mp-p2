
import java.util.*;

public class ListSet implements Iterable<Integer> {

    private final List<Integer> list;

    private final List<Integer> integerList;

    public ListSet() {
        list = new ArrayList<>();
    }

    public void clear() {
        list.clear();
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
        return integerList.iterator();
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
        return Collections.binarySearch(integerList, integerElement) >= 0;
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
        int lengthSubsetCandidate = subsetCandidate.integerList.size();
        while (result && counter < lengthSubsetCandidate) {
            if (contains(subsetCandidate.integerList.get(counter))) {
                result = false;
            }
            counter++;
        }
        return result;
    }

    /**
     * Contains method for calculating the intersection between an Integer ListSet and
     * the current ListSet.
     * It returns a ListSet with the intersection of the two ListSet.
     * It uses the method contains implemented using a binary search, so having both ListSet
     * sorted are prerequisites.
     *
     * @param intersectedSet ListSet to be intersected with the current ListSet
     * @return ListSet with the intersection of the two ListSet
     */
    public ListSet retainAll(ListSet intersectedSet) {
        ListSet intersectionList = new ListSet();
        for (Integer element : intersectedSet) {
            if (this.contains(element)) {
                intersectionList.add(element);
            }
        }
        return intersectionList;
    }

    public boolean add(Integer integerElement) {
        // TO BE COMPLETED
        // Returning 'false' here merely as a placeholder. Not an adequate implementation.
        return false;
    }

    public boolean addAll(ListSet listSet) {
        boolean changed = false;

        // Can use FOR-EACH LOOP or extended loop with ListSet because it implements Iterable
        for (Integer integerElement: listSet) {

            // Could do some error checking, for robustness.
            // We should consider whether we'd like to allow a null element or not, and if we wouldn't, what exception could be appropriate.
            // Here's an example of what we could do in this regard.
            if (integerElement == null)
                throw new NullPointerException();

            // TO BE COMPLETED
            // else ...
            // If any element in the input/other List is not already contained in the current/this List, then add it.
            // Could use the List methods contains() and add(), if we'd rather focus on code simplicity,
            // or perhaps write a more efficient implementation.
            // Update variable 'changed' if the list is changed.

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
