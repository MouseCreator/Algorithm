public class Main {
    public static void main(String[] args) {
        BoyerMoore m = new BoyerMoore("ATCCTCTCCTATTCCCT", "TCTCCTATTC");
        m.setAlphabet("G, A, C, T");
        m.execute();
    }
}