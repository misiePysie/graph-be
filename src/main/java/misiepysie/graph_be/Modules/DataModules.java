package misiepysie.graph_be.Modules;

import java.util.ArrayList;

public class DataModules {

      ArrayList<String> listOFNamesNodePackage=new ArrayList<>();
    ArrayList<EdgeMethodPackage> listOfTempEdgeMethodPackage=new ArrayList<>();
    ArrayList<EdgePackage> listOfTempEdgePackage =new ArrayList<EdgePackage>();
    private static ArrayList<NodePackage> ListOfNodePackage=new ArrayList<NodePackage>();
    private static ArrayList<EdgeMethodPackage> listOfEdgeMethodPackage=new ArrayList<>();
    private static ArrayList<EdgePackage> listOfEdgePackage =new ArrayList<EdgePackage>();

    public DataModules(ArrayList<String> listOFNamesNodePackage, ArrayList<EdgeMethodPackage> listOfTempEdgeMethodPackage, ArrayList<EdgePackage> listOfTempEdgePackage) {
        this.listOFNamesNodePackage = listOFNamesNodePackage;
        this.listOfTempEdgeMethodPackage = listOfTempEdgeMethodPackage;
        this.listOfTempEdgePackage = listOfTempEdgePackage;
    }


    //todo: przerobienie list z Package i Edge Package na listy stringowe
    /*todo: przesylamy:
        1.liste packagow jako nodes (packages)
        2.liste metod( jako podzbior packagow)
        3.liste edgow Packagow (edge package-package weight jako suma wszystkich weightow z metod)
        4.liste edgow metod (edge metoda-metoda, weight)
     */

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
