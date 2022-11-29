import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BoyerMooreTest {
    @Test
    void testShiftTable() {
        final String pattern = "BARBER";
        final String sequence = "BARBERSHOP";
        BoyerMoore boyer = new BoyerMoore(sequence, pattern);
        HashMap<Character, Integer> expected = getExpected();
        HashMap<Character, Integer> actual = boyer.buildShiftTable();
        assertEquals(expected, actual);
    }
    private HashMap<Character, Integer> getExpected() {
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('A', 4);
        expected.put('B', 2);
        expected.put('E', 1);
        expected.put('R', 3);
        expected.put('S', 6);
        expected.put('H', 6);
        expected.put('O', 6);
        expected.put('P', 6);
        return expected;
    }

    @Test
    void testSuffixTable() {

        final String pattern = "ABCBAB";
        final String sequence = "None";
        BoyerMoore boyer = new BoyerMoore(sequence, pattern);
        int[] expected = new int[]{2, 4, 4, 4, 4};
        int[] actual = boyer.buildSuffixTable();
        assertArrayEquals(expected, actual);
    }

    @Test
    void testFind() {
        final String sequence = "BESS_KNEW_ABOUT_BAOBABS";
        final String pattern = "BAOBAB";
        BoyerMoore boyer = new BoyerMoore(sequence, pattern);
        boyer.execute();
        assertEquals(boyer.getResult(), 16);
    }

    @Test
    void simpleExample() {
        final String sequence = "ALLOBALOBALOALALALAB";
        final String pattern = "ALALA";
        BoyerMoore boyer = new BoyerMoore(sequence, pattern);
        boyer.execute();
    }
}