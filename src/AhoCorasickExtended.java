import java.util.ArrayList;

public class AhoCorasickExtended extends AhoCorasick{
    private final ArrayList<String> patterns;

    private int nodeCount = 0;

    public AhoCorasickExtended(String sequence, String[] patterns) {
        super(sequence, patterns[0]);
        this.patterns = new ArrayList<>();
        for (String s : patterns) {
            this.patterns.add(formatString(s));
        }
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
        boolean first = true;
        for (TreeEdge e : current.edges) {
            char ch = e.getEdgeValue();
            if (!first)
                printDepthValue(charCount);
            int toAdd = printEdge(ch);
            TreeNode to = e.getLeadsTo();
            printTreeRecursive(depth+1, charCount+toAdd, to);
            first = false;
        }
        if (current.edges.isEmpty()) {
            System.out.println();
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
        this.printAutomata();
        this.appendAutomata();
    }
    public void printAutomata() {
        System.out.println(ColorCode.ANSI_YELLOW + "Automata:" + ColorCode.ANSI_RESET);
        printAutomata(0, root, "");
        System.out.println();
    }
    private void printAutomata(int charCount, TreeNode current, String word) {
        String appended = "(" + current.getNum() + ")";
        charCount += appended.length();
        System.out.print(appended);
        boolean first = true;
        for (TreeEdge e : current.edges) {
            char ch = e.getEdgeValue();
            if (!first)
                printDepthValue(charCount);
            int toAdd = printEdge(ch);
            printAutomata(charCount+toAdd, e.getLeadsTo(), word+e.getEdgeValue());
            first = false;
        }

        if (current.edges.isEmpty()) {
            System.out.print(" : { ");
            for (String pattern : patterns) {
                if (word.endsWith(pattern)) {
                    System.out.print(pattern + " ");
                }
            }
            System.out.println("}");
        }

    }
    @Override
    protected void computeAlphabet() {
        alphabet = new ArrayList<>();
        for (char ch : sequence.toCharArray()) {
            if (!alphabet.contains(ch)) {
                alphabet.add(Character.toUpperCase(ch));
            }

        }
        for (String pattern : patterns) {
            for (char ch : pattern.toCharArray()) {
                if (!alphabet.contains(ch)) {
                    alphabet.add(Character.toUpperCase(ch));
                }
            }
        }
    }
    public void appendAutomata() {
        computeAlphabet();
        ArrayList<IntPair> appendedPairs = new ArrayList<>();
        buildTransitions(appendedPairs, root, "");
        printAdditional(appendedPairs);
        System.out.println();
    }
    private void printAdditional(ArrayList<IntPair> appendedPairs) {
        System.out.println(ColorCode.ANSI_GREEN + "Additional edges:" + ColorCode.ANSI_RESET);
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < appendedPairs.size(); i++) {
            IntPair pair = appendedPairs.get(i);
            if (pair.to != 0) {
                builder.append(ColorCode.ANSI_PURPLE)
                .append(pair)
                .append(ColorCode.ANSI_RESET);
            }
            else {
                builder.append(pair);
            }
            if (i != appendedPairs.size()-1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        System.out.println(builder);
    }
    private void buildTransitions(ArrayList<IntPair> appended, TreeNode current, String haveWord) {
        for (char ch : alphabet) {
            if (current.hasEdgeTo(ch)) {
                buildTransitions(appended, current.getTo(ch), haveWord+ch);
            }
            else {
                findOccurrences(current, appended, haveWord);
            }
        }
    }
    private void findOccurrences(TreeNode exception, ArrayList<IntPair> appended, String forWord) {
       for (int i = 0; i < forWord.length(); i++) {
           String longestSuffix = forWord.substring(i);
           for (String pattern : patterns) {
               if (!pattern.startsWith(longestSuffix)) {
                   continue;
               }
               TreeNode toAdd = appendAutomata(root, longestSuffix);
               if (toAdd != exception) {
                   appendUnique(exception, toAdd, appended);
                   return;
               }
           }
       }
    }
    private void appendUnique(TreeNode from, TreeNode to, ArrayList<IntPair> appended) {
        IntPair pair = new IntPair(from.num, to.num);
        if (!appended.contains(pair)) {
            appended.add(pair);
        }
    }
    private TreeNode appendAutomata(TreeNode current, String prefix) {
        if (prefix.isEmpty())
            return current;
        char moveTo = prefix.charAt(0);
        assert current.hasEdgeTo(moveTo);
        return appendAutomata(current.edgeTo(moveTo), prefix.substring(1));
    }

}
