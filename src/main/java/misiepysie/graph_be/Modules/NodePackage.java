package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.NodeMethod;

import java.util.ArrayList;
import java.util.Random;

public class NodePackage {

    private String name;
    private ArrayList<NodeMethod> methods=new ArrayList<>();
    private String color;
    private int size;



    public NodePackage(String name) {
        this.name = name;
        this.size=100;
        Random r=new Random();
        Colors[] tmpColors=Colors.values();
        int index = new Random().nextInt(tmpColors.length);
        this.color=tmpColors[index].toString();
    }

    public String getName() {
        return name;
    }

    public ArrayList<NodeMethod> getMethods() {
        return methods;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NodePackage{" +
                "\"name\":\"" + name + '\"' +
                ",\"methods\":\"" + methods +"\""+
                ",\"color\":\""+this.color+"\""+
                ",\"size\":\""+this.size+"\"}";
    }
}
