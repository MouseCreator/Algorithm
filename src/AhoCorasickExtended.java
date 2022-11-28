import java.util.ArrayList;
import java.util.List;

public class AhoCorasickExtended extends AhoCorasick{
    private String sequence;
    private ArrayList<String> patterns;

    private int nodeCount = 0;

    public AhoCorasickExtended(String sequence, String pattern) {
        super(sequence, pattern);
    }
    public AhoCorasickExtended(String sequence, String patterns[]) {
        super(sequence, patterns[0]);
        this.sequence = sequence;
        this.patterns = new ArrayList<>(List.of(patterns));
    }
    private final TreeNode root = new TreeNode(0);
    private void buildTree() {
        for (String s: patterns) {
            buildSubtree(0, root, s);
        }
    }
    private void buildSubtree(int q, TreeNode current, String from) {
        if (q == from.length()) {
            current.setWord(from);
            return;
        }
        char ch = from.charAt(q);
        if (current.hasEdgeTo(ch)) {
            buildSubtree(q+1, current.getTo(ch), from);
        } else {
            nodeCount++;
            TreeEdge newEdge = new TreeEdge(nodeCount, ch);
            current.addEdge(newEdge);
            buildSubtree(q+1, newEdge.getLeadsTo(), from);
        }
        return;
    }

    public void printTree() {
        System.out.println(ColorCode.ANSI_YELLOW + "Tree:" + ColorCode.ANSI_RESET);
        printTreeRecursive(0, 0, root);
        System.out.println();
    }
    private void printTreeRecursive(int depth, int charCount, TreeNode current) {
        String appended = "(" + current.getWord() + ")";
        charCount += appended.length();
        System.out.print(appended);
        for (TreeEdge e : current.edges) {
            char ch = e.getEdgeValue();
            int toAdd = printEdge(ch);
            TreeNode to = e.getLeadsTo();
            printTreeRecursive(depth+1, charCount+toAdd, to);
            System.out.println();
            printDepthValue(charCount);
        }

    }
    private void printDepthValue(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
    }
    private int printEdge(char ch) {
        final String toPrint = "--" + ch + "-->";
        System.out.print(toPrint);
        return toPrint.length();
    }
    public void execute() {
        this.buildTree();
        this.printTree();
    }
    public void printAutomata() {
        printAutomata(0, root);
        System.out.println();
    }
    private void printAutomata(int charCount, TreeNode current) {
        String appended = "(" + current.getNum() + ")";
        charCount += appended.length();
        System.out.print(appended);
        for (TreeEdge e : current.edges) {
            char ch = e.getEdgeValue();
            int toAdd = printEdge(ch);
            TreeNode to = e.getLeadsTo();
            printAutomata(charCount+toAdd, to);
            System.out.println();
            printDepthValue(charCount);
        }
    }
}
