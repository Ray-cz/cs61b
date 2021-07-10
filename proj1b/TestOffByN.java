import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {

    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testOffByOne() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('f', 'h'));
    }
}
