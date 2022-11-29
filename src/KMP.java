public class KMP extends Algorithm{
    public KMP(String sequence, String pattern) {
        super(sequence, pattern);
    }
    @Override
    public void execute() {
        match();
    }
    public int[] prefixFunction() {
        System.out.println("Prefix function:");
        int[] result = new int[M];
        for (int i = 0; i < M; i++) {
            String current = pattern.substring(0, i+1);
            result[i] = findPrefix(current);
            for (int j = 0; j < i+1; j++) {
                char c = current.charAt(j);
                if (j < result[i]) {
                    System.out.print(ColorCode.ANSI_GREEN);
                } else if (j >= i + 1 - result[i]){
                    System.out.print(ColorCode.ANSI_BLUE);
                } else  {
                    System.out.print(ColorCode.ANSI_RESET);
                }
                System.out.print(c);
            }
            System.out.println(ColorCode.ANSI_RESET + ":" + result[i]);
        }
        return result;
    }
    private int findPrefix(String str) {
        int found = 0;
        for (int i = 0; i < str.length()-1; i++) {
            String target = str.substring(0, i+1);
            String actual = str.substring(str.length()-i-1);
            if (target.equals(actual)) {
                found = i + 1;
            }
        }
        return found;
    }

    public void match() {
        int[] prefix = prefixFunction();
        int q = 0;
        System.out.println("Algorithm:");
        System.out.println(sequence);
        for (int i = 0; i < N; i++) {
            char currentChar = sequence.charAt(i);
            while (q > 0 && pattern.charAt(q) != currentChar) {
                shift(i-q);
                q = prefix[q - 1];
                System.out.println(pattern);
            }
            char charAtQ = pattern.charAt(q);
            if (charAtQ == sequence.charAt(i)) {
                q = q + 1;
                if (q == M) {
                    this.shift(i-M+1);
                    result = i - M + 1;
                    System.out.println(ColorCode.ANSI_GREEN + pattern + ColorCode.ANSI_RESET);
                    break;
                }
            }
            else {
                shift(i-q);
                System.out.println(pattern);
                q = 0;
            }

        }
    }
}
