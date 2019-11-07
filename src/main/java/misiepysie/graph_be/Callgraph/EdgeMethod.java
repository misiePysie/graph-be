package misiepysie.graph_be.Callgraph;

import com.google.gson.Gson;

public class EdgeMethod {
    private  String to;
    private  String from;





    public EdgeMethod(String to,String from) {
        this.to=to;
        this.from=from;
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

    @Override
    public String toString() {
      return  "{\"to\":\""+this.to+"\",\"from\":\""+this.from+"\"}";
    }
}
