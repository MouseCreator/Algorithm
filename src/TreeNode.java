import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TreeNode {
    private String word = "";

    int num = -1;
    public TreeNode(int num) {
        this.num = num;
        edges = new ArrayList<>();
    }
    public TreeNode(int num, String word) {
        this.num = num;
        this.word = word;
        edges = new ArrayList<>();
    }
    public boolean hasWord() {
        return word.isEmpty();
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public boolean hasEdgeTo(char to) {
        return edges.contains(new TreeEdge(0, to));
    }
    public void addEdge(TreeEdge edge) {
        this.edges.add(edge);
    }
    public TreeNode getTo(char to) {
        for (TreeEdge e : edges) {
            if (e.getEdgeValue() == to)
                return e.getLeadsTo();
        }
        throw new NoSuchElementException();
    }
    ArrayList<TreeEdge> edges;

    public int getNum() {
        return num;
    }
}
