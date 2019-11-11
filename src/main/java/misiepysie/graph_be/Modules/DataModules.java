package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.DataCallGraph;
import misiepysie.graph_be.Callgraph.EdgeMethod;
import misiepysie.graph_be.GraphObjects.Edge;

import java.util.ArrayList;

public class DataModules {
    private static ArrayList<String> listOfPackages=new ArrayList<String>();
    private static ArrayList<String> listOfPackageEdges=new ArrayList<String>();
    private static ArrayList<EdgeMethod> listOfMethodEdges= DataCallGraph.getEdgesOfMethods();

    private static ArrayList<Package>  listOfWorkPackages=new ArrayList<Package>();
    private static ArrayList<EdgePackage> listOfWorkEdges=new ArrayList<EdgePackage>();


    //todo: przerobienie list z Package i Edge Package na listy stringowe
    /*todo: przesylamy:
        1.liste packagow jako nodes (packages)
        2.liste metod( jako podzbior packagow)
        3.liste edgow Packagow (edge package-package weight jako suma wszystkich weightow z metod)
        4.liste edgow metod (edge metoda-metoda, weight)
     */
}
