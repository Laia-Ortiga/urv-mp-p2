
import java.util.*;

public class ListSet implements Iterable<Integer> {

    // COMPOSITION
    // This is an implementation by COMPOSITION, not INHERITANCE, based on a list.
    // Must wrap some methods such as clear(), size(), isEmpty(), contains(), toString(), and also, iterator(),
    // delegating ListSet functionality on List functionality.

    // When possible, we program to the interface List, rather than to a specific implementation such as ArrayList or LinkedList.
    // Here, the keyword 'final' makes the reference to the list immutable, not the contents of the list referred to.

    // We can INITIALIZE the List object in its declaration, or later on as part of a constructor.
    // Initialization in the declaration has limitations because of its simplicity, but for this simple example, it would be fine.
    // If initialization required some logic (for example, error handling or a for loop to fill a complex array),
    // simple assignment would be inadequate.
    // Instance variables could then be initialized in constructors, where error handling or other logic could be added.
    // Reference: https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html

    // Simpler initialization option
    // Initialize field when declaring it, without constructor
    // private final List<Integer> integerList = new ArrayList<>();

    // More flexible initialization option
    // Declare field, but initialize within constructor

    private final List<Integer> integerList;

    // CONSTRUCTOR
    public ListSet() {
        integerList = new ArrayList<>();
    }

    // METHOD DELEGATION

    // ITERATOR, IMPLEMENTATION of ITERABLE INTERFACE
    // Do not confuse Iterator with Iterable. Must implement the iterator() method, which returns an object implementing the interface Iterator.
    // Implementing Iterable interface allows an object to be the target of the "for-each loop" statement.

    @Override
    public Iterator<Integer> iterator() {
        return integerList.iterator();
    }

    // CONTAINS METHODS

    // Contains element

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

    // Contains subset

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

    // Intersection method

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

    // ADD element
    // If the list is kept in ascending order at all times,
    // binary search could be used to check whether the element is a duplicate in logarithmic time.
    // Otherwise, checking for containment or duplication would have linear complexity.

    // When it comes to sorting, you have several options.
    // I'd recommend checking the documentation of List for the method sort(),
    // but also the method sort() in the documentation of the "static" class Collections
    // (plural, not to be confused with the interface Collection, singular).

    // Interface List: https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/List.html
    // INTERFACE COLLECTION: https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Collection.html
    // CLASS COLLECTIONS: https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Collections.html

    public boolean add(Integer integerElement) {
        // TO BE COMPLETED
        // Returning 'false' here merely as a placeholder. Not an adequate implementation.
        return false;
    }

    // SET UNION or ADD ALL
    // We were not required to implement Set<Integer>, but we'll employ consistent method names, as well as return values.
    // As per instructions, our set methods must admit ListSet objects, not general Collection objects, unlike Set methods.
    // As for the returned value, just as the Set interface requires, a boolean will indicate if the set has changed.

    // A simple implementation could resort to the List (or Collection) methods contains() and add().

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

    // TO STRING
    @Override
    public String toString() {
        // TO BE COMPLETED
        // Recall that we're asked to display the contents of the set in increasing order.
        // It may or may not be enough to simply delegate on toString() of the List object,
        // depending on whether the list is sorted or not.
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (var x : integerList) {
            if (x != integerList.get(0)) {
                sb.append(", ");
            }
            sb.append(x);
        }
        sb.append("}");
        return sb.toString();
    }
}

