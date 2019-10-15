package misiepysie.graph_be;

import java.util.ArrayList;

public class Data {

    private ArrayList<Nodes> nodesData = new ArrayList<Nodes>();
    private ArrayList<Edge> edgesData = new ArrayList<Edge>();

    public Data(ArrayList<Nodes> nodesData, ArrayList<Edge> edgesData) {
        this.nodesData = nodesData;
        this.edgesData = edgesData;
    }

    public ArrayList<Nodes> getNodesData() {
        return nodesData;
    }

    public void setNodesData(ArrayList<Nodes> nodesData) {
        this.nodesData = nodesData;
    }

    public ArrayList<Edge> getEdgesData() {
        return edgesData;
    }

    public void setEdgesData(ArrayList<Edge> edgesData) {
        this.edgesData = edgesData;
    }
}
