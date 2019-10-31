package misiepysie.graph_be.Callgraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyzeCalls {


    public static void analyzeCallGraph() throws FileNotFoundException {

        File calls=new File("F:\\Java\\Projects\\Graph\\graph-be.txt"); //todo: zalozylam ze mam ten plik w moim folderze, wiec soebie poprostu musicie pobrac i powinno smigac, tylko no za kazdym razem tzreba generowac sobei mowy plik
        Scanner reader=new Scanner(calls);
        String line, methodFrom,methodTo;
        String[] methods;

        while(reader.hasNext())
        {
            line=reader.nextLine();
            if(line.contains("(M)misiepysie.graph_be.")|| line.contains("(O)misiepysie.graph_be."))
            {
                line.trim();
                methods=line.split(" ");
                methodTo=methods[0].substring(2);
                methodFrom=methods[1].substring(3);
                DataCallGraph.getMethodsFromArray().add(methodFrom);
                DataCallGraph.getMethodsToArray().add(methodTo);
                DataCallGraph.getEdgesOfMethods().add(new EdgeMethod(methodTo,methodFrom));
                System.out.println("First method: "+ methodTo);
                System.out.println("Second method: "+methodFrom);
            }
        }
    }

}


