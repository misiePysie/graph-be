package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.DataCallGraph;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class AnalyzeModules {

        public static void analyzeModule(String path, DataCallGraph temp) throws FileNotFoundException {

            File calls = new File(path);
            System.out.println(path);

            BufferedReader reader = new BufferedReader(new FileReader(calls));
            String line, nameModuleFrom, moduleTo,nameMethodFrom,nameMethodTo,nameModuleTo;
            String methodTo, methodFrom, sublineTo, sublineFrom, pathModuleFrom, pathModuleTo;
            String[] modules;
            String[] submodules;
            String[] submethods;

            try {

                while ((line=reader.readLine()) != null) {
                    System.out.println(line);
                    if (line.contains("(M)" + ".graph_be.") || line.contains("(O)misiepysie.graph_be.") || line.contains("(S)misiepysie.graph_be")) {

                        /*todo:tu musimy wyciagnac nazwe paczki i metody oraz wage, tworzymy od razu obiekt MisiePysiePckage i dodajemy do listy Packagow(DataModules)*/

                        /*todo: wyciagamy nazwe paczki z ktorej pochodzi ta metoda
                           tworzymy obiekt MisiePysiePackage(packageFrom)
                           sprawdzamy cz nameTo nalezy do MisiePysiePackage.method jezeli nie to dodajemy to do listy, jezeli nalezy to ignorujemy
                           dodajemy paczke do listy paczek w DataModules*/

                        line.trim();

                        //M:misiepysie.graph_be.Callgraph.AnalyzeCalls:analyzeCallGraph(java.lang.String,misiepysie.graph_be.Callgraph.DataCallGraph)   <------- TO
                        //(S)misiepysie.graph_be.Callgraph.DataCallGraph:getEdgesOfMethods() <-------- FROM

                        //TO
                        modules = line.split(" ");
                        sublineTo = modules[0].substring(13);
                        submethods = sublineTo.split(":");
                        //nazwa metody to
                        nameMethodTo = submethods[1];
                        //scieżka do paczki
                        pathModuleTo = submethods[0];
                        List<String> itemsTo = Arrays.asList(pathModuleTo.split("."));
                        //nazwapaczki to
                        nameModuleTo = itemsTo.get(itemsTo.size() - 1);

                        //FROM
                        sublineFrom = modules[1].substring(14);
                        submethods = sublineFrom.split(":");
                        // nazwa metody from
                        nameMethodFrom = submethods[1];
                        //nazwa paczki
                        //graph_be.GraphObjects.Edge
                        pathModuleFrom = submethods[0];
                        List<String> itemsFrom = Arrays.asList(pathModuleFrom.split("."));
                        //wybieramy element przedostatni z tablicy ktory jest paczka (na przykładzie to jest graphobject)
                        nameModuleFrom = itemsFrom.get(itemsFrom.size() - 1);

                        for ( NodePackage e : DataModules.getListOfNodePackage() ) {
                            if(!(e.getName().equals(nameModuleTo)) && !( e.getName().equals(nameModuleFrom)) ){
                                NodePackage nodePackageOne = new NodePackage(nameModuleTo);
                                NodePackage nodePackageTwo = new NodePackage(nameMethodFrom);
                                DataModules.getListOfNodePackage().add(nodePackageOne);
                                DataModules.getListOfNodePackage().add(nodePackageTwo);

                            }
                            else if(!(e.getName().equals(nameModuleTo)) && ( e.getName().equals(nameModuleFrom))){
                                NodePackage nodePackage = new NodePackage(nameModuleTo);

                            }
                            else if((e.getName().equals(nameModuleTo)) && !( e.getName().equals(nameModuleFrom))){
                                NodePackage nodePackage = new NodePackage(nameMethodFrom);
                            }else{
                                // dane dwa wezly juz istnieja ? ink weight?
                            }

                        }


//
//                        if(!DataModules.getListOfPackagesFrom().contains(nameModuleFrom)){
//
//                            DataModules.addElementToListOfPackagesFrom(nameModuleFrom);
//                            NodePackage objectFrom = new NodePackage(nameModuleFrom);
//                            DataModules.getListOfNodePackage().add(objectFrom);
//                            if(!objectFrom.getMethods().contains(nameMethodFrom)) {
//                                objectFrom.getMethods().add(nameMethodFrom);
//                            }else{
//                                //jeśli zawieraja waga +
//                            }
//
//                        }else{
//                            for(int i=0; i< DataModules.getListOfNodePackage().size() ;i++){
//                                if(DataModules.getListOfNodePackage().get(i).getName() == nameModuleFrom){
//                                    if(!DataModules.getListOfNodePackage().get(i).getMethods().contains(nameMethodFrom)){
//                                        DataModules.getListOfNodePackage().get(i).getMethods().add(nameMethodFrom);
//                                    }else{
//                                        //jeśli zwawiera dana metode zwieksz wagE metody
//                                    }
//                                }
//                            }
//                        }

                        //tworzymy sobie krawędzi (paczka z paczka) oraz (paczka z metoda)




                        if(DataModules.getListOfEdgePackage().size() == 0){
                            EdgePackage edgePackage = new EdgePackage(nameModuleTo,nameModuleFrom);
                            edgePackage.setWeight(1);
                            EdgeMethodPackage edgeMethodPackageA = new EdgeMethodPackage(nameMethodTo,nameModuleTo);
                            EdgeMethodPackage edgeMethodPackageB = new EdgeMethodPackage(nameMethodFrom,nameModuleFrom);

                        }else{
                            for(int i=0; i< DataModules.getListOfEdgePackage().size() ;i++){
                                if(DataModules.getListOfEdgePackage().get(i).getFrom() == nameModuleFrom && DataModules.getListOfEdgePackage().get(i).getTo() == nameModuleTo){
                                    int currentWeight = DataModules.getListOfEdgePackage().get(i).getWeight() + 1;
                                    DataModules.getListOfEdgePackage().get(i).setWeight(currentWeight);
                                }
                                else{
                                    EdgePackage edgePackage = new EdgePackage(nameModuleTo,nameModuleFrom);
                                    EdgeMethodPackage edgeMethodPackageA = new EdgeMethodPackage(nameMethodTo,nameModuleTo);
                                    EdgeMethodPackage edgeMethodPackageB = new EdgeMethodPackage(nameMethodFrom,nameModuleFrom);
                                    edgePackage.setWeight(1);

                                }
                            }
                        }



                        /*todo: to samo robimy dla To tyle, że musimy uwzglednic ze dana paczka moze istniec, zapisujemy tą nazwe do packageTo*/

                        //M:misiepysie.graph_be. Callgraph.AnalyzeCalls:analyzeCallGraph(java.lang.String,misiepysie.graph_be.Callgraph.DataCallGraph)
                        //(S)misiepysie.graph_be.Callgraph.DataCallGraph:getMethodsFromArray()


                        int weight=1;
                        boolean isEdgeAdded=false;
//                        for (EdgeMethodPackage e: DataModules.getListOfMethodEdges()) {
//
//                            if(e.toString().equals(new EdgeMethodPackage(nameMethodTo,nameMethodFrom).toString())) {
//                                weight++;
//                                e.setWeight(weight);
//                                isEdgeAdded=true;
//                            }
//                        }
                        if(isEdgeAdded==false)
                        {

                            //todo: tu zamiast nameTo dalemy packageTo
                            //DataModules.getListOfMethodEdges().add(new EdgeMethodPackage(nameMethodTo,nameMethodFrom,weight));

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



