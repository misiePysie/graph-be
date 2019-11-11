package misiepysie.graph_be.Callgraph;

import com.google.gson.Gson;

public class EdgeMethod {
    private  String to;
    private  String from;
    private int weight;

    public EdgeMethod(String to,String from) {
        this.to=to;
        this.from=from;
    }
//konstruktor dodany, żby móc uwzględnić wage połączenia
    public EdgeMethod(String to, String from, int weight) {
        this.to = to;
        this.from = from;
        this.weight = weight;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public EdgeMethod() {
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
//todo: dodac weight do toStringa
    @Override
    public String toString() {
      return  "{\"to\":\""+this.to+"\",\"from\":\""+this.from+"\"}";
    }
}
