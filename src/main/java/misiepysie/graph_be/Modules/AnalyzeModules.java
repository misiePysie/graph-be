package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.DataCallGraph;
import misiepysie.graph_be.Callgraph.EdgeMethod;

import java.io.*;

import static misiepysie.graph_be.Callgraph.DataCallGraph.getEdgesOfMethods;

public class AnalyzeModules {

        public static void analyzeModule(String path, DataCallGraph temp) throws FileNotFoundException {

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
                    /*todo:tu musimy wyciagnac nazwe paczki i metody oraz wage, tworzymy od razu obiekt MisiePysiePckage
                       i dodajemy do listy Packagow(DataModules)*/
                        line.trim();
                        methods = line.split(" ");

                        /*todo: wyciagamy nazwe paczki z ktorej pochodzi ta metoda
                           tworzymy obiekt MisiePysiePackage(packageFrom)
                           sprawdzamy cz nameTo nalezy do MisiePysiePackage.method jezeli nie to dodajemy to do listy, jezeli nalezy to ignorujemy
                           dodajemy paczke do listy paczek w DataModules*/
                        methodFrom = methods[1].substring(3);
                        submethods=methodFrom.split(":");
                        nameFrom=submethods[1];

                        /*todo: to samo robimy dla To tyle, że musimy uwzglednic ze dana paczka moze istniec, zapisujemy tą nazwe do packageTo*/
                        methodTo = methods[0].substring(2);
                        submethods=methodTo.split(":");
                        nameTo=submethods[1];

                        int weight=1;
                        boolean isEdgeAdded=false;
                        for (EdgeMethodPackage e: DataModules.getListOfMethodEdges()) {

                            if(e.toString().equals(new EdgeMethodPackage(nameTo,nameFrom).toString())) {
                                weight++;
                                e.setWeight(weight);
                                isEdgeAdded=true;
                            }
                        }
                        if(isEdgeAdded==false)
                        {

                            //todo: tu zamiast nameTo dalemy packageTo
                            DataModules.getListOfMethodEdges().add(new EdgeMethodPackage(nameTo,nameFrom,weight));

                            //todo:zamienic ostatnia lininijke na przesylanie z waga (z tą na dole) to Do Norbiego jak ogarnie przesyłanie w weight
                            //getEdgesOfMethods().add(new EdgeMethod(methodTo,methodFrom,weight));
                        }
                        //todo: analogiczne postepowanie dla EdgePackage tylko zamiast nameTo dajemy packageTo za zamiast EdgeFrom dajemy PackageFrom
                        //todo: z wagą w samych paczkach bedzie inaczej: tutaj trzeba to ustawic:
                        // e.setWeight(e.geWeight()++) w forze
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



