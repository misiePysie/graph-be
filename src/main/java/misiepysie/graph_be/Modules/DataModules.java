package misiepysie.graph_be.Modules;

import java.util.ArrayList;

public class DataModules {

    private static ArrayList<NodePackage> ListOfNodePackage=new ArrayList<NodePackage>();
    private static ArrayList<EdgeMethodPackage> listOfEdgeMethodPackage=new ArrayList<>();
    private static ArrayList<EdgePackage> listOfEdgePackage =new ArrayList<EdgePackage>();

    public DataModules(ArrayList<NodePackage> ListOfNodePackage,ArrayList<EdgeMethodPackage> listOfEdgeMethodPackage,ArrayList<EdgePackage> listOfEdgePackage) {
    this.ListOfNodePackage=ListOfNodePackage;
    this.listOfEdgeMethodPackage=listOfEdgeMethodPackage;
    this.listOfEdgePackage=listOfEdgePackage;
    }


    public static ArrayList<NodePackage> getListOfNodePackage() {
        return ListOfNodePackage;
    }

    public static void setListOfNodePackage(ArrayList<NodePackage> listOfNodePackage) {
        ListOfNodePackage = listOfNodePackage;
    }

    public static ArrayList<EdgeMethodPackage> getListOfEdgeMethodPackage() {
        return listOfEdgeMethodPackage;
    }

    public static void setListOfEdgeMethodPackage(ArrayList<EdgeMethodPackage> listOfEdgeMethodPackage) {
        DataModules.listOfEdgeMethodPackage = listOfEdgeMethodPackage;
    }

    public static ArrayList<EdgePackage> getListOfEdgePackage() {
        return listOfEdgePackage;
    }

    public static void setListOfEdgePackage(ArrayList<EdgePackage> listOfEdgePackage) {
        DataModules.listOfEdgePackage = listOfEdgePackage;
    }
}
