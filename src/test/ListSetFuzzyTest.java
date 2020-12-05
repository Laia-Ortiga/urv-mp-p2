package test;

import main.FuzzyInteger;
import main.ListSetFuzzy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListSetFuzzyTest {
    @Test
    public void addOneElement() {
        ListSetFuzzy listSet = new ListSetFuzzy();

        listSet.add(new FuzzyInteger(1, 0.25));

        assertEquals(1, listSet.size());
        for (FuzzyInteger i : listSet) assertEquals(1, i.getValue());
        for (FuzzyInteger i : listSet) assertEquals(0.25, i.getMembership());
    }

    @Test
    public void addElementPresentOnListSetFuzzyWithGreaterMembership() {
        ListSetFuzzy listSet = new ListSetFuzzy();
        listSet.add(new FuzzyInteger(1, 0.25));

        listSet.add(new FuzzyInteger(1, 0.35));

        assertEquals(1, listSet.size());
        for (FuzzyInteger i : listSet) assertEquals(1, i.getValue());
        for (FuzzyInteger i : listSet) assertEquals(0.25, i.getMembership());
    }

    @Test
    public void addElementPresentOnListSetFuzzyWithLowerMembership() {
        ListSetFuzzy listSet = new ListSetFuzzy();
        listSet.add(new FuzzyInteger(1, 0.15));
        listSet.add(new FuzzyInteger(2, 0.25));
        listSet.add(new FuzzyInteger(3, 0.35));

        boolean result = listSet.add(new FuzzyInteger(2, 0.10));

        assertTrue(result);
        assertEquals(3, listSet.size());
        List<FuzzyInteger> list = listSet.toList();
        assertEquals(1, list.get(0).getValue());
        assertEquals(0.15, list.get(0).getMembership());
        assertEquals(2, list.get(1).getValue());
        assertEquals(0.10, list.get(1).getMembership());
        assertEquals(3, list.get(2).getValue());
        assertEquals(0.35, list.get(2).getMembership());
    }

    @Test
    public void addListSetFuzzyIsSortered() {
        ListSetFuzzy listSet = new ListSetFuzzy();

        listSet.add(new FuzzyInteger(1, 0.25));
        listSet.add(new FuzzyInteger(2, 0.35));
        listSet.add(new FuzzyInteger(3, 0.45));

        List<FuzzyInteger> list = listSet.toList();
        assertEquals(3, listSet.size());
        assertEquals(1, list.get(0).getValue());
        assertEquals(0.25, list.get(0).getMembership());
        assertEquals(2, list.get(1).getValue());
        assertEquals(0.35, list.get(1).getMembership());
        assertEquals(3, list.get(2).getValue());
        assertEquals(0.45, list.get(2).getMembership());
    }

    @Test
    public void addListSetFuzzy() {
        ListSetFuzzy listSet = new ListSetFuzzy();
        listSet.add(new FuzzyInteger(1, 0.25));
        listSet.add(new FuzzyInteger(3, 0.45));
        ListSetFuzzy listSet2 = new ListSetFuzzy();
        listSet.add(new FuzzyInteger(1, 0.25));
        listSet.add(new FuzzyInteger(2, 0.35));
        listSet.add(new FuzzyInteger(3, 0.1));

        listSet.addAll(listSet2);

        List<FuzzyInteger> list = listSet.toList();
        assertEquals(3, listSet.size());
        assertEquals(1, list.get(0).getValue());
        assertEquals(0.25, list.get(0).getMembership());
        assertEquals(2, list.get(1).getValue());
        assertEquals(0.35, list.get(1).getMembership());
        assertEquals(3, list.get(2).getValue());
        assertEquals(0.1, list.get(2).getMembership());
    }

    @Test
    public void addListSetFuzzyDestinationListIsEmpty() {
        ListSetFuzzy listSet = new ListSetFuzzy();
        ListSetFuzzy listSet2 = new ListSetFuzzy();
        listSet.add(new FuzzyInteger(1, 0.25));
        listSet.add(new FuzzyInteger(2, 0.35));
        listSet.add(new FuzzyInteger(3, 0.45));

        listSet.addAll(listSet2);

        List<FuzzyInteger> list = listSet.toList();
        assertEquals(3, listSet.size());
        assertEquals(1, list.get(0).getValue());
        assertEquals(0.25, list.get(0).getMembership());
        assertEquals(2, list.get(1).getValue());
        assertEquals(0.35, list.get(1).getMembership());
        assertEquals(3, list.get(2).getValue());
        assertEquals(0.45, list.get(2).getMembership());
    }
}
