package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math.*;

import static java.lang.Double.min;

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
        int resultSearch = Collections.binarySearch(list, fuzzyIntegerElement); // We use the already implemented Collections class method binarySearch
        return resultSearch >= 0 && fuzzyIntegerElement.getMembership() <= list.get(resultSearch).getMembership() ? fuzzyIntegerElement.getMembership() : -1; // The minimum will always be the value of the element fuzzyIntegerElement
    }

    /**
     * Contains method for a ListSetFuzzy subset in an ordered (in ascending order) Integer ListSetFuzzy.
     * It returns a list of the length of the fuzzy subset containing all the memberships in the original set in case
     * that they are a subset or full of -1 otherwise.
     * It uses the method contains implemented using a binary search, so having the ListSetFuzzyFuzzy
     * sorted is one prerequisite.
     *
     * @param fuzzySubsetCandidate to be checked whether it is a subset or not of the current ListSetFuzzy
     * @return ArrayList<Double> of the length of the fuzzy subset containing all the memberships in the original set in case
     * that they are a subset or full of -1 otherwise.
     */
    public ArrayList<Double> containsAll(ListSetFuzzy fuzzySubsetCandidate) {
        int lengthFuzzySubsetCandidate = fuzzySubsetCandidate.list.size();
        ArrayList<Double> result = new List(lengthFuzzySubsetCandidate);
        boolean isSubset = true;
        int counter = 0;
        while (isSubset && counter < lengthFuzzySubsetCandidate) {      // Search for some element in the subset candidate that is not in the original set
            if (contains(fuzzySubsetCandidate.list.get(counter)) == -1) {   // If a single element is not contained, then it is not a subset
                isSubset = false;
            }
            else {
                result.set(counter, fuzzySubsetCandidate.list.get(counter).getMembership());    // If not proved the opposite, set the minimum membership of all the elements
                                                                                                // in the candidate, which will always be the membership in the candidate
            }
            counter++;
        }
        if (isSubset == false) {   // If it is not a subset, we fill in the result list with -1 values
            for (int i = 0; i < result.size(); i++) {
                result.set(i, -1.0);
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
    public double retainAll(ListSetFuzzy intersectedSet) {
        int i = 0;
        double minimumMembership = 1.0;
        while (i < list.size()) {                                           // Traverse the original set.
            FuzzyInteger element = list.get(i);
            int resultSearch = Collections.binarySearch(intersectedSet, element);       // Check whether the elements of the original set are also members of the intersected set
            // Equivalent to calculating A\(A\B) and leaving the result in list
            if (resultSearch >= 0) {                                                    // If found in intersected set (common values)
                int comparisonResult = element.getMembership().compareTo(intersectedSet.list.get(resultSearch).getMembership());    // Compare memberships of commons values
                double elementMembership = min(element.getMembership(), intersectedSet.list.get(resultSearch).getMembership());
                if (comparisonResult != 0) {                               // Setting a membership is only required when values of pertinences are distinct
                    FuzzyInteger elementToBeSet = FuzzyInteger(element.getValue(), intersectedSet.list.get(resultSearch).getMembership());
                    list.set(resultSearch, elementToBeSet);
                }
                minimumMembership = min(minimumMembership, elementMemberShip);
                i++;
            }
            else {                                                                     // If not found in intersected set (not common values)
                remove(element);
            }
        }
        return minimumMembership;
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
