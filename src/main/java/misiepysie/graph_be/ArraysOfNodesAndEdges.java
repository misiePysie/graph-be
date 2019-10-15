package misiepysie.graph_be;

import java.util.ArrayList;
import java.util.List;

public class ArraysOfNodesAndEdges {
    private static List<Node> arrayListOfNodes = new ArrayList<Node>();

    private static List<Edge> arrayListOfEdges = new ArrayList<Edge>();

    public ArraysOfNodesAndEdges(List<Node> arrayListOfNodes, List<Edge> arrayListOfEdges) {
        this.arrayListOfNodes = arrayListOfNodes;
        this.arrayListOfEdges = arrayListOfEdges;
    }

    public static List<Node> getArrayListOfNodes() {
        return arrayListOfNodes;
    }

    public static void setArrayListOfNodes(List<Node> arrayListOfNodes) {
        ArraysOfNodesAndEdges.arrayListOfNodes = arrayListOfNodes;
    }

    public static List<Edge> getArrayListOfEdges() {
        return arrayListOfEdges;
    }

    public static void setArrayListOfEdges(List<Edge> arrayListOfEdges) {
        ArraysOfNodesAndEdges.arrayListOfEdges = arrayListOfEdges;
    }
}
