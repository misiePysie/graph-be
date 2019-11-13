package misiepysie.graph_be.Modules;

public class EdgePackage {
    private  String to;
    private  String from;
    private int weight=1; //weight jako suma wszystich weightow z modułow

    public EdgePackage(String to, String from) {
        this.to = to;
        this.from = from;
        this.weight=1;
    }

    public EdgePackage() {
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

    public  int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgePackage{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", weight=" + weight +
                '}';
    }
}
