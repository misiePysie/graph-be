package misiepysie.graph_be.Data;

import misiepysie.graph_be.GraphObjects.Node;

import java.util.ArrayList;

public class DataApi {

    private ArrayList<Node> nodesData= new ArrayList<Node>();
    private ArrayList<EdgeApi> edgesData= new ArrayList<EdgeApi>();

    public DataApi(ArrayList<Node> nodesData, ArrayList<EdgeApi> edgesData) {
        this.nodesData = nodesData;
        this.edgesData = edgesData;
    }

    public ArrayList<Node> getNodesData() {
        return nodesData;
    }

    public void setNodesData(ArrayList<Node> nodesData) {
        this.nodesData = nodesData;
    }

    public ArrayList<EdgeApi> getEdgesData() {
        return edgesData;
    }

    public void setEdgesData(ArrayList<EdgeApi> edgesData) {
        this.edgesData = edgesData;
    }
}
