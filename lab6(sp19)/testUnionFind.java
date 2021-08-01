import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {

    @Test
    public void test() {
        UnionFind test = new UnionFind(10);
        test.union(0, 1);
        test.union(1, 2);
        test.union(3, 4);
        assertTrue(test.connected(0, 2));
        assertFalse(test.connected(2, 4));
        assertFalse(test.connected(3, 2));
        test.union(2, 3);
        assertTrue(test.connected(1, 4));
        assertEquals(4, test.parent(3));
        assertEquals(1, test.find(3));
    }
}
