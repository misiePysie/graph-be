package misiepysie.graph_be.Data;

import misiepysie.graph_be.GraphObjects.Edge;
import misiepysie.graph_be.GraphObjects.Node;

import java.util.ArrayList;

public class Data {
    private static ArrayList<Node> nodesData = new ArrayList<Node>();

    private static ArrayList<Edge> edgesData = new ArrayList<Edge>();

    public Data(ArrayList<Node> nodesData, ArrayList<Edge> edgesData) {
        this.nodesData = nodesData;
        this.edgesData = edgesData;
    }

    public static ArrayList<Node> getNodesData() {
        return nodesData;
    }

    public static void setNodesData(ArrayList<Node> nodesData) {
        Data.nodesData = nodesData;
    }

    public static ArrayList<Edge> getEdgesData() {
        return edgesData;
    }

    public static void setEdgesData(ArrayList<Edge> edgesData) {
        Data.edgesData = edgesData;
    }

    public Data() {
    }

    }

