package misiepysie.graph_be.Callgraph;

import java.io.*;

public class AnalyzeCalls {


    public static void analyzeCallGraph(String path, DataCallGraph temp) throws FileNotFoundException {

        File calls = new File(path);

        BufferedReader reader = new BufferedReader(new FileReader(calls));
        String line,  methodTo,methodFrom,temporary1,temporary2;
        String[] modules,submodulesTo,submodulesFrom;
        String[] submethods;

        try {

            while ((line=reader.readLine()) != null) {

                if (line.contains("(M)" + ".graph_be.") || line.contains("(O)misiepysie.graph_be.") || line.contains("(S)misiepysie.graph_be")) {

                    line.trim();

                    modules = line.split(" ");
                    submodulesTo = modules[0].split(":");
                    temporary1 = submodulesTo[submodulesTo.length - 1];//nazwa metody to
                    temporary2=submodulesTo[submodulesTo.length-1].substring(temporary1.indexOf('(')+1,temporary1.indexOf(")"));
                    methodTo=temporary1.replace(temporary2,"");

                    submodulesFrom = modules[1].split(":");
                    temporary1 = submodulesFrom[submodulesFrom.length - 1];//nazwa metody to
                    temporary2 = submodulesFrom[submodulesFrom.length - 1].substring(temporary1.indexOf('(') + 1, temporary1.indexOf(")"));
                    methodFrom = temporary1.replace(temporary2, "");
//
//

                    int weight = 1;
                    boolean isEdgeAdded = false;
                    boolean isNodeAdded=false;
                    EdgeMethod tempEdge;
                    //dodanie nowych nodow do listy jesli ich nie ma:
                    NodeMethod tempFrom = new NodeMethod(methodFrom);
                    NodeMethod tempTo = new NodeMethod(methodTo);
                    for (NodeMethod node:temp.getMethodsFromArray()
                         ) {
                        if(node.getName().equals(methodFrom))
                        {
                            isNodeAdded=true;
                        }
                    }
                    if(!isNodeAdded)
                    {
                        temp.getMethodsFromArray().add(tempFrom);
                    }
                    isNodeAdded=false;
                    for (NodeMethod node:temp.getMethodsToArray()
                    ) {
                        if(node.getName().equals(methodTo))
                        {
                            isNodeAdded=true;
                        }
                    }
                    if(!isNodeAdded)
                    {
                        temp.getMethodsToArray().add(tempTo);
                    }



                    for (EdgeMethod e : temp.getEdgesOfMethods()) {
                        if (e.getFrom().getName().equals(methodFrom) && e.getTo().getName().equals(methodTo)) {
                            e.setWeight(e.getWeight() + 1);
                            isEdgeAdded = true;
                        }
                    }
                    if (!isEdgeAdded) {
                        tempEdge = new EdgeMethod(tempTo, tempFrom);
                        temp.getEdgesOfMethods().add(tempEdge);
                    }
            }
        }
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }
}
}


