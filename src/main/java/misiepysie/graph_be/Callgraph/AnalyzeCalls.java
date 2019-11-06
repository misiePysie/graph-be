package misiepysie.graph_be.Callgraph;

import jdk.jfr.events.FileReadEvent;

import java.io.*;
import java.util.Scanner;

public class AnalyzeCalls {


    public static void analyzeCallGraph(String path, DataCallGraph temp) throws FileNotFoundException {

        File calls = new File(path);
        System.out.println(path);

        BufferedReader reader = new BufferedReader(new FileReader(calls));
        String st;
        String line, methodFrom, methodTo;
        String[] methods;

        try {
            while ((line = reader.readLine()) != null) {

                if (line.contains("(M)misiepysie.graph_be.") || line.contains("(O)misiepysie.graph_be.")) {
                    line.trim();
                    methods = line.split(" ");
                    methodTo = methods[0].substring(2);
                    methodFrom = methods[1].substring(3);
                    temp.getMethodsFromArray().add(methodFrom);
                    temp.getMethodsToArray().add(methodTo);
                    temp.getEdgesOfMethods().add(new EdgeMethod(methodTo, methodFrom));
                    System.out.println("First method: " + methodTo);
                    System.out.println("Second method: " + methodFrom);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


