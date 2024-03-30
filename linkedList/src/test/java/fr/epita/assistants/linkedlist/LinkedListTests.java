package fr.epita.assistants.linkedlist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTests {
    @Test
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void testInsertOne() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(12);
        assertEquals(Integer.valueOf(12), list.get(0), "Invalid element");
    }

    @Test
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void testGetFail() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(3);
        list.insert(5);
        list.insert(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    }

    @Test
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void testRemoveNotPresent() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        assertNull(list.remove(12), "Invalid return value of remove()");
    }

    @Test
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void testInsertMany() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        list.insert(2);
        list.insert(5);
        list.insert(-1);
        list.insert(3);
        list.insert(6);
        list.insert(4);
        list.insert(0);
        assertEquals(Integer.valueOf(-1), list.get(0), "Invalid element");
        assertEquals(Integer.valueOf(0), list.get(1), "Invalid element");
        assertEquals(Integer.valueOf(1), list.get(2), "Invalid element");
        assertEquals(Integer.valueOf(2), list.get(3), "Invalid element");
        assertEquals(Integer.valueOf(3), list.get(4), "Invalid element");
        assertEquals(Integer.valueOf(4), list.get(5), "Invalid element");
        assertEquals(Integer.valueOf(5), list.get(6), "Invalid element");
        assertEquals(Integer.valueOf(6), list.get(7), "Invalid element");
        assertEquals(8, list.size(), "Invalid Size");
    }

    @Test
    @Timeout(value = 10, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    public void testNull() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(null);
        assertNull(list.get(0), "Invalid element");
    }
    // add your own tests here
}
