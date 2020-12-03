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
     * It returns a boolean indicating whether an element is found or not in a ListSetFuzzy.
     * It uses a binary search method to find the search element in the set so as to minimize
     * the computational cost of this method, being logarithmic instead of linear.
     * Having the ListSetFuzzy sorted is one prerequisite.
     *
     * @param fuzzyIntegerElement element to be searched in the ListSetFuzzy
     * @return real number giving the membership of the element in the set if it is in the set with a greater membership, -1 otherwise
     */
    public double contains(FuzzyInteger fuzzyIntegerElement) {
        // We used the already implemented Collections class method binarySearch
        int resultSearch = Collections.binarySearch(list, fuzzyIntegerElement);
        return resultSearch >= 0 && fuzzyIntegerElement.getMembership() <= list.get(resultSearch).getMembership() ? list.get(resultSearch).getMembership() : -1;
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
        while (isSubset && counter < lengthFuzzySubsetCandidate) {
            if (contains(fuzzySubsetCandidate.list.get(counter)) == -1) {
                isSubset = false;
            }
            else {
                result.set(counter, fuzzySubsetCandidate.list.get(counter).getMembership());
            }
            counter++;
        }
        if (isSubset == false) {
            for (int i = 0; i < result.size(); i++) result.get(i) = -1;
        }
        return result;
    }

    /**
     * RetainAll method for calculating the intersection between an Integer ListSetFuzzy and
     * the current ListSetFuzzy.
     * It returns a boolean to indicate whether the set has changed as a result of the call.
     * It uses the method contains implemented using a binary search, so having both ListSetFuzzy
     * sorted are prerequisites.
     *
     * @param intersectedSet ListSetFuzzy to be intersected with the current ListSetFuzzy
     * @return boolean to indicate whether the set has changed as a result of the call, that is, whether any of the elements adjoined
     * in the new set were not already present in the original set
     */
    public ArrayList<Double> retainAll(ListSetFuzzy intersectedSet) {
        boolean changed = false;

        int i = 0;
        while (i < list.size()) {
            FuzzyInteger element = list.get(i);
            int resultSearch = Collections.binarySearch(list, element);
            if (resultSearch >= 0) {
                Double(element.getMembership()).compareTo(list.get(resultSearch).getMembership());
                int comparisonResult = Double(element.getMembership()).compareTo(list.get(resultSearch).getMembership());
                changed = true;
                if (comparisonResult != 0) {
                    double elementMembership = min(element.getMembership(),list.get(resultSearch).getMembership());
                    list.get(resultSearch).setMembership(elementMembership);
                }
                else {
                    remove(element);
                }
            }
            else {
                i++;
            }
        }
        return changed;
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
