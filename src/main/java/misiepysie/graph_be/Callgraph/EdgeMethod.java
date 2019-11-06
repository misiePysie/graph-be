package misiepysie.graph_be.Callgraph;

public class EdgeMethod {
    private static String to;
    private static String from;

    public EdgeMethod(String to,String from) {
        this.to=to;
        this.from=from;
    }



    public static String getTo() {
        return to;
    }

    public static String getFrom() {
        return from;
    }



}
