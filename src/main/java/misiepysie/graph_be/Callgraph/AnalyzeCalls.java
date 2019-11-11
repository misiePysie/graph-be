package misiepysie.graph_be.Callgraph;

import misiepysie.graph_be.GraphObjects.Edge;

import java.io.*;

import static misiepysie.graph_be.Callgraph.DataCallGraph.getEdgesOfMethods;

public class AnalyzeCalls {


    public static void analyzeCallGraph(String path, DataCallGraph temp) throws FileNotFoundException {

        File calls = new File(path);
        System.out.println(path);

        BufferedReader reader = new BufferedReader(new FileReader(calls));
        String line, methodFrom, methodTo,nameFrom,nameTo;
        String[] methods;
        String[] submethods;

        try {

            while ((line=reader.readLine()) != null) {

                if (line.contains("(M)" +
                        ".graph_be.") || line.contains("(O)misiepysie.graph_be.") || line.contains("(S)misiepysie.graph_be")) {

                    line.trim();

                    methods = line.split(" ");
                    methodTo = methods[0].substring(2);
                    submethods=methodTo.split(":");
                    nameTo=submethods[1];
                    methodFrom = methods[1].substring(3);
                    submethods=methodFrom.split(":");
                    nameFrom=submethods[1];

                    int weight=1;
                    boolean isEdgeAdded=false;
                    for (EdgeMethod e: temp.getEdgesOfMethods()) {
                        if(e.toString().toString().equals(new EdgeMethod(nameTo,nameFrom).toString())) {
                            weight++;
                            e.setWeight(weight);
                            isEdgeAdded=true;
                        }
                    }
                    if(isEdgeAdded==false)
                    {
                        temp.getMethodsFromArray().add(nameFrom);
                        temp.getMethodsToArray().add(nameTo);
                        getEdgesOfMethods().add(new EdgeMethod(nameTo,nameFrom));

                        //todo:zamienic ostatnia lininijke na przesylanie z waga (z tą na dole)
                        //getEdgesOfMethods().add(new EdgeMethod(methodTo,methodFrom,weight));

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


