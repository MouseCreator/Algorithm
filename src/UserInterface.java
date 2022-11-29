import java.util.Scanner;

public class UserInterface {
    private final String unknownString = "Unknown";
    private int askForChoose() {
        String chooseAlgorithmMessage = """
                Choose algorithm:
                1 - Boyer Moore
                2 - KMP
                3 - Aho-Corasick (1 pattern)
                4 - Aho-Corasick (multiple patterns)
                5 - Shift-And
                6 - help
                7 - exit""";
        System.out.println(chooseAlgorithmMessage);
        int toPerform;
        while (true) {
            System.out.print("Algorithm: ");
            toPerform = getInteger();
            int numberOfOptions = 7;
            if (toPerform < 0 || toPerform > numberOfOptions) {
                System.out.println(unknownString);
            } else {
                return toPerform;
            }
        }

    }
    private int getInteger() {
        Scanner console = new Scanner(System.in);
        return Integer.parseInt(console.nextLine());
    }

    public void performAlgorithm() {
        boolean exitFlag = true;
        while (exitFlag) {
            int algorithm;
            try {
                algorithm = askForChoose();
            } catch (Exception e) {
                algorithm = 0;
            }
            try {
                switch (algorithm) {
                    case 1 -> boyer();
                    case 2 -> kmp();
                    case 3 -> aho();
                    case 4 -> ahoMultiple();
                    case 5 -> shift();
                    case 6 -> help();
                    case 7 -> exitFlag = false;
                    default -> System.out.println(unknownString);
                }
            } catch (Exception e) {
                System.err.println("Error occurred while performing this algorithm.");
            }
        }
    }
    private String getString(String input) {
        System.out.print(input);
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }
    private String getSequence() {
        return getString("Sequence: ");
    }
    private String getPattern() {
        return getString("Pattern: ");
    }
    private String getAlphabet() {
        return getString("Alphabet: ");
    }
    private void help() {
        System.out.println(
               """
               !!!HELP!!!
               Choose algorithm and follow the instructions.
               Boyer Moore:
                SEQUENCE - some text (may be skipped).
                PATTERN - pattern that you need to find in the text.
                ALPHABET - you may add some symbols to the alphabet, if they didn't appear in sequence or pattern.
                The program will build shift-table, suffix-table and demonstrate the performance.
               KMP:
                SEQUENCE - some text (may be skipped).
                PATTERN - pattern that you need to find in the text.
                The program will compute prefix-function and demonstrate the performance.
               Aho-Corasick:
                PATTERN - some text.
                The program will build automata for the entered pattern (as table).
               Aho-Corasick (Extended):
                PATTERNS - patterns, separated with spaces or commas.
                The program will build tree and automata for the patterns.
               Shift-And:
                SEQUENCE - some text.
                PATTERN - pattern that you need to find in the text.
                The program will build U-vectors and M-table. The calculations will be shown in the process."
               """);
    }
    private void boyer() {
        String sequence = getSequence();
        String pattern = getPattern();
        if (sequence.isEmpty()) {
            sequence = pattern;
        }
        BoyerMoore boyerMoore = new BoyerMoore(sequence, pattern);
        boyerMoore.setAlphabet(getAlphabet());
        boyerMoore.execute();
    }
    private void kmp() {
        String sequence = getSequence();
        String pattern = getPattern();
        if (sequence.isEmpty()) {
            sequence = pattern;
        }
        KMP kmp = new KMP(sequence, pattern);
        kmp.execute();
    }
    private void aho() {
        String pattern = getPattern();
        AhoCorasick ahoCorasick = new AhoCorasick("", pattern);
        ahoCorasick.execute();
    }
    private void ahoMultiple() {
        String[] patterns = getPatterns();
        AhoCorasickExtended ahoCorasick = new AhoCorasickExtended("", patterns);
        ahoCorasick.execute();
    }
    private String[] getPatterns() {
        String str = getString("Patterns: ");
        return str.split("[\\s,]+");
    }
    private void shift() {
        String sequence = getSequence();
        String pattern = getPattern();
        if (sequence.isEmpty()) {
            sequence = pattern;
        }
        ShiftAnd shiftAnd = new ShiftAnd(sequence, pattern);
        shiftAnd.execute();
    }
}
