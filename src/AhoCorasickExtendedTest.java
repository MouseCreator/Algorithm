import org.junit.jupiter.api.Test;


class AhoCorasickExtendedTest {
    @Test
    void executeTest() {
        String[] patterns = new String[]{"he", "she", "his", "hers"};
        AhoCorasickExtended a = new AhoCorasickExtended("H", patterns);
        a.execute();
    }
    @Test
    void executeTestAbra() {
        String[] patterns = new String[]{"abra", "bra", "abc", "bc"};
        AhoCorasickExtended a = new AhoCorasickExtended("a", patterns);
        a.execute();
    }
    @Test
    void executeTestAbba() {
        String[] patterns = new String[]{"bba", "abb", "bab", "abba"};
        AhoCorasickExtended a = new AhoCorasickExtended("a", patterns);
        a.execute();
    }
}