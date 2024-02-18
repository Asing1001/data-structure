import org.junit.jupiter.api.Test;

import com.paddingleft.edocleet.MyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    public void testAddAndGet() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0, 10);
        list.add(1, 20);
        list.add(2, 30);
        
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }
    
    @Test
    public void testRemove() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0, 10);
        list.add(1, 20);
        list.add(2, 30);
        
        list.remove(1);
        
        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
    }
}
