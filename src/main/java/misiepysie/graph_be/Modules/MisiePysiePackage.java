package misiepysie.graph_be.Modules;

import java.util.ArrayList;

public class MisiePysiePackage {

    private String name;
    private ArrayList<String> methods=new ArrayList<>();

    public MisiePysiePackage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getMethods() {
        return methods;
    }


}
