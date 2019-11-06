package misiepysie.graph_be.Callgraph;

import java.util.ArrayList;

public class DataCallGraph {
    private static ArrayList<String> methodsToArray=new ArrayList<String>();
    private static ArrayList<String> methodsFromArray=new ArrayList<String>();

    private static ArrayList<EdgeMethod> edgesOfMethods=new ArrayList<EdgeMethod>();

    public DataCallGraph() {
    }



    public static ArrayList<EdgeMethod> getEdgesOfMethods() {
        return edgesOfMethods;
    }

    public static ArrayList<String> getMethodsToArray() {
        return methodsToArray;
    }

    public static ArrayList<String> getMethodsFromArray() {
        return methodsFromArray;
    }

    public static void setMethodsToArray(ArrayList<String> methodsToArray) {
        DataCallGraph.methodsToArray = methodsToArray;
    }

    public static void setMethodsFromArray(ArrayList<String> methodsFromArray) {
        DataCallGraph.methodsFromArray = methodsFromArray;
    }

    public static void setEdgesOfMethods(ArrayList<EdgeMethod> edgesOfMethods) {
        DataCallGraph.edgesOfMethods = edgesOfMethods;
    }
}
