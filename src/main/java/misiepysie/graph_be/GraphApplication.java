package misiepysie.graph_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class GraphApplication {

    public static String directoryPath;

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);

          ReadFiles.listAllFilesNames(System.getProperty("user.dir")+"/src");
          ReadFiles.createNodeForEachFile();
          System.out.println(Arrays.toString(ReadFiles.getListOfSourceFiles().toArray()));

          ReadFiles.listAllFilesNames(System.getProperty("user.home") + "/graph-fe-app/src");
          ReadFiles.createNodeForEachFile();
          System.out.println(Arrays.toString(ReadFiles.getListOfSourceFiles().toArray()));

          //ReadFiles.listAllFilesNames("F:\\Java\\Projects\\graphy\\src");

        }
}

