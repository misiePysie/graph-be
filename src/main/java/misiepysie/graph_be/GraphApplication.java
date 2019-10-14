package misiepysie.graph_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);

        ReadFiles importFiles=new ReadFiles();
        importFiles.listAllFilesNames();
        importFiles.listAllFilesNames().forEach(System.out::println);
        ReadFiles.createNodeForEachFile();
        //ReadFiles.getListOfSourceFiles().forEach(x->x.toString());


    }

    // test

}
