public class TreeEdge {
    private char edgeValue;
    private TreeNode leadsTo;

    public char getEdgeValue() {
        return edgeValue;
    }
    public void setEdgeValue(char ch) {
        this.edgeValue = ch;
    }
    public TreeEdge(int num, char edgeValue) {
        this.edgeValue = edgeValue;
        this.leadsTo = new TreeNode(num);
    }
    public TreeEdge(char edgeValue, TreeNode to) {
        this.edgeValue = edgeValue;
        this.leadsTo = to;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof TreeEdge))
            return false;
        TreeEdge node = (TreeEdge) other;
        return this.edgeValue == ((TreeEdge) other).edgeValue;
    }

    public TreeNode getLeadsTo() {
        return leadsTo;
    }

    public void setLeadsTo(TreeNode leadsTo) {
        this.leadsTo = leadsTo;
    }
}
