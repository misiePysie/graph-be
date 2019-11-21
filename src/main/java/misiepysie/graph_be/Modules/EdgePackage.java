package misiepysie.graph_be.Modules;

public class EdgePackage {
    private  NodePackage to;
    private  NodePackage from;
    private int weight=1; //weight jako suma wszystich weightow z modułow

    public EdgePackage(NodePackage to, NodePackage from) {
        this.to = to;
        this.from = from;
        this.weight=1;
    }

    public EdgePackage() {
    }

    public NodePackage getTo() {
        return to;
    }

    public void setTo(NodePackage to) {
        this.to = to;
    }

    public NodePackage getFrom() {
        return from;
    }

    public void setFrom(NodePackage from) {
        this.from = from;
    }

    public  int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgePackage{" +
                "to='" + to+ '\'' +
                ", from='" + from + '\'' +
                ", weight=" + weight +
                '}';
    }
}
