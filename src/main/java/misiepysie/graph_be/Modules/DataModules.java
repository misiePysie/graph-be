package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.DataCallGraph;
import misiepysie.graph_be.Callgraph.EdgeMethod;
import misiepysie.graph_be.GraphObjects.Edge;

import java.util.ArrayList;
import java.util.List;

public class DataModules {

    private static List<String> listOfPackagesFrom=new ArrayList<String>();
    private static List<String> listOfPackagesTo=new ArrayList<String>();

    private static ArrayList<MisiePysiePackage> ListOfPackageMysiePysie=new ArrayList<MisiePysiePackage>();

    // private static ArrayList<String> listOfPackages=new ArrayList<String>();
    private static ArrayList<String> listOfPackageEdges=new ArrayList<String>();
    private static ArrayList<EdgeMethodPackage> listOfMethodEdges=new ArrayList<>();
    private static ArrayList<Package>  listOfWorkPackages=new ArrayList<Package>();
    private static ArrayList<EdgePackage> listOfWorkEdges=new ArrayList<EdgePackage>();
    public static ArrayList<EdgeMethodPackage> getListOfMethodEdges() {
        return listOfMethodEdges;
    }

    //todo: przerobienie list z Package i Edge Package na listy stringowe
    /*todo: przesylamy:
        1.liste packagow jako nodes (packages)
        2.liste metod( jako podzbior packagow)
        3.liste edgow Packagow (edge package-package weight jako suma wszystkich weightow z metod)
        4.liste edgow metod (edge metoda-metoda, weight)
     */

    public static ArrayList<MisiePysiePackage> getListOfPackageMysiePysie() {
        return ListOfPackageMysiePysie;
    }

    public static void setListOfMethodEdges(ArrayList<EdgeMethodPackage> listOfMethodEdges) {
        DataModules.listOfMethodEdges = listOfMethodEdges;
    }

    public static List<String> getListOfPackagesFrom() {
        return listOfPackagesFrom;
    }

    public static List<String> getListOfPackagesTo() {
        return listOfPackagesTo;
    }

    public static void addElementToListOfPackagesFrom(String name){
        listOfPackagesFrom.add(name);
    }

    public static void addElementToListOfPackagesTo(String name){
        listOfPackagesTo.add(name);
    }

}
