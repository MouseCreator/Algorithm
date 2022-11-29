
import java.util.ArrayList;

public class AhoCorasick extends Algorithm{

    public AhoCorasick(String sequence, String pattern) {
        super(sequence, pattern);
    }
    @Override
    public void execute() {
        computeAlphabet();
        computeTransitionFunction();

    }
    protected void computeAlphabet() {
        alphabet = new ArrayList<>();
        for (char ch : sequence.toCharArray()) {
            if (!alphabet.contains(ch)) {
                alphabet.add(Character.toUpperCase(ch));
            }

        }
        for (char ch : pattern.toCharArray()) {
            if (!alphabet.contains(ch)) {
                alphabet.add(Character.toUpperCase(ch));
            }
        }
    }
    protected ArrayList<Character> alphabet;

    public int[][] getTransitions() {
        return transitions;
    }

    protected int[][] transitions;

    public void computeTransitionFunction() {
        transitions = new int[M+1][alphabet.size()];
        for (int q = 0; q <= M; q++) {
            for (int i = 0; i < alphabet.size(); i++) {
                int k = Math.min(M + 1, q + 2);
                do {
                    k--;
                } while (isNotPrefix(k, q, alphabet.get(i)) && k > 0);
                transitions[q][i] = k;
            }
        }
        printTransitions();
    }
    public void printTransitions() {
        System.out.println("Transition function");
        System.out.print('\t');
        for (char ch : alphabet) {
            System.out.print(ch + "\t");
        }
        System.out.println("Pattern");
        for (int i = 0; i <= M; i++) {
            System.out.print(ColorCode.ANSI_BLUE + i + ColorCode.ANSI_RESET + "\t");
            for (int j = 0; j < alphabet.size(); j++) {
                if (i != M && alphabet.get(j) == pattern.charAt(i)) {
                    System.out.print(ColorCode.ANSI_YELLOW + transitions[i][j] + ColorCode.ANSI_RESET + "\t");
                }
                else {
                    System.out.print(transitions[i][j] + "\t");
                }
            }
            if (i == M) {
                System.out.println();
            }
            else {
                System.out.println(ColorCode.ANSI_BLUE + pattern.charAt(i) + ColorCode.ANSI_RESET);
            }
        }
    }
    protected boolean isNotPrefix(int k, int q, char a) {
        String qString = pattern.substring(0, q) + a;
        String kString = pattern.substring(0, k);
        int v = qString.lastIndexOf(kString);
        return v != q - k + 1;

    }

    private void match() {
        System.out.print(ColorCode.ANSI_YELLOW + "Q:" + ColorCode.ANSI_RESET);
        int q = 0;
        for (int i = 0; i < N; i++) {
            q = transitions[q][alphabet.indexOf(sequence.charAt(i))];
            System.out.print(" " + q);
            if (q == M) {
                break;
            }
        }
        System.out.println();
    }

}
