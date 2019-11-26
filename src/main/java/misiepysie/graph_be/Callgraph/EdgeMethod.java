package misiepysie.graph_be.Callgraph;

public class EdgeMethod {
    private  NodeMethod to;
    private  NodeMethod from;
    private int weight;

    public EdgeMethod(NodeMethod to,NodeMethod from) {
        this.to=to;
        this.from=from;
        this.weight=1;
    }

    public EdgeMethod(NodeMethod to, NodeMethod from, int weight) {
        this.to = to;
        this.from = from;
        this.weight = weight;
    }

    public NodeMethod getTo() {
        return to;
    }

    public NodeMethod getFrom() {
        return from;
    }

    public EdgeMethod() {
    }

    public void setTo(NodeMethod to) {
        this.to = to;
    }

    public void setFrom(NodeMethod from) {
        this.from = from;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
      return  "{\"to\":\""+this.to+"\",\"from\":\""+this.from+"\",\"weight\":"+this.weight +"}";
    }
}
