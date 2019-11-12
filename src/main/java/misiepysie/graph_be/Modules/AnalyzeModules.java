package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.DataCallGraph;
import misiepysie.graph_be.Callgraph.EdgeMethod;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static misiepysie.graph_be.Callgraph.DataCallGraph.getEdgesOfMethods;
import static sun.security.krb5.internal.crypto.Nonce.value;

public class AnalyzeModules {

        public static void analyzeModule(String path, DataCallGraph temp) throws FileNotFoundException {

            File calls = new File(path);
            System.out.println(path);

            BufferedReader reader = new BufferedReader(new FileReader(calls));
            String line, nameModuleFrom, moduleTo,nameMethodFrom,nameMethodTo,nameModuleTo;
            String methodTo, methodFrom, sublineTo, sublineFrom, pathmodule;
            String[] modules;
            String[] submodules;
            String[] submethods;

            try {

                while ((line=reader.readLine()) != null) {
                    System.out.println(line);
                    if (line.contains("(M)" + ".graph_be.") || line.contains("(O)misiepysie.graph_be.") || line.contains("(S)misiepysie.graph_be")) {


                    /*todo:tu musimy wyciagnac nazwe paczki i metody oraz wage, tworzymy od razu obiekt MisiePysiePckage
                       i dodajemy do listy Packagow(DataModules)*/

                        line.trim();
                        modules = line.split(" ");

                        /*todo: wyciagamy nazwe paczki z ktorej pochodzi ta metoda
                           tworzymy obiekt MisiePysiePackage(packageFrom)
                           sprawdzamy cz nameTo nalezy do MisiePysiePackage.method jezeli nie to dodajemy to do listy, jezeli nalezy to ignorujemy
                           dodajemy paczke do listy paczek w DataModules*/


                        sublineFrom = modules[1].substring(14);
                        submethods = sublineFrom.split(":");
                        // nazwa metody from
                        nameMethodFrom = submethods[1];
                        //nazwa paczki
                        //graph_be.GraphObjects.Edge
                        pathmodule = submethods[0];

                        List<String> items = Arrays.asList(pathmodule.split("."));

                        nameModuleFrom = items.get(items.size() - 1);

                        if(!DataModules.getListOfPackagesFrom().contains(nameModuleFrom)){
                            DataModules.addElementToListOfPackagesFrom(nameModuleFrom);

                            MisiePysiePackage objectFrom = new MisiePysiePackage(nameModuleFrom);
                            DataModules.getListOfPackageMysiePysie().add(objectFrom);
                            if(!objectFrom.getMethods().contains(nameMethodFrom)) {
                                objectFrom.getMethods().add(nameMethodFrom);
                            }else{
                                //jeśli zawieraja waga ++
                            }

                        }else{
                            for(int i=0; i< DataModules.getListOfPackageMysiePysie().size() ;i++){
                                if(DataModules.getListOfPackageMysiePysie().get(i).getName() == nameModuleFrom){
                                    if(!DataModules.getListOfPackageMysiePysie().get(i).getMethods().contains(nameMethodFrom)){
                                        DataModules.getListOfPackageMysiePysie().get(i).getMethods().add(nameMethodFrom);
                                    }else{
                                        //jeśli zwawiera dana metode zwieksz wagE metody
                                    }
                                }
                            }
                        }


                        /*todo: to samo robimy dla To tyle, że musimy uwzglednic ze dana paczka moze istniec, zapisujemy tą nazwe do packageTo*/

                        //M:misiepysie.graph_be. Callgraph.AnalyzeCalls:analyzeCallGraph(java.lang.String,misiepysie.graph_be.Callgraph.DataCallGraph)
                        //(S)misiepysie.graph_be.Callgraph.DataCallGraph:getMethodsFromArray()

                        sublineTo = modules[0].substring(2);
                        submethods = sublineTo.split(":");
                        //nazwa metody to
                        nameMethodTo = submethods[1];
                        //nazwa paczki
                        submodules = submethods[0].split(".");
                        nameModuleTo = submodules[0];

                        MisiePysiePackage object = new MisiePysiePackage(nameModuleTo);
                        if(!object.getMethods().contains(nameMethodTo)){
                            object.getMethods().add(nameMethodTo);
                        }
                        else{

                        }



                        int weight=1;
                        boolean isEdgeAdded=false;
                        for (EdgeMethodPackage e: DataModules.getListOfMethodEdges()) {

                            if(e.toString().equals(new EdgeMethodPackage(nameMethodTo,nameMethodFrom).toString())) {
                                weight++;
                                e.setWeight(weight);
                                isEdgeAdded=true;
                            }
                        }
                        if(isEdgeAdded==false)
                        {

                            //todo: tu zamiast nameTo dalemy packageTo
                            DataModules.getListOfMethodEdges().add(new EdgeMethodPackage(nameMethodTo,nameMethodFrom,weight));

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



