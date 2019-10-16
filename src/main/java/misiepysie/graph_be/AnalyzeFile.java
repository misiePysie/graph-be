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
            getListOfNodes().add(sf);
        }
        resizeCircle();
    }

    public static List<String> listAllFilesNames(String path ) {

        try (Stream<Path> walk = Files.walk(Paths.get(path))) { //todo: add the path of current directory

            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
            //set list of files as an already read directory
            listOfFileNames=result;
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createEdge() {
        Edge edge;
        Node node2;
        int numberOfConnections = 0;
        listOfEdges=new ArrayList<Edge>();
        for (int k = 0; k < listOfNodes.size(); k++) {
            try {
                Stream<String> lines = Files.lines(Paths.get(listOfNodes.get(k).getId()));
                List<String> content = lines.collect(Collectors.toList());
                String lineWithImport=null;
                for (int i = 0; i < content.size(); i++) {
                    lineWithImport = searchImports(content.get(i));
                   // System.out.println(lineWithImport+"\n");

                    if (!(lineWithImport == null)) {
                        numberOfConnections = countConnections(lineWithImport);

                        for (int j = 0; j < listOfNodes.size(); j++) {

                            if (listOfNodes.get(j).getLabel().equals(searchAnotherNode(lineWithImport))) ;
                            node2 = listOfNodes.get(j);
                            edge = new Edge(listOfNodes.get(k), node2, numberOfConnections);
                            getListOfEdges().add(edge);
                           // break;

                        }
                        break;
                    }else
                    {
                        break;
                    }
                }


            }
            catch(IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }

    }

//    public static int countingWordsInString(String descriptionFile, String keyWord ){
//        int j = 0;
//        Pattern p = Pattern.compile(keyWord);
//        Matcher m = p.matcher( descriptionFile );
//        while (m.find()) {
//            j++;
//        }
//        return j;
//    }

    public static String searchImports(String line) {
        if (line.startsWith("import")) {
            return line;
        }
        else return null;
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


    public static String searchAnotherNode (String lineWithImport) {

        String anotherNode;
        int indexOfFirstQuotes;
        if (lineWithImport.contains("\"")) {
            indexOfFirstQuotes=lineWithImport.indexOf("\"");
            anotherNode=lineWithImport.substring(indexOfFirstQuotes+1,lineWithImport.length()-2);
            return anotherNode;
        } else if ((lineWithImport.contains("css"))) {
           // lineWithImport.replace("*","\'");
            indexOfFirstQuotes=lineWithImport.indexOf("\'");
            anotherNode=lineWithImport.substring(indexOfFirstQuotes+1,lineWithImport.length()-2);
            return anotherNode;

        }else{

            return null;
        }

    }
    public static int countConnections(String lineWithImport){
        int numberOfCommas=1;

        for (int i=0;i<lineWithImport.length();i++){
            if (lineWithImport.charAt(i)==','){
                numberOfCommas++;
            }
        }
        return numberOfCommas;
    }


}
