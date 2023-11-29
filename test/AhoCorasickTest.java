import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AhoCorasickTest {
    @Test
    void testTransitions() {
        AhoCorasick ahoCorasick = new AhoCorasick("a", "ababaca");
        ahoCorasick.computeTransitionFunction();
        int[][] expected = arrayFromString();
        boolean isEqual = Arrays.deepEquals(expected, ahoCorasick.getTransitions());
        assertTrue(isEqual);
    }
    private int[][] arrayFromString() {
        String str = "100 120 300 140 500 146 700 120".trim();
        String[] rows = str.split(" ");
        int[][] result = new int[rows.length][rows[0].length()];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                result[i][j] = rows[i].charAt(j) - '0';
            }
        }
        return result;
    }
}