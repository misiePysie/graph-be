package misiepysie.graph_be.Modules;

public class EdgePackage {
    private  String to;
    private  String from;
    private static int weight; //weight jako suma wszystich weightow z modułow (dlatego static)

    public EdgePackage(String to, String from) {
        this.to = to;
        this.from = from;
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

    public static int getWeight() {
        return weight;
    }

    public static void setWeight(int weight) {
        EdgePackage.weight = weight;
    }
}
