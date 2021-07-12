import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        String message="\n";
        int size = 0;
        for (int i = 0; i < 500; i++) {
            double selector = StdRandom.uniform();

            if (selector < 0.25) {
                sad.addFirst(i);
                ads.addFirst(i);
                size += 1;
                message = message.concat("addFirst(" + i + ")\n");
                assertEquals(message, ads.get(0), sad.get(0));
            } else if (selector < 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                size += 1;
                message = message.concat("addLast(" + i + ")\n");
                assertEquals(message, ads.get(size - 1), sad.get(size - 1));
            } else if (selector < 0.75) {
                if (ads.isEmpty()) {
                    assertTrue(sad.isEmpty());
                    continue;
                }
                Integer x = ads.removeFirst();
                Integer y = sad.removeFirst();
                message = message.concat("removeFirst()\n");
                size -= 1;
                assertEquals(message, x, y);
            } else {
                if (ads.isEmpty()) {
                    assertTrue(sad.isEmpty());
                    continue;
                }
                Integer x = ads.removeLast();
                Integer y = sad.removeLast();
                message = message.concat("removeLast()\n");
                size -= 1;
                assertEquals(message, x, y);
            }
        }
    }
}
