public class Algorithm {
    protected String sequence;
    protected String pattern;

    protected int N;
    protected int M;

    protected int result;

    public int getResult() {
        return result;
    }
    public Algorithm(String sequence, String pattern) {
        this.sequence = formatString(sequence);
        this.N = sequence.length();
        this.pattern = formatString(pattern);
        this.M = pattern.length();
    }
    protected String formatString(String str) {
        return str.toUpperCase().toUpperCase().replace(" ", "_");
    }

    public void execute() {
        System.out.println(sequence);
        for (int i = 0; i < N - M; i++) {
            shift(i);
            printInColor(matches(i));
        }
    }

    protected void printInColor(boolean matches) {
        if (matches) {
            System.out.println(ColorCode.ANSI_GREEN + pattern + ColorCode.ANSI_RESET);
        } else {
            System.out.println(pattern);
        }
    }
    protected boolean matches(int shiftCount) {
        return this.sequence.substring(shiftCount, shiftCount + M).equals(pattern);
    }
    protected void shift(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
}
