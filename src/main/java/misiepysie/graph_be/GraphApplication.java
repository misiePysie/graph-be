package misiepysie.graph_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class GraphApplication {

    public static String directoryPath;

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);

        try{
            ReadFiles importFiles = new ReadFiles();
            importFiles.listAllFilesNames().forEach(System.out::println);
            ReadFiles.createNodeForEachFile();
            //ReadFiles.getListOfSourceFiles().forEach(x->x.toString());
        }
         catch(IOException e){
            System.out.println(e.getStackTrace());
        }

    }

    // test

}