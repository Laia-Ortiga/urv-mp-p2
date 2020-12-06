package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class defining a Fuzzy set of FuzzyIntegers using a list to implement it.
 */
public class ListFuzzySet implements Iterable<FuzzyInteger> {

    // Structure where elements from ListFuzzySet are stored.
    private List<FuzzyInteger> list;

    private final TNorm tnorm;

    /**
     * Constructs an empty fuzzy set. In this case, the triangular norm that will be used is Gödel-Dummet.
     */
    public ListFuzzySet() {
        this.list = new ArrayList<>();
        this.tnorm = new TNorm(Math::min);
    }

    /**
     * Constructs an empty fuzzy set. In this case, the triangular norm that will be used is passed by parameter.
     *
     * @param tnorm triangular norm that will be used
     */
    public ListFuzzySet(TNorm tnorm) {
        this.list = new ArrayList<>();
        this.tnorm = tnorm;
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Return the number of elements in the set.
     *
     * @return elements in the set
     */
    public int size() {
        return list.size();
    }

    /**
     * Indicates if the set has no elements.
     *
     * @return whether set has no elements
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Method that returns a real number giving the t-norm between the membership degree of the element in the set
     * and the element itself.
     * It uses a binary search method to find the searched element in the set so as to minimize
     * the computational cost of this method, being logarithmic instead of linear.
     *
     * @param fuzzyIntegerElement element to be searched in the ListSetFuzzy
     * @return real number giving the t-norm between the membership degree of the element in the set
     * and the element itself.
     */
    public double contains(FuzzyInteger fuzzyIntegerElement) {
        int searchResult = Collections.binarySearch(list, fuzzyIntegerElement);
        // If the result is non-negative, then the value is in the set.
        if (searchResult >= 0) {
            return tnorm.apply(list.get(searchResult).getMembership(), fuzzyIntegerElement.getMembership());
        }
        return 0.0;
    }

    /**
     * Method that returns the maximum of the t-norms between the elements that belong to both sets.
     *
     * @param fuzzySubsetCandidate to be checked whether it is a subset or not of the current ListSetFuzzy
     * @return maximum of the t-norms between the elements that belong to both sets
     */
    public double containsAll(ListFuzzySet fuzzySubsetCandidate) {
        double result = 0.0;
        int i = 0;
        int j = 0;
        // Linear search over two ordered fuzzy sets.
        while (i < size() && j < fuzzySubsetCandidate.size()) {
            int compareResult = list.get(i).compareTo(fuzzySubsetCandidate.list.get(j));

            if (compareResult < 0) {
                i++;
            } else if (compareResult == 0) {
                double oldMembership = list.get(i).getMembership();
                double newMembership = tnorm.apply(oldMembership, fuzzySubsetCandidate.list.get(j).getMembership());
                result = Math.max(newMembership, result);
                j++;
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

    /**
     * Method for calculating the intersection between two fuzzy sets.
     * It returns a real number contaning the maximum value of the distinct elements memberships for the intersection
     * set.
     *
     * @param intersectedSet ListSetFuzzy to be intersected with the current ListSetFuzzy
     * @return real number contaning the maximum value of the distinct elements memberships for the intersection set
     */
    public double retainAll(ListFuzzySet intersectedSet) {
        double result = 0.0;
        int i = size() - 1;
        int j = intersectedSet.size() - 1;

        // We iterate from back to front as removing from an ArrayList shifts elements after the one removed
        // This way it should be a little faster
        while (i >= 0 && j >= 0) {
            int comparison = list.get(i).compareTo(intersectedSet.list.get(j));
            if (comparison == 0) {
                double oldMembership = list.get(i).getMembership();
                double elementMembership = tnorm.apply(oldMembership,
                        intersectedSet.list.get(j).getMembership());
                list.set(i, new FuzzyInteger(list.get(i).getValue(), elementMembership));
                if (elementMembership < oldMembership) {
                    result = Math.max(elementMembership, result);
                }
                i--;
                j--;
            } else {
                if (comparison < 0) {
                    j--;
                } else {
                    result = Math.max(list.get(i).getMembership(), result);
                    list.remove(i);
                    i--;
                }
            }
        }
        list.subList(0, i + 1).clear();
        return result;
    }

    private double remove(int index, double membership) {
        double oldMembership = list.get(index).getMembership();
        double newMembership = tnorm.apply(list.get(index).getMembership(), 1 - membership);

        if (newMembership == 0.0) {
            // Remove entirely from the list if the membership would be 0.0
            list.remove(index);
            return 0.0;
        } else {
            list.set(index, new FuzzyInteger(list.get(index).getValue(), newMembership));
            return newMembership;
        }
    }

    /**
     * Removes an element from this fuzzy set.
     * Removing doesn't mean setting the membership degree of the element to 0.0, but to the t-norm of the old value
     * and the new value (which is 1.0 - the membership degree of the element to remove).
     *
     * @param x the element to be removed from this set
     * @return the old membership degree of the element to be removed or 0.0 if it wasn't there
     */
    public double remove(FuzzyInteger x) {
        // Search for element with same value.
        int index = Collections.binarySearch(list, x);
        if (index >= 0) {
            double oldMembership = list.get(index).getMembership();
            remove(index, x.getMembership());
            return oldMembership;
        }
        return 0.0;
    }

    /**
     * Acts as if using the method remove for every single element of the specified set.
     *
     * @param x the list of elements to be removed from this set
     * @return the maximum of the t-norms between the old memberships and the ones to be removed
     */
    public double removeAll(ListFuzzySet x) {
        double result = 0.0;
        int i = size() - 1;
        int xIndex = x.size() - 1;

        // We iterate from back to front as removing from an ArrayList shifts elements after the one removed
        // This way it should be a little faster
        while (i >= 0 && xIndex >= 0) {
            if (list.get(i).compareTo(x.list.get(xIndex)) < 0) {
                xIndex--;
            } else if (list.get(i).compareTo(x.list.get(xIndex)) > 0) {
                i--;
            } else {
                double oldMembership = list.get(i).getMembership();
                double newMembership = remove(i, x.list.get(xIndex).getMembership());
                if (newMembership < oldMembership) {
                    result = Math.max(result, newMembership);
                }
                i--;
                xIndex--;
            }
        }
        return result;
    }

    /**
     * Method that sets an element in a set with a membership equal p disjunction q, where p is the membership of
     * the element in the list and q is the membership of the element to be added, and it returns p.
     *
     * @param number Element to be added
     * @return the membership of the element to be replaced in the list if it was in the list or 0.0 otherwise
     */
    public double add(FuzzyInteger number) {
        if (number == null) return 0.0;

        int index = Collections.binarySearch(list, number);

        if (index < 0) {
            list.add(-index - 1, number);
            return 0.0;
        } else {
            double oldMembership = list.get(index).getMembership();
            // Uses the Morgan's law to get a disjunction using the triangular norm.
            double newMembership = 1.0 - tnorm.apply(1.0 - number.getMembership(), 1.0 - oldMembership);
            list.set(index, new FuzzyInteger(number.getValue(), newMembership));
            return oldMembership;
        }
    }

    /**
     * Adds as if calling the method add for every element in the parameter.
     *
     * @param listSet Elements to be added
     * @return the maximum of the new changed memberships
     */
    public double addAll(ListFuzzySet listSet) {
        double result = 0.0;

        int compareResult, i = 0, j = 0;

        while (i < size() && j < listSet.size()) {
            compareResult = list.get(i).compareTo(listSet.list.get(j));

            // Own element is lower than given element. Search for next given element.
            if (compareResult == -1) {
                i++;
                // Both elements are the same. Search for next elements on both ListSets.
            } else if (compareResult == 0) {
                double oldMembership = list.get(i).getMembership();
                double newMembership = 1.0 - tnorm.apply(1.0 - oldMembership, 1.0 - listSet.list.get(j).getMembership());
                // Adds given element if has lower membership
                list.set(i, new FuzzyInteger(listSet.list.get(j).getValue(), newMembership));

                if (newMembership > oldMembership) {
                    result = Math.max(newMembership, result);
                }
                j++;
                i++;
                // Given element is greater than own element. Add it and search for next given element.
            } else if (compareResult == 1) {
                list.add(i, listSet.list.get(j));
                result = 1.0;
                j++;
            }
        }

        // Adds the rest of unchecked given elements.
        if (list.addAll(listSet.list.subList(j, listSet.list.size()))) {
            result = 1.0;
        }

        return result;
    }

    /**
     * Return elements from the ListFuzzySet in a List.
     * @return List with ListFuzzySet elements
     */
    public List<FuzzyInteger> toList() {
        return List.copyOf(this.list);
    }

    /**
     * Returns an Iterator over elements from ListFuzzySet.
     * @return Iterator over elements from ListFuzzySet
     */
    @Override
    public Iterator<FuzzyInteger> iterator() {
        return list.iterator();
    }
    /**
     * Returns a textual representation of the ListFuzzySet.
     * @return Representation of the ListFuzzySet
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
}
