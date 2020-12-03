package test;

import main.ListSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListSetTest {

    @Test
    public void addOneElement() {
        ListSet listSet = new ListSet();

        listSet.add(1);

        for (int i : listSet) assertEquals(1, i);
        assertEquals(1, listSet.size());
    }

    @Test
    public void addElementPresentOnListSet() {
        ListSet listSet = new ListSet();
        listSet.add(1);

        listSet.add(1);

        for (int i : listSet) assertEquals(1, i);
        assertEquals(1, listSet.size());
    }

    @Test
    public void addListSetIsSortered() {
        ListSet listSet = new ListSet();

        listSet.add(1);
        listSet.add(3);
        listSet.add(2);

        List<Integer> list = listSet.toList();
        assertEquals(3, listSet.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void addListSet() {
        ListSet listSet = new ListSet();
        listSet.add(1);
        listSet.add(3);
        ListSet listSet2 = new ListSet();
        listSet2.add(2);
        listSet2.add(1);

        listSet.addAll(listSet2);

        List<Integer> list = listSet.toList();
        assertEquals(3, listSet.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void addListSetDestinationListIsEmpty() {
        ListSet listSet = new ListSet();
        ListSet listSet2 = new ListSet();
        listSet2.add(2);
        listSet2.add(1);
        listSet2.add(3);

        listSet.addAll(listSet2);

        List<Integer> list = listSet.toList();
        assertEquals(3, listSet.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }
}