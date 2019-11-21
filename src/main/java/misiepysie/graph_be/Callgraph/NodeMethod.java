package misiepysie.graph_be.Callgraph;

public class NodeMethod {
    private String name;
    private int size;

    public NodeMethod(String name) {
        this.name = name;
        this.size=10;
    }

    @Override
    public String toString() {
        return "NodeMethod{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
