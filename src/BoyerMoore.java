import java.util.HashMap;

public class BoyerMoore extends Algorithm {
    public BoyerMoore(String sequence, String pattern) {
        super(sequence, pattern);
    }
    private HashMap<Character, Integer> shiftTable = new HashMap<>();
    private int[] suffixTable;
    @Override
    public void execute() {
        createShiftTableAndPrint();
        longestPrefixWithPrint();
        buildSuffixTable();
        find();
    }

    public void createShiftTableAndPrint() {
        buildShiftTable();
        printShiftTable();
    }

    protected HashMap<Character, Integer> buildShiftTable() {

        for (int i = 0; i < N; i++) {
            shiftTable.put(sequence.charAt(i), M);
        }

        for (int i = 0; i < M - 1; i++) {
            shiftTable.put(pattern.charAt(i), M - 1 - i);
        }

        return shiftTable;
    }
    public void printShiftTable() {
        System.out.println(ColorCode.ANSI_YELLOW + "Shift Table:" + ColorCode.ANSI_RESET);
        shiftTable.keySet().stream().sorted().forEach((k) -> System.out.print(k + "\t"));
        System.out.println();
        shiftTable.keySet().stream().sorted().forEach((k) -> System.out.print(shiftTable.get(k) + "\t"));
        System.out.println();
    }

    protected int[] buildSuffixTable() {
        suffixTable = new int[M - 1];

        for (int i = 1; i < M; i++) {
            System.out.print("k = " + i + "\t");
            String activeString = pattern;
            String suffix = activeString.substring(M-i);
            activeString = activeString.substring(0, M-1);
            String reason;
            while(true) {
                int last = activeString.lastIndexOf(suffix);
                if (last == -1) {
                    int longestPrefix = longestPrefix();
                    if (longestPrefix == 0) {
                        suffixTable[i-1] = M;
                        reason = "as the length of the pattern";
                    }
                    else  {
                        suffixTable[i-1] = M - longestPrefix;
                        reason = "as " + M + " - " + longestPrefix + " = " + suffixTable[i-1] +
                                " (distance between prefix and suffix)";
                    }
                    System.out.print(pattern.substring(0, M-i) + ColorCode.ANSI_YELLOW + pattern.substring(M-i) + ColorCode.ANSI_RESET);
                    break;
                }
                if (last != 0 && pattern.charAt(M-i-1) == pattern.charAt(last-1)) {
                    activeString = activeString.substring(0, last);
                } else {
                    suffixTable[i-1] = M - i - last;
                    reason = "as distance between two instances of the suffix";
                    for (int j = 0; j < M; j++) {
                        char c = pattern.charAt(j);
                        if (j >= last && j < last+i) {
                            System.out.print(ColorCode.ANSI_GREEN);
                        } else if (j >= M - i){
                            System.out.print(ColorCode.ANSI_BLUE);
                        } else  {
                            System.out.print(ColorCode.ANSI_RESET);
                        }
                        System.out.print(c);
                    }
                    System.out.print(ColorCode.ANSI_RESET);
                    break;
                }
            }
            System.out.println("\td = " + suffixTable[i-1] + "\t" + reason);
        }
        return suffixTable;
    }
    private int longestPrefix() {
        int i = 1;
        int result = 0;
        while (i < M) {
            String str1 = pattern.substring(0, i);
            String str2 = pattern.substring(M-i);
            if (str1.equals(str2))
                result = i;
            i++;
        }
        return result;
    }

    private void longestPrefixWithPrint() {
        int prefix = longestPrefix();
        if (prefix == 0) {
            System.out.println("The pattern does not contain prefix that equals to suffix. Therefore "
                    + ColorCode.ANSI_YELLOW + "d2 = M = " + M + ColorCode.ANSI_RESET);
        } else {
            System.out.println("The longest prefix that equals to suffix is: " + prefix);
            System.out.println(ColorCode.ANSI_YELLOW + pattern.substring(0, prefix) + ColorCode.ANSI_RESET +
                    pattern.substring(prefix, Math.max(prefix, M - prefix))
                    + ColorCode.ANSI_BLUE + pattern.substring(M - prefix + 1) +
                    ColorCode.ANSI_RESET);
        }
    }
    public void setAlphabet(String alphabet) {
        this.shiftTable = new HashMap<>();
        char[] chars = alphabet.toUpperCase().replaceAll("[, ;]", "").toCharArray();
        for (char ch : chars) {
            shiftTable.put(ch, M);
        }
    }
    public void find() {
        int i = 0;
        System.out.println("Algorithm:");
        System.out.println(sequence);
        boolean found = false;
        while (!found && i < N - M + 1) {
            this.shift(i);
            boolean success = true;
            int k = 0;
            for (int j = M - 1; j >= 0; j--, k++) {
                char ch = sequence.charAt(j+i);
                if (ch != pattern.charAt(j)) {

                    if (k == 0) {
                        i += Math.max(shiftTable.get(ch) , 1);
                    }
                    else if (k > 0){
                        i += Math.max(shiftTable.get(ch) - k, suffixTable[k-1]);
                    }
                    else {
                        assert false;
                    }
                    success = false;
                    break;
                }
            }
            if (success) {
                System.out.println(ColorCode.ANSI_GREEN + pattern + ColorCode.ANSI_RESET);
                found = true;
            } else {
                System.out.println(pattern);
            }
        }
        this.result = found ? i : -1;
    }

}
