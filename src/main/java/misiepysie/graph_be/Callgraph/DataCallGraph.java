package misiepysie.graph_be.Callgraph;

import java.util.ArrayList;

public class DataCallGraph {
    private static ArrayList<String> methodsToArray=new ArrayList<>();
    private static ArrayList<String> methodsFromArray=new ArrayList<>();

    private static ArrayList<EdgeMethod> edgesOfMethods=new ArrayList<>();



    public static ArrayList<EdgeMethod> getEdgesOfMethods() {
        return edgesOfMethods;
    }

    public static ArrayList<String> getMethodsToArray() {
        return methodsToArray;
    }

    public static ArrayList<String> getMethodsFromArray() {
        return methodsFromArray;
    }

}
