
import java.util.HashMap;

public class ShiftAnd extends Algorithm{
    public ShiftAnd(String sequence, String pattern) {
        super(sequence, pattern);
    }
    private int[][] matrixM;
    HashMap<Character, String> masks;
    private void buildMasks() {
        masks = new HashMap<>();
        for (char ch : sequence.toCharArray()) {
            String toPut = "0".repeat(M);
            masks.put(ch,toPut);
        }


        for (char ch : pattern.toCharArray()) {
            StringBuilder toPut = new StringBuilder();
            for (char ch2 : pattern.toCharArray()) {
                toPut.append(ch == ch2 ? "1" : "0");
            }
            masks.put(ch,toPut.toString());
        }
        System.out.println(ColorCode.ANSI_YELLOW + "Masks:" + ColorCode.ANSI_RESET);
        for (Character key : masks.keySet()) {
            System.out.println(key + ":" + masks.get(key));
        }
    }

    private void buildMatrixM() {
        this.matrixM = new int[N+1][M];
        System.out.println(ColorCode.ANSI_YELLOW + "Calculating matrix M:" + ColorCode.ANSI_RESET);
        String prev = "0".repeat(M);
        for (int i = 1; i <= N ; i++) {
            String shifted = bitShift(prev);
            String fromChar = masks.get(sequence.charAt(i-1));
            String newStr = stringAnd(shifted, fromChar);
            System.out.println(prev + "->" + shifted + ", " + shifted + " & " + fromChar + " = " + newStr);
            prev = newStr;
            appendMatrix(i, prev);
        }
    }

    private void printMatrixM() {
        System.out.println(ColorCode.ANSI_YELLOW + "Matrix M" + ColorCode.ANSI_RESET);
        System.out.print("\t\t\t" + ColorCode.ANSI_BLUE);
        for (int i = 0; i < N; i++) {
            System.out.print(sequence.charAt(i) + "\t");
        }
        System.out.println();
        System.out.print("\t\t");
        for (int i = 0; i < N + 1; i++) {
            System.out.print(i + "\t");
        }
        System.out.println(ColorCode.ANSI_RESET);
        for (int i = 0; i < M; i++) {
            System.out.print(ColorCode.ANSI_BLUE + pattern.charAt(i) + "\t" + (i + 1) + "\t" + ColorCode.ANSI_RESET);
            for (int j = 0; j < N + 1; j++) {
                int val = matrixM[j][i];
                if (val == 1) {
                    if (i == M - 1) {
                        System.out.print(ColorCode.ANSI_GREEN + val + ColorCode.ANSI_RESET + "\t");
                    } else {
                        System.out.print(ColorCode.ANSI_YELLOW + val + ColorCode.ANSI_RESET + "\t");
                    }
                } else {
                    System.out.print(val + "\t");
                }
            }
            System.out.println();
        }
    }

    private void appendMatrix(int n, String from) {
        for (int i = 0; i < M; i++) {
            matrixM[n][i] = from.charAt(i) - '0';
        }
    }

    private String bitShift(String prev) {
        return "1" + prev.substring(0, M - 1);
    }

    private String stringAnd(String a, String b) {
        StringBuilder prevBuilder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (a.charAt(i) == '1' && b.charAt(i) == '1')
                prevBuilder.append("1");
            else
                prevBuilder.append("0");
        }
        return prevBuilder.toString();
    }

    public void execute() {
        buildMasks();
        buildMatrixM();
        printMatrixM();
    }
}
