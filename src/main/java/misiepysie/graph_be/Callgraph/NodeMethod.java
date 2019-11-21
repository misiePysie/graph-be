package misiepysie.graph_be.Callgraph;

public class NodeMethod {


    private String name;
    private int size;



    private String color;

    public NodeMethod(String name) {
        this.name = name;
        this.size=10;
    }
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "NodeMethod{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", color=" + color +
                '}';
    }
}
