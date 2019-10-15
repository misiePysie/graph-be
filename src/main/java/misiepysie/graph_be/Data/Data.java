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

//    @Override
//    public String toString() {
//            String tempNodes="";
//            for(int i=0;i<this.nodesData.size();i++){
//                tempNodes+=this.nodesData.get(i).toString();
//                while(i<this.nodesData.size()-1){
//                    tempNodes+=",";
//                    tempNodes+='\n';
//                }
//                if(i==this.nodesData.size()-1){
//                    tempNodes+="'\n'";
//                    tempNodes+="],";
//                    tempNodes+='\n';
//                }
//            }
//
//            String tempEdges="";
//
//            for(int i=0;i<this.edgesData.size();i++){
//                tempEdges+=this.edgesData.get(i).toString();
//                while(i<this.edgesData.size()-1){
//                    tempEdges+=",";
//                    tempNodes+='\n';
//                }
//                if(i==this.edgesData.size()-1){
//                    tempEdges+="'\n'";
//                    tempEdges+="]";
//                }
//            }
//
//            return ("{"+'\n'+"nodesData: ["+tempNodes+"edgesData: ["+tempEdges);
//        }
    }

