package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.NodeMethod;

public class EdgeMethodPackage {
    private  NodePackage to;
    private NodeMethod from;
    private int weight;

    public EdgeMethodPackage(NodePackage to, NodeMethod from) {
        this.to = to;
        this.from = from;
        this.weight=1;
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "EdgeMethodPackage{" +
                "to='" + to + '\'' +
                ", from='" + from+ '\'' +
                ", weight=" + weight +
                '}';
    }

    public NodePackage getTo() {
        return to;
    }

    public void setTo(NodePackage to) {
        this.to = to;
    }

    public NodeMethod getFrom() {
        return from;
    }

    public void setFrom(NodeMethod from) {
        this.from = from;
    }
}
