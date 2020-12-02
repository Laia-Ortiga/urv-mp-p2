package main;

import java.util.ArrayList;
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

}
