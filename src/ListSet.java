import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ListSet implements Iterable<Integer> {

    private final ArrayList<Integer> list;

    public ListSet() {
        this.list = new ArrayList<>();
    }

    // Just for testing purposes
    // Delete on merge
    public ListSet(ArrayList<Integer> list) {
        this.list = list;
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * axsor: Borreu-la si voleu, però m'agrada poder llegir el codi
     */
    public boolean isNotEmpty() {
        return !list.isEmpty();
    }

    public int size() {
        return list.size();
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
//        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "ListSet{" +
            "list=" + list +
            '}';
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
