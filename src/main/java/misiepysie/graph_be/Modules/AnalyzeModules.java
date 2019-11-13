package misiepysie.graph_be.Modules;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class AnalyzeModules {

        public static void analyzeModule(String path, DataModules temp) throws FileNotFoundException {

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

                        EdgePackage tempEdgePackage;
                        EdgeMethodPackage tempEdgeMethodPackage;
                        NodePackage nodePackageTo;
                        NodePackage nodePackageFrom;


                        if (!DataModules.getListOfNodePackage().toString().contains(nameModuleTo) && !DataModules.getListOfNodePackage().toString().contains(nameModuleFrom)) { //jezeli nie mamy zadnej z paczek

                            nodePackageTo = new NodePackage(nameModuleTo); //tworzymy sobie obie paczki
                            nodePackageFrom = new NodePackage(nameMethodFrom);

                            nodePackageTo.getMethods().add(nameMethodTo);//dodajemy metody do listy
                            nodePackageFrom.getMethods().add(nameMethodFrom);

                            DataModules.getListOfNodePackage().add(nodePackageTo); //dodajemy paczki do listy paczek
                            DataModules.getListOfNodePackage().add(nodePackageFrom);

                            tempEdgePackage = new EdgePackage(nameModuleTo, nameModuleFrom); //tworzymy sobie edgea paczek i dodajemy do listy
                            DataModules.getListOfEdgePackage().add(tempEdgePackage);

                            tempEdgeMethodPackage = new EdgeMethodPackage(nameModuleTo, nameMethodFrom); //tworzymy edgea metoda-paczka i dodajemy do listy
                            DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                        } else if (!DataModules.getListOfNodePackage().toString().contains(nameModuleTo) && DataModules.getListOfNodePackage().toString().contains(nameModuleFrom)) { //jezeli Nie mamy paczki to ale mamy paczke from
                            //obsluga PACZKI TO
                            nodePackageTo = new NodePackage(nameModuleTo); //musimy stworzyc paczke to
                            nodePackageTo.getMethods().add(nameMethodTo); //dodajemy sobie tam metode
                            DataModules.getListOfNodePackage().add(nodePackageTo); //dodajemy paczke do listy paczek
                            //OBSLUGA PACZKI FROM i edge METHOD-PACKAGE
                            for (NodePackage e : DataModules.getListOfNodePackage()) {
                                if (e.getMethods().contains(nameMethodFrom)) { // jezeli paczka from zawiera metode from
                                    tempEdgeMethodPackage = new EdgeMethodPackage(nameMethodFrom, nameModuleTo); //tworzymy tymczasowy obiekt
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                } else { //jezeli paczka from nie zawiera metody from
                                    e.getMethods().add(nameMethodFrom); //dodajemy tą metode do listy
                                    tempEdgeMethodPackage = new EdgeMethodPackage(nameMethodFrom, nameModuleTo); //twozrymy edge nowego;
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage); //dodajemy go do listy
                                }
                            }
                            //OBSLUGA EDGE PACKAGE-PACKAGE
                            tempEdgePackage = new EdgePackage(nameModuleTo, nameModuleFrom);
                            DataModules.getListOfEdgePackage().add(tempEdgePackage);
                        } else if (DataModules.getListOfNodePackage().toString().contains(nameModuleTo) && !DataModules.getListOfNodePackage().toString().contains(nameModuleFrom)) { //mamy paczke to ale nie mamy paczki from
                            //obsluga PACZKI FROM
                            nodePackageFrom = new NodePackage(nameModuleFrom); //musimy stworzyc paczke from
                            nodePackageFrom.getMethods().add(nameMethodFrom); //dodajemy sobie tam metode
                            DataModules.getListOfNodePackage().add(nodePackageFrom); //dodajemy paczke do listy paczek

                            //OBSLUGA PACZKI TO i edge METHOD-PACKAGE
                            for (NodePackage e : DataModules.getListOfNodePackage()) {
                                if (e.getMethods().contains(nameMethodTo)) { // jezeli paczka to zawiera metode to
                                    tempEdgeMethodPackage = new EdgeMethodPackage(nameMethodFrom, nameModuleTo); //to poprostu tworzymy edge mathod-package i dodajemy do do listy
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                } else { //jezeli paczka to nie zawiera metody to
                                    e.getMethods().add(nameMethodTo); //dodajemy tą metode do listy
                                    tempEdgeMethodPackage = new EdgeMethodPackage(nameMethodFrom, nameModuleTo); //i dopiero tworzymy edge i dodajemy do listy
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                }
                            }
                            //OBSLUGA EDGE PACKAGE-PACKAGE
                            tempEdgePackage = new EdgePackage(nameModuleTo, nameModuleFrom);
                            DataModules.getListOfEdgePackage().add(tempEdgePackage);
                        } else { //obie paczki są na liscie
                            for (NodePackage e : DataModules.getListOfNodePackage()) { //przechodzimy po liscie paczek;
                                //OBSLUGA PACZKI FROM
                                if (e.getName().equals(nameModuleFrom))  //jezeli natknęlismy sie na paczke from
                                {
                                    nodePackageFrom = e; //przypisujemy sobie paczke From do tymczasowej zmiennej
                                    if (!e.getMethods().contains(nameMethodFrom)) //sprawdzamy czy nasza paczka ma metode ktora wywoluje
                                    {
                                        e.getMethods().add(nameMethodFrom); //jesli nie to ją dodajemy
                                    }
                                } //OBSLUGA PACZKI TO
                                if (e.getName().equals(nameModuleTo)) { //jezeli natknelismy sie na paczke to
                                    nodePackageTo = e;  //przypisujemy sobie paczke From do tymczasowej zmiennej
                                    if (!e.getMethods().contains(nameMethodTo)) //sprawdzamy czy nasza paczka ma metode ktora jest wywolywana
                                    {
                                        e.getMethods().add(nameMethodTo); //jesli nie to ją dodajemy
                                    }

                                }
                                //OBSLUGA edgea METHOD-PACKAGE
                                tempEdgeMethodPackage = new EdgeMethodPackage(nameMethodFrom, nameModuleTo); //tworzymy tymczasowy obiekt
                                if (DataModules.getListOfEdgeMethodPackage().contains(tempEdgeMethodPackage)) {//sprawdzamy czy nasz obiekt jest na liscie
                                    for (EdgeMethodPackage eMP : DataModules.getListOfEdgeMethodPackage() //jesli jest na liscie to przechodzimy przez liste
                                    ) {
                                        if (eMP == tempEdgeMethodPackage) //jesli natrafimy na taki sam obiekt
                                        {
                                            eMP.setWeight(eMP.getWeight() + 1); //to zwiekszamy wage
                                        }
                                    }
                                } else { //jezeli nie ma go na liscie to
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage); //dodajemy go do listy
                                }
                                //OBSLUGA egdea PACKAGE-PACKAGE
                                tempEdgePackage = new EdgePackage(nameModuleFrom, nameModuleTo); //tworzymy tymczasowego edgea
                                if (DataModules.getListOfEdgePackage().contains(tempEdgePackage)) {//sprawdzamy czy nasz obiekt jest na liscie
                                    for (EdgePackage eP : DataModules.getListOfEdgePackage() //jesli jest na liscie to przechodzimy przez liste
                                    ) {
                                        if (eP == tempEdgePackage) //jesli natrafimy na taki sam obiekt
                                        {
                                            eP.setWeight(eP.getWeight() + 1); //to zwiekszamy wage
                                        }
                                    }
                                }
                            }

                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataModules.getListOfEdgePackage().forEach(x-> System.out.println(x));
        }


}



