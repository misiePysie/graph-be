package misiepysie.graph_be.Modules;

import java.util.ArrayList;

public class DataModules {

    private  ArrayList<NodePackage> ListOfNodePackage=new ArrayList<NodePackage>();
    private  ArrayList<EdgeMethodPackage> listOfEdgeMethodPackage=new ArrayList<>();
    private  ArrayList<EdgePackage> listOfEdgePackage =new ArrayList<EdgePackage>();

    public DataModules(ArrayList<NodePackage> ListOfNodePackage,ArrayList<EdgeMethodPackage> listOfEdgeMethodPackage,ArrayList<EdgePackage> listOfEdgePackage) {
    this.ListOfNodePackage=ListOfNodePackage;
    this.listOfEdgeMethodPackage=listOfEdgeMethodPackage;
    this.listOfEdgePackage=listOfEdgePackage;
    }


    public  ArrayList<NodePackage> getListOfNodePackage() {
        return ListOfNodePackage;
    }

    public  void setListOfNodePackage(ArrayList<NodePackage> listOfNodePackage) {
        ListOfNodePackage = listOfNodePackage;
    }

    public  ArrayList<EdgeMethodPackage> getListOfEdgeMethodPackage() {
        return listOfEdgeMethodPackage;
    }

    public  void setListOfEdgeMethodPackage(ArrayList<EdgeMethodPackage> listOfEdgeMethodPackage) {
        listOfEdgeMethodPackage = listOfEdgeMethodPackage;
    }

    public  ArrayList<EdgePackage> getListOfEdgePackage() {
        return listOfEdgePackage;
    }

    public  void setListOfEdgePackage(ArrayList<EdgePackage> listOfEdgePackage) {
        listOfEdgePackage = listOfEdgePackage;
    }

    @Override
    public String toString() {
        return "DataModules{" +
                "ListOfNodePackage=" + ListOfNodePackage +
                ", listOfEdgeMethodPackage=" + listOfEdgeMethodPackage +
                ", listOfEdgePackage=" + listOfEdgePackage +
                '}';
    }
}
