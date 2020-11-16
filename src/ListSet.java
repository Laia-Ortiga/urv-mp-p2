import java.util.ArrayList;
import java.util.Iterator;

public class ListSet implements Iterable<Integer> {

    private final ArrayList<Integer> list = new ArrayList<>();

    public ListSet() {

    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<Integer> iterator() {
        throw new UnsupportedOperationException();
    }
}
