import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AhoCorasickExtendedTest {
    @Test
    void executeTest() {
        String patterns[] = new String[]{"he", "she", "his", "hers"};
        AhoCorasickExtended a = new AhoCorasickExtended("", patterns);
        a.execute();
        a.printAutomata();
    }
}