package misiepysie.graph_be.Callgraph;

import com.google.gson.Gson;

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

    public EdgeMethod() {
    }

    public static void setTo(String to) {
        EdgeMethod.to = to;
    }

    public static void setFrom(String from) {
        EdgeMethod.from = from;
    }

    @Override
    public String toString() {
      return  "{\"to\":\""+this.to+"\",\"from\":\""+this.from+"\"}";
    }
}
