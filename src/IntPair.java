public class IntPair {
    public int from;
    public int to;
    public IntPair(int from, int to) {
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "<" + from + ", " + to + ">";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof IntPair pair)) {
            return false;
        }
        return  (pair.from == this.from && pair.to == this.to);
    }
}
