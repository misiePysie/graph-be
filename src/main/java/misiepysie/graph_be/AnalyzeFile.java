package misiepysie.graph_be;

import misiepysie.graph_be.GraphObjects.Edge;
import misiepysie.graph_be.GraphObjects.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyzeFile {

    public AnalyzeFile() throws IOException {
    }

    private static List<Node> listOfNodes;

    public static void setListOfFileNames(List<String> listOfFileNames) {
        AnalyzeFile.listOfFileNames = listOfFileNames;
    }

    private static List<String> listOfFileNames;

    private static List<Edge> listOfEdges;
    public static List<Edge> getListOfEdges() {
        return listOfEdges;
    }

    public static void setListOfEdges(List<Edge> listOfEdges) {
        AnalyzeFile.listOfEdges = listOfEdges;
    }

    public static void createNodeForEachFile() {
        listOfNodes = new ArrayList<Node>();

        for (int i = 1; i < listOfFileNames.size() ; i++) {

            Node sf = new Node(listOfFileNames.get(i),getName(listOfFileNames.get(i)),getSize(i));
            listOfNodes.add(sf);
        }
        resizeCircle();
    }

    public static List<String> listAllFilesNames(String path ) {
        try (Stream<Path> walk = Files.walk(Paths.get(path))) { //todo: add the path of current directory


            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
            //set list of files as an already read directory

            listOfFileNames.addAll(result);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createEdge() {
        Edge edge;
        int numberOfConnections = 0;
        listOfEdges=new ArrayList<Edge>();
       listOfNodes.forEach(x-> System.out.println(x));
        for (Node n1:listOfNodes
             ) {
            System.out.println("To: "+n1);
            try {
                Stream<String> lines = Files.lines(Paths.get(n1.getId()));
                List<String> content = lines.collect(Collectors.toList());
                for (String s : content
                ) {
                    if (searchImports(s)) {
                        System.out.println("True");
                        System.out.println(s);
                        numberOfConnections = countConnections(s);
                        System.out.println(numberOfConnections);
                        for (Node n2 : listOfNodes
                        ) {
                            if (!(n1.getId().contains(searchAnotherNode(s))) && n2.getId().contains(searchAnotherNode(s))) {
                                if (searchAnotherNode(s).contains("style")) {
                                    if (n1.getLabel().contains("footer") || n1.getLabel().contains("header")) {
                                        System.out.println("From :" + listOfNodes.get(listOfNodes.indexOf(n1) + 1));
                                        System.out.println(listOfNodes.get(listOfNodes.indexOf(n1) + 1).getLabel());
                                        edge = new Edge(listOfNodes.get(listOfNodes.indexOf(n1) + 1), n1, numberOfConnections);
                                        listOfEdges.add(edge);
                                    } else if (n1.getLabel().contains("graphNetwork")) {
                                        System.out.println("From :" + listOfNodes.get(listOfNodes.indexOf(n1) + 3));
                                        System.out.println(listOfNodes.get(listOfNodes.indexOf(n1) + 3).getLabel());
                                        edge = new Edge(listOfNodes.get(listOfNodes.indexOf(n1) + 3), n1, numberOfConnections);
                                        listOfEdges.add(edge);

                                    } else {
                                        System.out.println("From :" + n2);
                                        System.out.println(n2.getLabel());
                                        edge = new Edge(n2, n1, numberOfConnections);
                                        listOfEdges.add(edge);
                                    }
                                } else {
                                    System.out.println("From :" + n2);
                                    System.out.println(n2.getLabel());
                                    edge = new Edge(n2, n1, numberOfConnections);
                                    listOfEdges.add(edge);
                                }

                                break;
                            }

                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static boolean searchImports(String line) {
        if (line.startsWith("import")) {
            return true;
        }
        else return false;
    }

    public static double getSize(int i) {
        File f = new File(listOfFileNames.get(i));
        return f.length();
    }

    public static String getName(String path) {
        File f=new File(path);
        return f.getName();
    }

    private static void resizeCircle()
    {
        double max=-1;
        int index=-1;
        for (int i = 0; i< listOfNodes.size(); i++)
        {
            if(listOfNodes.get(i).getFileSize()>max) {
                max = listOfNodes.get(i).getFileSize();
                index = i;
            }
        }
        listOfNodes.get(index).setSizeOfCircle(100);
        double val=0;
        for(int i = 0; i< listOfNodes.size(); i++)
        {
            val=(listOfNodes.get(i).getFileSize()*100)/max;
            listOfNodes.get(i).setSize(val);
        }

    }

    public static List<Node> getListOfNodes() {
        return listOfNodes;
    }


    public static String searchAnotherNode(String lineWithImport) {

        String anotherNode;
        int indexOfFirstQuotes;
        int ind;
        if(lineWithImport.contains("from")) //dla plikow js'owych
        {
            ind=lineWithImport.indexOf("from");
            if(lineWithImport.contains("services"))
            {
                return "httpService";

            }else if(lineWithImport.contains("modules")) {
                anotherNode=lineWithImport.substring(lineWithImport.length()-7,lineWithImport.length()-2);
                return anotherNode;

            }else if(lineWithImport.contains("./css")) {
                return "style";

            }else{
                anotherNode = lineWithImport.substring(ind+8 ,lineWithImport.length() - 2);
                return anotherNode;
            }


        }else if (lineWithImport.contains("graph_be")) { //dla plikow java'owych
            ind = lineWithImport.indexOf(".graph_be");
            if (lineWithImport.contains("Data")) {
                anotherNode = lineWithImport.substring(ind + 15, lineWithImport.length() - 1);
                System.out.println(anotherNode+".java");
                return anotherNode+".java";
            }
            if (lineWithImport.contains("GraphObjects")) {
                anotherNode = lineWithImport.substring(ind + 23, lineWithImport.length() - 1);
                System.out.println(anotherNode+".java");
                return anotherNode+".java";
            }
        }
            return " ";


    }

    public static String searchPathToClass(String pathToClass) {
        for (int i = 0; i < listOfFileNames.size(); i++) {
            if (listOfFileNames.get(i).contains(pathToClass)) {
                return listOfFileNames.get(i);
            }
        }
        return null;
    }

    public static int countConnections(String lineWithImport){
        int numberOfCommas=1;

        for (int i=0;i<lineWithImport.length();i++){
            if (lineWithImport.charAt(i)==','){
                numberOfCommas++;
            }
            if(lineWithImport.charAt(i)=='*')
            {
                numberOfCommas=8888;
            }
        }

        return numberOfCommas;
    }



    public static List<String> getListOfFileNames() {
        return listOfFileNames;
    }
}
