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
          System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));

          AnalyzeFile.listAllFilesNames(System.getProperty("user.home") + "/graph-fe-app/src");
          AnalyzeFile.createNodeForEachFile();
          System.out.println(Arrays.toString(AnalyzeFile.getListOfNodes().toArray()));

          //AnalyzeFile.listAllFilesNames("F:\\Java\\Projects\\graphy\\src");

        }
}

