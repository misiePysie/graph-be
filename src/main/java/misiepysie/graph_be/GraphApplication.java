package misiepysie.graph_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class GraphApplication {

    public static String directoryPath;

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);

        try{
            ReadFiles importFiles = new ReadFiles();
            importFiles.listAllFilesNames().forEach(System.out::println);
            ReadFiles.createNodeForEachFile();
            ReadFiles.getListOfSourceFiles().forEach(x->x.toString());

            //to co przesylamy chlopakom, nie musimy tego na razie wyswietlac
            //System.out.println(Arrays.toString(ReadFiles.getListOfSourceFiles().toArray()));
        }
         catch(IOException e){
            System.out.println(e.getStackTrace());
        }

    }

}