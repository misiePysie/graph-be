package misiepysie.graph_be.Modules;

import java.io.*;

public class AnalyzeModules {

        public static void analyzeModule(String path, DataModules temp) throws FileNotFoundException {

            File calls = new File(path);
            System.out.println(path);

            BufferedReader reader = new BufferedReader(new FileReader(calls));

            String line,methodTo, methodFrom, pathModuleFrom, pathModuleTo, tempString;
            String[] modules;
            String[] submodulesTo;
            String[] subSubModulesTo;
            String[] submodulesFrom;
            String[] subSubModulesFrom;

            try {

                while ((line=reader.readLine()) != null) {
                   // System.out.println(line);
                    if (line.contains("(M)" + ".graph_be.") || line.contains("(O)misiepysie.graph_be.") || line.contains("(S)misiepysie.graph_be")) {

                        line.trim();
                        //M:misiepysie.graph_be.Callgraph.AnalyzeCalls:analyzeCallGraph(java.lang.String,misiepysie.graph_be.Callgraph.DataCallGraph)   <------- TO
                        //(S)misiepysie.graph_be.Callgraph.DataCallGraph:getEdgesOfMethods() <-------- FROM
                        modules = line.split(" "); //modules dzieli nam na to i from

                        //TO
                        submodulesTo = modules[0].split(":");
                        methodTo = submodulesTo[submodulesTo.length - 1];//nazwa metody to
                        subSubModulesTo = submodulesTo[1].split("\\.");
                       if((subSubModulesTo[subSubModulesTo.length-2]).equals("graph_be"))
                       {
                           pathModuleTo=subSubModulesTo[subSubModulesTo.length-2];
                       }
                       else {
                           pathModuleTo = subSubModulesTo[subSubModulesTo.length - 2];//scieżka do paczki to
                       }
                        //FROM
                        submodulesFrom = modules[1].split(":");
                        methodFrom = submodulesFrom[submodulesFrom.length - 1];
                        subSubModulesFrom = submodulesFrom[0].split("\\.");
                        if((subSubModulesFrom[subSubModulesFrom.length-2]).equals("graph_be"))
                        {
                            pathModuleFrom=subSubModulesFrom[subSubModulesFrom.length-2];
                        }
                        else {
                            pathModuleFrom = subSubModulesFrom[subSubModulesFrom.length - 2];//scieżka do paczki to
                        }


                        EdgePackage tempEdgePackage;
                        EdgeMethodPackage tempEdgeMethodPackage;
                        NodePackage nodePackageTo;
                        NodePackage nodePackageFrom;


                        if (!DataModules.getListOfNodePackage().toString().contains(pathModuleTo) && !DataModules.getListOfNodePackage().toString().contains(pathModuleFrom)) { //jezeli nie mamy zadnej z paczek

                            nodePackageTo = new NodePackage(pathModuleTo); //tworzymy sobie obie paczki
                            nodePackageFrom = new NodePackage(pathModuleFrom);

                            nodePackageTo.getMethods().add(methodTo);//dodajemy metody do listy
                            nodePackageFrom.getMethods().add(methodFrom);

                            DataModules.getListOfNodePackage().add(nodePackageTo); //dodajemy paczki do listy paczek
                            DataModules.getListOfNodePackage().add(nodePackageFrom);

                            tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom); //tworzymy sobie edgea paczek i dodajemy do listy
                            DataModules.getListOfEdgePackage().add(tempEdgePackage);

                            tempEdgeMethodPackage = new EdgeMethodPackage(pathModuleTo, methodFrom); //tworzymy edgea metoda-paczka i dodajemy do listy
                            DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);

                        } else if (!DataModules.getListOfNodePackage().toString().contains(pathModuleTo) && DataModules.getListOfNodePackage().toString().contains(pathModuleFrom)) { //jezeli Nie mamy paczki to ale mamy paczke from
                            //obsluga PACZKI TO
                            nodePackageTo = new NodePackage(pathModuleTo); //musimy stworzyc paczke to
                            nodePackageTo.getMethods().add(methodTo); //dodajemy sobie tam metode
                            DataModules.getListOfNodePackage().add(nodePackageTo); //dodajemy paczke do listy paczek
                            //OBSLUGA PACZKI FROM i edge METHOD-PACKAGE
                            for (NodePackage e : DataModules.getListOfNodePackage()) {
                                if (e.getMethods().contains(methodFrom)) { // jezeli paczka from zawiera metode from
                                    tempEdgeMethodPackage = new EdgeMethodPackage(methodFrom, pathModuleTo); //tworzymy tymczasowy obiekt
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                } else { //jezeli paczka from nie zawiera metody from
                                    e.getMethods().add(methodFrom); //dodajemy tą metode do listy
                                    tempEdgeMethodPackage = new EdgeMethodPackage(methodFrom, pathModuleTo); //twozrymy edge nowego;
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage); //dodajemy go do listy
                                }
                            }
                            //OBSLUGA EDGE PACKAGE-PACKAGE
                            tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom);
                            DataModules.getListOfEdgePackage().add(tempEdgePackage);
                        } else if (DataModules.getListOfNodePackage().toString().contains(pathModuleTo) && !DataModules.getListOfNodePackage().toString().contains(pathModuleFrom)) { //mamy paczke to ale nie mamy paczki from
                            //obsluga PACZKI FROM
                            nodePackageFrom = new NodePackage(pathModuleFrom); //musimy stworzyc paczke from
                            nodePackageFrom.getMethods().add(methodFrom); //dodajemy sobie tam metode
                            DataModules.getListOfNodePackage().add(nodePackageFrom); //dodajemy paczke do listy paczek

                            //OBSLUGA PACZKI TO i edge METHOD-PACKAGE
                            for (NodePackage e : DataModules.getListOfNodePackage()) {
                                if (e.getMethods().contains(methodTo)) { // jezeli paczka to zawiera metode to
                                    tempEdgeMethodPackage = new EdgeMethodPackage(methodFrom, pathModuleTo); //to poprostu tworzymy edge mathod-package i dodajemy do do listy
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                } else { //jezeli paczka to nie zawiera metody to
                                    e.getMethods().add(methodTo); //dodajemy tą metode do listy
                                    tempEdgeMethodPackage = new EdgeMethodPackage(methodFrom, pathModuleTo); //i dopiero tworzymy edge i dodajemy do listy
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                }
                            }
                            //OBSLUGA EDGE PACKAGE-PACKAGE
                            tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom);
                            DataModules.getListOfEdgePackage().add(tempEdgePackage);
                        } else { //obie paczki są na liscie
                            for (NodePackage e : DataModules.getListOfNodePackage()) { //przechodzimy po liscie paczek;
                                //OBSLUGA PACZKI FROM
                                if (e.getName().equals(pathModuleFrom))  //jezeli natknęlismy sie na paczke from
                                {
                                    nodePackageFrom = e; //przypisujemy sobie paczke From do tymczasowej zmiennej
                                    if (!e.getMethods().contains(methodFrom)) //sprawdzamy czy nasza paczka ma metode ktora wywoluje
                                    {
                                        nodePackageFrom.getMethods().add(methodFrom); //jesli nie to ją dodajemy
                                    }
                                    DataModules.getListOfNodePackage().remove(e);
                                    DataModules.getListOfNodePackage().add(nodePackageFrom);

                                } //OBSLUGA PACZKI TO
                                if (e.getName().equals(pathModuleTo)) { //jezeli natknelismy sie na paczke to
                                    nodePackageTo = e;  //przypisujemy sobie paczke From do tymczasowej zmiennej
                                    if (!e.getMethods().contains(methodTo)) //sprawdzamy czy nasza paczka ma metode ktora jest wywolywana
                                    {
                                        e.getMethods().add(methodTo); //jesli nie to ją dodajemy
                                    }
                                    DataModules.getListOfNodePackage().remove(e);
                                    DataModules.getListOfNodePackage().add(nodePackageTo);
                                }
                                //OBSLUGA edgea METHOD-PACKAGE
                                tempEdgeMethodPackage = new EdgeMethodPackage(methodFrom, pathModuleTo); //tworzymy tymczasowy obiekt
                                if (DataModules.getListOfEdgeMethodPackage().contains(tempEdgeMethodPackage)) {//sprawdzamy czy nasz obiekt jest na liscie

                                    for (EdgeMethodPackage eMP:DataModules.getListOfEdgeMethodPackage()
                                         ) {
                                        if(eMP.equals(tempEdgeMethodPackage))
                                        {
                                            DataModules.getListOfEdgeMethodPackage().remove(eMP);
                                            tempEdgeMethodPackage.setWeight(eMP.getWeight()+1);
                                            DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                        }

                                    }
                                } else { //jezeli nie ma go na liscie to
                                    DataModules.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage); //dodajemy go do listy
                                }
                                //OBSLUGA egdea PACKAGE-PACKAGE
                                tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom); //tworzymy tymczasowego edgea
                                if (DataModules.getListOfEdgePackage().contains(tempEdgePackage)) {//sprawdzamy czy nasz obiekt jest na liscie
                                    for (EdgePackage eP:DataModules.getListOfEdgePackage()
                                    ) {
                                        if(eP.equals(tempEdgePackage))
                                        {
                                            DataModules.getListOfEdgePackage().remove(eP);
                                            tempEdgePackage.setWeight(eP.getWeight()+1);
                                            DataModules.getListOfEdgePackage().add(tempEdgePackage);
                                        }

                                    }

                                }
                                else{
                                    DataModules.getListOfEdgePackage().add(tempEdgePackage);
                                }
                            }

                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataModules.getListOfEdgePackage().forEach(x-> System.out.println(x));
            DataModules.getListOfEdgeMethodPackage().forEach(x-> System.out.println(x));
            DataModules.getListOfNodePackage().forEach(x-> System.out.println(x));
        }


}



