package misiepysie.graph_be.GraphObjects;

import com.google.gson.Gson;

public class Edge {
    private Node from;
    private Node to;
    private int weight;

//    @Override
//    public String toString() {
//        return this.from.getId() +
//                "," + this.to.getId() +
//                "," + this.weight +"\n";
//    }



    public Edge(Node from, Node to, int numberOfConnections) {
        this.from = from;
        this.to = to;
        this.weight = numberOfConnections;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
}

}
