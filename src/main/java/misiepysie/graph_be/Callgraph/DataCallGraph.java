package misiepysie.graph_be.Callgraph;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DataCallGraph {

    private  ArrayList<NodeMethod> methodsToArray=new ArrayList<NodeMethod>();
    private  ArrayList<NodeMethod> methodsFromArray=new ArrayList<NodeMethod>();
    private  ArrayList<EdgeMethod> edgesOfMethods=new ArrayList<EdgeMethod>();

    public DataCallGraph(ArrayList<NodeMethod> methodsToArray, ArrayList<NodeMethod> methodsFromArray, ArrayList<EdgeMethod> edgesOfMethods) {
        this.methodsToArray=methodsToArray;
        this.methodsFromArray=methodsFromArray;
        this.edgesOfMethods=edgesOfMethods;
    }



    public  ArrayList<EdgeMethod> getEdgesOfMethods() {
        return edgesOfMethods;
    }

    public  ArrayList<NodeMethod> getMethodsToArray() {
        return methodsToArray;
    }

    public  ArrayList<NodeMethod> getMethodsFromArray() {
        return methodsFromArray;
    }

    public  void setMethodsToArray(ArrayList<NodeMethod> methodsToArray) {
        methodsToArray = methodsToArray;
    }

    public  void setMethodsFromArray(ArrayList<NodeMethod> methodsFromArray) {
        methodsFromArray = methodsFromArray;
    }

    public  void setEdgesOfMethods(ArrayList<EdgeMethod> edgesOfMethods) {
        edgesOfMethods = edgesOfMethods;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return "{\"methodsToArray\":"+gson.toJson(this.methodsToArray)+",\"methodsFromArray\":"+gson.toJson(this.methodsFromArray)+",\"edgesOfMethods\":"+this.edgesOfMethods+"}";

    }
}
