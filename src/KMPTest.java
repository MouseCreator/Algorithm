import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KMPTest {
    @Test
    void testPrefix() {
        KMP kmp = new KMP("", "ababaca");
        int[] actual = kmp.prefixFunction();
        int[] expected = new int[]{0, 0, 1, 2, 3, 0, 1};
        assertArrayEquals(expected,actual);
    }
    @Test
    void testMatch() {
        KMP kmp = new KMP("abacaabaccabacabaab", "abacab");
        kmp.execute();
        assertEquals(kmp.getResult(),10);
    }
}