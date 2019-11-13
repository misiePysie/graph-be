package misiepysie.graph_be.Modules;

import java.util.ArrayList;

public class NodePackage {

    private String name;
    private ArrayList<String> methods=new ArrayList<>();
    private int weight;

    public NodePackage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMethods() {
        return methods;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NodePackage{" +
                "name='" + name + '\'' +
                ", methods=" + methods +
                ", weight=" + weight +
                '}';
    }
}
