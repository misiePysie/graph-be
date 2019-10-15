package misiepysie.graph_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class GraphApplication {

    public static String directoryPath;

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);

          AnalyzeFile.listAllFilesNames(System.getProperty("user.dir")+"/src");
          AnalyzeFile.createNodeForEachFile();
          System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));

          AnalyzeFile.listAllFilesNames("C:\\Users\\Gabi\\Desktop\\graph-fe-app-master\\src");
          AnalyzeFile.createNodeForEachFile();
          System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));



          AnalyzeFile.createEdge();
         System.out.println(Arrays.toString(AnalyzeFile.getListOfEdges().toArray()));

        }
}

