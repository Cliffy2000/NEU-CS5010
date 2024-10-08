package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import item.Item;

public class ItemTest {
    private Item item;

    @Before
    public void setUp() {
        item = new Item("Water", 5);
    }

    @Test
    public void testGetName() {
        assertEquals("Water", item.getName());
    }

    @Test
    public void testSetName() {
        item.setName("Honey");
        assertEquals("Honey", item.getName());
    }

    @Test
    public void testGetCount() {
        assertEquals(5, item.getCount());
    }

    @Test
    public void testSetCount() {
        item.setCount(10);
        assertEquals(10, item.getCount());
    }
}
