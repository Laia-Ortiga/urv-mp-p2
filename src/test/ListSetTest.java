package test;

import main.ListSet;
import org.junit.jupiter.api.Test;

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

        assertEquals(3, listSet.size());
        assertEquals(1, listSet.get(0));
        assertEquals(2, listSet.get(1));
        assertEquals(3, listSet.get(2));
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
        System.out.println(listSet);
        assertEquals(3, listSet.size());
        assertEquals(1, listSet.get(0));
        assertEquals(2, listSet.get(1));
        assertEquals(3, listSet.get(2));
    }
}
