package misiepysie.graph_be.Modules;

import misiepysie.graph_be.Callgraph.NodeMethod;

import java.io.*;

public class AnalyzeModules {

    public static DataModules analyzeModule(String path, DataModules temp) throws FileNotFoundException {

        File calls = new File(path);
        System.out.println(path);

        BufferedReader reader = new BufferedReader(new FileReader(calls));

        String line, methodTo, methodFrom, pathModuleFrom, pathModuleTo, tempString;
        String[] modules;
        String[] submodulesTo;
        String[] subSubModulesTo;
        String[] submodulesFrom;
        String[] subSubModulesFrom;

        String temporary1,temporary2;

        try {

            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
                if (line.contains("(M)" + ".graph_be.") || line.contains("(O)misiepysie.graph_be.") || line.contains("(S)misiepysie.graph_be")) {

                    line.trim();
                    //M:misiepysie.graph_be.Callgraph.AnalyzeCalls:analyzeCallGraph(java.lang.String,misiepysie.graph_be.Callgraph.DataCallGraph)   <------- TO
                    //(S)misiepysie.graph_be.Callgraph.DataCallGraph:getEdgesOfMethods() <-------- FROM
                    modules = line.split(" "); //modules dzieli nam na to i from

                    //TO
                    submodulesTo = modules[0].split(":");
                    temporary1 = submodulesTo[submodulesTo.length - 1];//nazwa metody to
                    temporary2=submodulesTo[submodulesTo.length-1].substring(temporary1.indexOf('(')+1,temporary1.indexOf(")"));
                    methodTo=temporary1.replace(temporary2,"");
                    subSubModulesTo = submodulesTo[1].split("\\.");
                    if ((subSubModulesTo[subSubModulesTo.length - 2]).equals("graph_be")) {
                        pathModuleTo = subSubModulesTo[subSubModulesTo.length - 2];
                    } else {
                        pathModuleTo = subSubModulesTo[subSubModulesTo.length - 2];//scieżka do paczki to
                    }
                    //FROM
                    submodulesFrom = modules[1].split(":");
                    temporary1 = submodulesFrom[submodulesFrom.length - 1];//nazwa metody to
                    temporary2=submodulesFrom[submodulesFrom.length-1].substring(temporary1.indexOf('(')+1,temporary1.indexOf(")"));
                    methodFrom = temporary1.replace(temporary2,"");
                    subSubModulesFrom = submodulesFrom[0].split("\\.");
                    if ((subSubModulesFrom[subSubModulesFrom.length - 2]).equals("graph_be")) {
                        pathModuleFrom = subSubModulesFrom[subSubModulesFrom.length - 2];
                    } else {
                        pathModuleFrom = subSubModulesFrom[subSubModulesFrom.length - 2];//scieżka do paczki to
                    }


                    EdgePackage tempEdgePackage;
                    EdgeMethodPackage tempEdgeMethodPackage;
                    NodePackage nodePackageTo;
                    NodePackage nodePackageFrom;
                    NodeMethod nodeMethodTo;
                    NodeMethod nodeMethodFrom;
                    boolean contains=false;
                    boolean containsFrom=false;
                    boolean containsTo=false;


                    if (temp.getListOfNodePackage().isEmpty()) {
                        if(pathModuleFrom.equals(pathModuleTo))
                        {
                            nodePackageTo = new NodePackage(pathModuleTo);
                            nodeMethodTo=new NodeMethod(methodTo);
                            nodeMethodFrom=new NodeMethod(methodFrom);

                            nodePackageTo.getMethods().add(nodeMethodTo);//dodajemy metody do listy
                            nodePackageTo.getMethods().add(nodeMethodFrom);
                            temp.getListOfNodePackage().add(nodePackageTo); //dodajemy paczki do listy paczek

                            tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom); //tworzymy sobie edgea paczek i dodajemy do listy
                            temp.getListOfEdgePackage().add(tempEdgePackage);

                            tempEdgeMethodPackage = new EdgeMethodPackage(pathModuleTo, methodFrom); //tworzymy edgea metoda-paczka i dodajemy do listy
                            temp.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);

                        }else {
                            nodePackageTo = new NodePackage(pathModuleTo); //tworzymy sobie obie paczki
                            nodePackageFrom = new NodePackage(pathModuleFrom);
                            nodeMethodTo=new NodeMethod(methodTo);
                            nodeMethodFrom=new NodeMethod(methodFrom);
                            nodePackageTo.getMethods().add(nodeMethodTo);//dodajemy metody do listy
                            nodePackageFrom.getMethods().add(nodeMethodFrom);

                            temp.getListOfNodePackage().add(nodePackageTo); //dodajemy paczki do listy paczek
                            temp.getListOfNodePackage().add(nodePackageFrom);

                            tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom); //tworzymy sobie edgea paczek i dodajemy do listy
                            temp.getListOfEdgePackage().add(tempEdgePackage);

                            tempEdgeMethodPackage = new EdgeMethodPackage(pathModuleTo, methodFrom); //tworzymy edgea metoda-paczka i dodajemy do listy
                            temp.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                           // System.out.println(temp.getListOfNodePackage());
                        }
                    } else {
                        if(pathModuleFrom.equals(pathModuleTo)) //sciezki maja ta sama nazwe
                        {
                            contains=false;

                            for(int i =0;i<temp.getListOfNodePackage().size();i++)
                            {
                                if(temp.getListOfNodePackage().get(i).getName().equals(pathModuleFrom)) //sprawdzamy czy lista nodów zawiera nasza sciezke
                                {

                                    if(!temp.getListOfNodePackage().get(i).getMethods().toString().contains(methodTo)) { //jesli tak to dodajemy metody jesli  ich nie ma
                                        nodeMethodTo = new NodeMethod(methodTo);
                                        temp.getListOfNodePackage().get(i).getMethods().add(nodeMethodTo);
                                    }

                                    if(!temp.getListOfNodePackage().get(i).getMethods().toString().contains(methodFrom)) {
                                        nodeMethodFrom = new NodeMethod(methodFrom);
                                        temp.getListOfNodePackage().get(i).getMethods().add(nodeMethodFrom);
                                    }


                                    tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom); //tworzymy sobie edgea paczek i dodajemy do listy
                                    for(int j=0;j<temp.getListOfEdgePackage().size();j++)
                                    {
                                        if(temp.getListOfEdgePackage().get(j).getFrom().equals(pathModuleFrom)&&temp.getListOfEdgePackage().get(j).getTo().equals(pathModuleTo)){
                                            contains=true;
                                            temp.getListOfEdgePackage().get(j).setWeight(temp.getListOfEdgePackage().get(j).getWeight()+1);
                                        }
                                    }
                                    if(!contains)
                                    {
                                        temp.getListOfEdgePackage().add(tempEdgePackage);
                                    }
                                    contains=false;
                                    tempEdgeMethodPackage = new EdgeMethodPackage(pathModuleTo, methodFrom); //tworzymy edgea metoda-paczka i dodajemy do listy
                                    for(int j=0;j<temp.getListOfEdgeMethodPackage().size();j++)
                                    { //todo: zieniac
                                        if(temp.getListOfEdgeMethodPackage().get(j).getFrom().equals(methodFrom)&&temp.getListOfEdgeMethodPackage().get(j).getTo().equals(pathModuleTo)){
                                            contains=true;
                                            temp.getListOfEdgeMethodPackage().get(j).setWeight(temp.getListOfEdgeMethodPackage().get(j).getWeight()+1);
                                        }
                                    }
                                    if(!contains)
                                    {
                                        temp.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                    }
                                    contains=true;
                                }
                            }
                            if(!contains) //jesleli sciezka nie zawiera sie w liscie node'ow
                            {
                                nodePackageFrom=new NodePackage(pathModuleFrom); //tworzymy jednego node'a, dodajemy do niego obie metody i dodajemy do listy
                                nodeMethodTo=new NodeMethod(methodTo);
                                nodeMethodFrom=new NodeMethod(methodFrom);
                                nodePackageFrom.getMethods().add(nodeMethodFrom);
                                nodePackageFrom.getMethods().add(nodeMethodTo);
                                temp.getListOfNodePackage().add(nodePackageFrom);

                                tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom); //tworzymy sobie edgea paczek i dodajemy do listy
                                temp.getListOfEdgePackage().add(tempEdgePackage);

                                tempEdgeMethodPackage = new EdgeMethodPackage(pathModuleTo, methodFrom); //tworzymy edgea metoda-paczka i dodajemy do listy
                                temp.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                            }

                        }
                        else{ //sciezki nie sa takie same
                            for(int i=0;i<temp.getListOfNodePackage().size();i++) //sprawdzamy czy sa na liscie nodow jesli tak dodajemy metody jesli ich tam nie ma
                            {
                                if(temp.getListOfNodePackage().get(i).getName().equals(pathModuleFrom)){
                                    containsFrom=true;
                                    if(!temp.getListOfNodePackage().get(i).getMethods().toString().contains(methodFrom)) {
                                        nodeMethodFrom = new NodeMethod(methodFrom);
                                        temp.getListOfNodePackage().get(i).getMethods().add(nodeMethodFrom);
                                    }
                                }
                                if(temp.getListOfNodePackage().get(i).getName().equals(pathModuleTo))
                                {
                                    containsTo=true;
                                    if(!temp.getListOfNodePackage().get(i).getMethods().toString().contains(methodTo)) { //jesli tak to dodajemy metody jesli  ich nie ma
                                        nodeMethodTo = new NodeMethod(methodTo);
                                        temp.getListOfNodePackage().get(i).getMethods().add(nodeMethodTo);
                                    }

                                }
                            }
                            if(containsFrom==containsTo==true)
                            {
                                contains=false;
                                tempEdgePackage = new EdgePackage(pathModuleTo, pathModuleFrom); //tworzymy sobie edgea paczek i dodajemy do listy
                                for(int j=0;j<temp.getListOfEdgePackage().size();j++)
                                {
                                    if(temp.getListOfEdgePackage().get(j).getFrom().equals(pathModuleFrom)&&temp.getListOfEdgePackage().get(j).getTo().equals(pathModuleTo)){
                                        contains=true;
                                        temp.getListOfEdgePackage().get(j).setWeight(temp.getListOfEdgePackage().get(j).getWeight()+1);
                                    }
                                }
                                if(!contains)
                                {
                                    temp.getListOfEdgePackage().add(tempEdgePackage);
                                }
                                contains=false;
                                tempEdgeMethodPackage = new EdgeMethodPackage(pathModuleTo, methodFrom); //tworzymy edgea metoda-paczka i dodajemy do listy
                                for(int j=0;j<temp.getListOfEdgeMethodPackage().size();j++)
                                {
                                    if(temp.getListOfEdgeMethodPackage().get(j).getFrom().equals(methodFrom)&&temp.getListOfEdgeMethodPackage().get(j).getTo().equals(pathModuleTo)){
                                        contains=true;
                                        temp.getListOfEdgeMethodPackage().get(j).setWeight(temp.getListOfEdgeMethodPackage().get(j).getWeight()+1);
                                    }
                                }
                                if(!contains)
                                {
                                    temp.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                                }
                                contains=true;
                            }
                            if(!containsFrom)// jesli nie am ich na liscie nodow to tworzymy nody i dodajemy do listy
                            {
                                nodePackageFrom=new NodePackage(pathModuleFrom);
                                nodeMethodFrom=new NodeMethod(methodFrom);
                                nodePackageFrom.getMethods().add(nodeMethodFrom);
                                temp.getListOfNodePackage().add(nodePackageFrom);
                            }
                            if(!containsTo)
                            {
                                nodePackageTo=new NodePackage(pathModuleTo);
                                nodeMethodTo=new NodeMethod(methodTo);
                                nodePackageTo.getMethods().add(nodeMethodTo);
                                temp.getListOfNodePackage().add(nodePackageTo);
                            }
                            if(!contains)
                            {
                                tempEdgePackage=new EdgePackage(pathModuleTo,pathModuleFrom);
                                temp.getListOfEdgePackage().add(tempEdgePackage);

                                tempEdgeMethodPackage=new EdgeMethodPackage(pathModuleTo,methodFrom);
                                temp.getListOfEdgeMethodPackage().add(tempEdgeMethodPackage);
                            }

                        }

                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//            temp.getListOfEdgePackage().forEach(x-> System.out.println(x.toString()));
//            temp.getListOfEdgeMethodPackage().forEach(x-> System.out.println(x.toString()));
//            temp.getListOfNodePackage().forEach(x-> System.out.println(x.toString()));
        return temp;
    }


}



