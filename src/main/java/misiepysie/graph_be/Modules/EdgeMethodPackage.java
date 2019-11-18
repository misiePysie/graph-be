package misiepysie.graph_be.Modules;

public class EdgeMethodPackage {
    private  String to;
    private  String from;
    private int weight;

    public EdgeMethodPackage(String to, String from) {
        this.to = to;
        this.from = from;
        this.weight=1;
    }

    public EdgeMethodPackage(String to, String from, int weight) {
        this.to = to;
        this.from = from;
        this.weight = weight;
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
                ", from='" + from + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
