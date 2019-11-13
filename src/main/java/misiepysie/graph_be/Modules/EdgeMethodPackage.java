package misiepysie.graph_be.Modules;

public class EdgeMethodPackage {
    private  String to;
    private  String from;
    private int weight;

    public EdgeMethodPackage(String to, String from) {
        this.to = to;
        this.from = from;
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


}
