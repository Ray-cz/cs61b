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
        test.union(3, 5);
    }

    @Test
    public void testPathCompression() {
        UnionFind u = new UnionFind(12);
        u.union(0, 1);
        u.union(1, 2);

        u.union(3, 4);
        u.union(4, 5);

        u.union(0, 3);

        u.union(6, 7);
        u.union(7, 8);

        u.union(9, 10);
        u.union(10, 11);

        u.union(6, 9);

        u.union(0, 6);
        int[] a = {4, 4, 1, 4, 10, 4, 10, 10, 7, 10, -12, 10};
        assertArrayEquals(u.parent, a);
    }
}
