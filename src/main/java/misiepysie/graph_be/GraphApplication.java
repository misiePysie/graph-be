package misiepysie.graph_be;

import misiepysie.graph_be.Callgraph.AnalyzeCalls;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class GraphApplication {

//    public static String pathBackEnd;
//    public static String pathFrontEnd;
//public static DirectoryPath directory = new DirectoryPath();

    public static void main(String[] args) {

        try {
            AnalyzeCalls.analyzeCallGraph();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       // SpringApplication.run(GraphApplication.class, args);


//         System.out.println(Arrays.toString(Data.getEdgesData().toArray()));

        }
}

