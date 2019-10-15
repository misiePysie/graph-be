package misiepysie.graph_be.Data;

import misiepysie.graph_be.GraphObjects.Edge;
import misiepysie.graph_be.GraphObjects.Node;

import java.util.ArrayList;

public class DataApi {

    private ArrayList<Node> nodesData= new ArrayList<Node>();
    private ArrayList<Edge> edgesData= new ArrayList<Edge>();

    public DataApi(ArrayList<Node> nodesData, ArrayList<Edge> edgesData) {
        this.nodesData = nodesData;
        this.edgesData = edgesData;
    }

    public ArrayList<Node> getNodesData() {
        return nodesData;
    }

    public void setNodesData(ArrayList<Node> nodesData) {
        this.nodesData = nodesData;
    }

    public ArrayList<Edge> getEdgesData() {
        return edgesData;
    }

    public void setEdgesData(ArrayList<Edge> edgesData) {
        this.edgesData = edgesData;
    }
}
