package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListFuzzySet implements Iterable<FuzzyInteger>  {

    private List<FuzzyInteger> list;

    private final TNorm tnorm;

    public ListFuzzySet() {
        this.list = new ArrayList<>();
        this.tnorm = new TNorm(Math::min);
    }

    public ListFuzzySet(TNorm tnorm) {
        this.list = new ArrayList<>();
        this.tnorm = tnorm;
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

    /**
     * Contains method for an element in an ordered (in ascending order) Integer ListSetFuzzy.
     * It returns a real number giving the minimum membership of the element in the set if it is in the set, -1 otherwise
     * It uses a binary search method to find the searched element in the set so as to minimize
     * the computational cost of this method, being logarithmic instead of linear.
     * Having the ListSetFuzzy sorted is one prerequisite.
     *
     * @param fuzzyIntegerElement element to be searched in the ListSetFuzzy
     * @return real number giving the minimum membership of the element in the set if it is in the set, -1 otherwise
     */
    public double contains(FuzzyInteger fuzzyIntegerElement) {
        int searchResult = Collections.binarySearch(list, fuzzyIntegerElement);
        if (searchResult >= 0) {
            // The minimum will always be the value of the element fuzzyIntegerElement
            return tnorm.apply(list.get(searchResult).getMembership(), fuzzyIntegerElement.getMembership());
        }
        return 0.0;
    }

    /**
     * Contains method for a ListSetFuzzy subset in an ordered (in ascending order) Integer ListSetFuzzy.
     * It returns a list of the length of the fuzzy subset containing all the memberships in the original set in case
     * that they are a subset or full of 0.0 otherwise.
     * It uses the method contains implemented using a binary search, so having the ListSetFuzzyFuzzy
     * sorted is one prerequisite.
     *
     * @param fuzzySubsetCandidate to be checked whether it is a subset or not of the current ListSetFuzzy
     * @return List<Double> of the length of the fuzzy subset containing all the memberships in the original set in case
     * that they are a subset or full of 0.0 otherwise.
     */
    public double containsAll(ListFuzzySet fuzzySubsetCandidate) {
        double result = 0.0;
        int i = 0;
        int j = 0;

        while (i < size() && j < fuzzySubsetCandidate.size()) {
            int compareResult = list.get(i).compareTo(fuzzySubsetCandidate.list.get(j));

            // Own element is lower than given element. Search for next given element.
            if (compareResult < 0) {
                i++;
                // Both elements are the same. Search for next elements on both ListSets.
            } else if (compareResult == 0) {
                double oldMembership = list.get(i).getMembership();
                double newMembership = tnorm.apply(oldMembership, fuzzySubsetCandidate.list.get(j).getMembership());
                // Adds given element if has lower membership
                result = Math.max(newMembership, result);
                j++;
                i++;
                // Given element is greater than own element. Add it and search for next given element.
            } else {
                j++;
            }
        }
        return result;
    }

    /**
     * RetainAll method for calculating the intersection between an Integer ListSetFuzzy and
     * the current ListSetFuzzy.
     * It returns a real number contaning the minimum value of the pertinences in the intersection set
     * It uses the method contains implemented using a binary search, so having both ListSetFuzzy
     * sorted are prerequisites.
     *
     * @param intersectedSet ListSetFuzzy to be intersected with the current ListSetFuzzy
     * @return real number contaning the minimum value of the pertinences in the intersection set
     */
    public double retainAll(ListFuzzySet intersectedSet) {
        double result = 1.0;
        int i = size() - 1;
        int j = intersectedSet.size() - 1;

        // We iterate from back to front as removing from an ArrayList shifts elements after the one removed
        // This way it should be a little faster
        while (i >= 0 && j >= 0) {
            int comparison = list.get(i).compareTo(intersectedSet.list.get(j));
            if (comparison == 0) {
                double elementMembership = tnorm.apply(list.get(i).getMembership(),
                        intersectedSet.list.get(j).getMembership());
                list.set(i, new FuzzyInteger(list.get(i).getValue(), elementMembership));
                i--;
                j--;
            }
            else {
                if (comparison < 0) {
                    j--;
                }
                else {
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
        }
        else {
            list.set(index, new FuzzyInteger(list.get(index).getValue(), newMembership));
            return newMembership;
        }
    }

    /**
     * Removes an element from this fuzzy set.
     * Removing doesn't mean to set the membership degree of the element to 0.0, but to the minimum of the old value and
     * the new value (which is 1.0 - the membership degree of the element to remove)
     *
     * @param x the element to be removed from this set
     * @return the old membership degree of the element to be removed or 0.0 if it wasn't there
     */
    public double remove(FuzzyInteger x) {
        // search for element with same value
        int index = Collections.binarySearch(list, x);
        if (index >= 0) {
            return remove(index, x.getMembership());
        }
        return 0.0;
    }

    /**
     * Acts as if using the method remove for every single element of the specified set.
     *
     * @param x the list of elements to be removed from this set
     * @return the minimum of all the old membership degrees of every element to be removed
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
            }
            else if (list.get(i).compareTo(x.list.get(xIndex)) > 0) {
                i--;
            }
            else {
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
     * Adds new element into the list sorting it ascending by value.
     * Updates element membership in case the newer is greater than the actual membership element.
     *
     * @param number Element to be added
     * @return Previous membership from updated element. 0.0 in case element was not present.
     */
    public double add(FuzzyInteger number) {
        if (number == null) return 0.0;

        int index = Collections.binarySearch(list, number);

        // Given element gonna be added on their own position in the ListSet.
        if (index < 0) {
            list.add(-index - 1, number);
            return 0.0;
        // Adds given element if has lower membership
        } else {
            double oldMembership = list.get(index).getMembership();
            double newMembership = 1.0 - tnorm.apply(1.0 - number.getMembership(), 1.0 - oldMembership);
            list.set(index, new FuzzyInteger(number.getValue(), newMembership));
            return oldMembership;
        }
    }

    /**
     * Adds new elements into the list.
     * In case new element is present on the list, updates the membership value ifthe newer its greater.
     *
     * @param listSet Elements to be added
     * @return Maximum membership from changed FuzzyIntegers
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

        // Add the rest of unchecked given elements.
        if (list.addAll(listSet.list.subList(j, listSet.list.size()))) {
            result = 1.0;
        }

        return result;
    }

    public List<FuzzyInteger> toList() {
        return List.copyOf(this.list);
    }

    @Override
    public Iterator<FuzzyInteger> iterator() {
        return list.iterator();
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
