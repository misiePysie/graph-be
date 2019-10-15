package misiepysie.graph_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class GraphApplication {

    public static String pathBackEnd;
    public static String pathFrontEnd;

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);

          AnalyzeFile.listAllFilesNames(System.getProperty("user.dir")+"/src");
          AnalyzeFile.createNodeForEachFile();
          ArraysOfNodesAndEdges.getArrayListOfNodes().addAll(AnalyzeFile.getListOfNodes());
         // System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));

          AnalyzeFile.listAllFilesNames("C:\\Users\\Gabi\\Desktop\\graph-fe-app-master\\src");
          AnalyzeFile.createNodeForEachFile();
          ArraysOfNodesAndEdges.getArrayListOfNodes().addAll(AnalyzeFile.getListOfNodes());
        System.out.println(Arrays.toString(ArraysOfNodesAndEdges.getArrayListOfNodes().toArray()));
          //System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));



          AnalyzeFile.createEdge();
          ArraysOfNodesAndEdges.getArrayListOfEdges().addAll(AnalyzeFile.getListOfEdges());
         System.out.println(Arrays.toString(ArraysOfNodesAndEdges.getArrayListOfEdges().toArray()));

        }
}

